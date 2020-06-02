package com.epam.mentoring.department.service;

import com.epam.mentoring.department.dto.DepartmentDto;
import com.epam.mentoring.department.model.Department;
import com.epam.mentoring.department.dao.DepartmentDaoImpl;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final MapperFacade mapperFacade;

    private final EmployeeServiceImpl employeeServiceImpl;

    private final DepartmentDaoImpl departmentDao;

    public DepartmentDto from(Department department) {

        DepartmentDto departmentDto = mapperFacade.map(department, DepartmentDto.class);
        departmentDto.setChefName(employeeServiceImpl.getChefName(departmentDto.getId()));
        departmentDto.setAmountEmployee(Long.valueOf(employeeServiceImpl.getSumEmployees(departmentDto.getId())));

        return departmentDto;
    }

    public List<DepartmentDto> from(List<Department> departments) {

        List<DepartmentDto> dtoList = mapperFacade.mapAsList(departments.toArray(), DepartmentDto.class);
        for (int i = 0; i < dtoList.size(); i++) {
            DepartmentDto dto = dtoList.get(i);
            dto.setChefName(employeeServiceImpl.getChefName(dto.getId()));
            dto.setAmountEmployee(Long.valueOf(employeeServiceImpl.getSumEmployees(dto.getId())));
        }
        return dtoList;
    }

    public Department from(DepartmentDto departmentDto) {
        return mapperFacade.map(departmentDto, Department.class);
    }

    @Override
    public Department create(DepartmentDto dto) {
        Department department = from(dto);
        return departmentDao.createDepartment(department.getName(), department.getCreateDate(), department.getUpperDepartment());
    }

    @Override
    public Department update(DepartmentDto dto, Long id) {
        return departmentDao.updateDepartment(id.intValue(), dto.getName(), dto.getCreateDate(), dto.getUpperDepartment());
    }

    @Override
    public Department update(Long id, Long idUp) {
        Department department = get(id);
        department.setUpperDepartment(idUp);
        return departmentDao.updateDepartment(id.intValue(), department.getName(), department.getCreateDate(), department.getUpperDepartment());
    }

    @Override
    public List<Department> get() {
        return departmentDao.findAll();
    }

    @Override
    public Department get(Long id) {
        return departmentDao.findDepartmentById(id);
    }

    @Override
    public void delete(Long id) {
        departmentDao.removeDepartment(id.intValue());
    }

    @Override
    public List<Department> getLower(Long id) {
        return departmentDao.findDepartmentsByUpperDepartment(id);
    }

    @Override
    public List<Department> getAllLowerDep(Long id) {

        List<Department> departmentList = new ArrayList<>();
        return getLowers(departmentList, id);
    }

    @Override
    public List<Department> getAllUpperDep(Long id) {

        List<Department> departmentList = new ArrayList<>();
        return getUppers(departmentList, id);
    }

    @Override
    public List<Department> getLowers(List<Department> departmentList, Long id) {

        if (getLower(id) != null) {
            for (Department department : getLower(id)) {
                departmentList.add(department);
                getLowers(departmentList, department.getId());
            }
        } else {
            departmentList.add(get(id));
        }
        return departmentList;
    }

    @Override
    public List<Department> getUppers(List<Department> departmentList, Long id) {

        Department department = departmentDao
                .findDepartmentById(departmentDao.findDepartmentById(id).getUpperDepartment());

        if (department != null) {
            departmentList.add(department);
            getUppers(departmentList, department.getId());

            return departmentList;
        }
        return departmentList;
    }

    @Override
    public Department getName(String name) {
        return departmentDao.findDepartmentByName(name);
    }
}
