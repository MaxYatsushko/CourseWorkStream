package com.example.courseworkstream.service;

import com.example.courseworkstream.Employee;
import com.example.courseworkstream.exception.DepartmentSearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeServiceImpl = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(e -> e.getSalary()))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден", 555));
    }

    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        return employeeServiceImpl.getAll().stream()
                .filter(employee -> departmentId == null || employee.getDepartment().getId() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(e -> e, Collectors.toList()))
                );
    }
}