package com.example.courseworkstream.controller;

import com.example.courseworkstream.exception.DepartmentSearchException;
import com.example.courseworkstream.service.DepartmentService;
import com.example.courseworkstream.service.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.courseworkstream.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentServiceImpl = departmentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentSearchException.class)
    public String handleException(DepartmentSearchException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(path = "/all")
    public Map<String, List<Employee>> allByDepartmentId(@RequestParam(required = false) Integer departmentId) {
        return departmentServiceImpl.getAll(departmentId);
    }
}