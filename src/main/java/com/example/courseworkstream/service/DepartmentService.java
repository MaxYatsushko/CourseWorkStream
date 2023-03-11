package com.example.courseworkstream.service;

import com.example.courseworkstream.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalary(int departmentId);
    Employee getEmployeeWithMaxSalary(int departmentId);
    Map<String, List<Employee>> getAll(Integer departmentId);
}
