package com.example.courseworkstream.controller;

import com.example.courseworkstream.Employee;
import com.example.courseworkstream.exception.EmployeeAlreadyAddedException;
import com.example.courseworkstream.exception.EmployeeNotFoundException;
import com.example.courseworkstream.exception.EmployeeStorageIsFullException;
import com.example.courseworkstream.exception.InvalidStringDataException;
import com.example.courseworkstream.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @RestController
 @RequestMapping("/employee")
 public class EmployeeController {

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler({EmployeeStorageIsFullException.class})
     public String handleException(EmployeeStorageIsFullException e) {
         return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
     }
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(EmployeeAlreadyAddedException.class)
     public String handleException(EmployeeAlreadyAddedException e) {
         return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
     }
     @ResponseStatus(HttpStatus.NOT_FOUND)
     @ExceptionHandler(EmployeeNotFoundException.class)
     public String handleException(EmployeeNotFoundException e) {
         return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
     }

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(InvalidStringDataException.class)
     public String handleException(InvalidStringDataException e) {
         return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
     }

     private final EmployeeServiceImpl employeeServiceImpl;

     public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
         this.employeeServiceImpl = employeeServiceImpl;
     }

     @GetMapping(path = "/add")
     public Employee addEmployee(
             @RequestParam (name = "firstName") String firstName,
             @RequestParam (name = "lastName") String lastName,
             @RequestParam (name = "salary") float salary,
             @RequestParam (name = "departmentId") int departmentId) {
         return employeeServiceImpl.add(firstName, lastName, salary, departmentId);
     }

     @GetMapping(path = "/find")
     public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
         return employeeServiceImpl.find(firstName, lastName);
     }

     @GetMapping(path = "/remove")
     public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
         return employeeServiceImpl.remove(firstName, lastName);
     }

     @GetMapping(path = "/findAll")
     public List<Employee> getEmployees() {
         return employeeServiceImpl.getAll();
     }
}
