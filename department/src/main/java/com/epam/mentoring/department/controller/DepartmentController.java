package com.epam.mentoring.department.controller;

import com.epam.mentoring.department.dto.DepartmentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface DepartmentController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDto create(@RequestBody DepartmentDto dto);

    @GetMapping
    List<DepartmentDto> get();

    @GetMapping("/{id}")
    DepartmentDto get(@PathVariable("id") Long id);

    @GetMapping("/{id}/lower")
    List<DepartmentDto> getLower(@PathVariable("id") Long id);

    @GetMapping("/{id}/lowers")
    List<DepartmentDto> getLowers(@PathVariable("id") Long id);

    @GetMapping("/{id}/uppers")
    List<DepartmentDto> getUppers(@PathVariable("id") Long id);

    @GetMapping("/{name}/name")
    DepartmentDto getByName(@PathVariable("name") String name);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDto update(@RequestBody DepartmentDto dto, @PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}/upper/{idUp}", consumes = MediaType.APPLICATION_JSON_VALUE)
    DepartmentDto updateUpperDepartment(@PathVariable("id") Long id, @PathVariable("idUp") Long idUp);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}

