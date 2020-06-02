package com.epam.mentoring.department.controller;

import com.epam.mentoring.department.dto.DepartmentDto;
import com.epam.mentoring.department.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/departments")
@RestController
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentControllerImpl(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public List<DepartmentDto> get() {
        return departmentService.from(departmentService.get());
    }

    @Override
    public DepartmentDto get(Long id) {
        return departmentService.from(departmentService.get(id));
    }

    @Override
    public DepartmentDto create(DepartmentDto dto) {
        return departmentService.from(departmentService.create(dto));
    }

    @Override
    public DepartmentDto update(DepartmentDto dto, Long id) {
        return departmentService.from(departmentService.update(dto, id));
    }

    @Override
    public DepartmentDto updateUpperDepartment(Long id, Long idUp) {
        return departmentService.from(departmentService.update(id, idUp));
    }

    @Override
    public void delete(Long id) {
        departmentService.delete(id);
    }

    @Override
    public List<DepartmentDto> getLower(Long id) {
        return departmentService.from(departmentService.getLower(id));
    }

    @Override
    public List<DepartmentDto> getLowers(Long id) {
        return departmentService.from(departmentService.getAllLowerDep(id));
    }

    @Override
    public List<DepartmentDto> getUppers(Long id) {
        return departmentService.from(departmentService.getAllUpperDep(id));
    }

    @Override
    public DepartmentDto getByName(String name) {
        return departmentService.from(departmentService.getName(name));
    }
}


