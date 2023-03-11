package com.example.courseworkstream.service;

import com.example.courseworkstream.Employee;
import com.example.courseworkstream.exception.EmployeeAlreadyAddedException;
import com.example.courseworkstream.exception.EmployeeStorageIsFullException;
import com.example.courseworkstream.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.example.courseworkstream.Department.DEPARTMENT_BY_ID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int MAX_EMPLOYEES_COUNT = 10;

    private static final List<Employee> employees = new ArrayList<>();

    static {
        Employee workerBuh = new Employee("Инна", "Иванова", 70000f, DEPARTMENT_BY_ID.get(0));
        Employee workerBuh2 = new Employee("Марья", "Петрова", 96500f, DEPARTMENT_BY_ID.get(0));

        Employee workerIt1 = new Employee("Иван", "Петров", 200_000f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt2 = new Employee("Крирл", "Петров", 180_500f, DEPARTMENT_BY_ID.get(1));
        Employee workerIt3 = new Employee("Инга", "Бергман", 80_000f, DEPARTMENT_BY_ID.get(1));

        Employee workerGlobal1 = new Employee("Артем", "Дзюба", 150_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal2 = new Employee("Далер", "Кузяев", 120_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal3 = new Employee("Ласина", "Траоре", 30_000, DEPARTMENT_BY_ID.get(2));
        Employee workerGlobal4 = new Employee("Сейду", "Думбия", 150_000, DEPARTMENT_BY_ID.get(2));

        employees.add(workerBuh);
        employees.add(workerBuh2);
        employees.add(workerIt1);
        employees.add(workerIt2);
        employees.add(workerIt3);
        employees.add(workerGlobal1);
        employees.add(workerGlobal2);
        employees.add(workerGlobal1);
        employees.add(workerGlobal2);
    }

    @Override
    public Employee add(String firstName, String lastName, float salary, int departmentId) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен", 777);
        }

        Employee employee = new Employee(firstName, lastName, salary, DEPARTMENT_BY_ID.get(departmentId));
        if (employees.contains(employee))
            throw new EmployeeAlreadyAddedException("В массиве уже есть такой сотрудник", 888);

        employees.add(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = null;

        for (Employee e : employees)
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
                break;
            }

        if (employee == null)
                throw new EmployeeNotFoundException("Сотрудник не найден", 999);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
       Employee employee = find(firstName, lastName);

       for (Employee e : employees)
           if (e.equals(employee))
               return e;

       return employee;
    }

    @Override
    public List<Employee> getAll() {
       return employees;
    }
}
