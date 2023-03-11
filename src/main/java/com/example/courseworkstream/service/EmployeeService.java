package com.example.courseworkstream.service;

import com.example.courseworkstream.Employee;
import com.example.courseworkstream.exception.EmployeeAlreadyAddedException;
import com.example.courseworkstream.exception.EmployeeStorageIsFullException;
import com.example.courseworkstream.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 10;

    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен", 777);
        }

            Employee employee = new Employee(firstName, lastName);

            if (employees.contains(employee)) {
                throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник", 888);
            }

            employees.add(employee);

//        for (int i = 0; i < employees.size(); i++) {
//
//            if (employees[i] != null && employees[i].equals(employee)) {
//                throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник");
//            }
//
//            if (employees[i] == null) {
//                employees[i] = employee;
//                break;
//            }
//        }

            return employee;
        }

        public Employee find(String firstName, String lastName) {
            Employee employee = null;

            for (Employee e : employees) {
                if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                    employee = e;
                }
            }

            if (employee == null) {
                throw new EmployeeNotFoundException("Сотрудник не найден", 999);
            }

            return employee;
        }

        public Employee remove(String firstName, String lastName) {
            Employee employee = find(firstName, lastName);

            for (Employee e : employees) {
                if (e.equals(employee)) {
                    return e;
                }
            }

            return employee;
        }

        public List<Employee> getAll() {
            return employees;
        }
}
