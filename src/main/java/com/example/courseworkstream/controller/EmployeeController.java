package com.example.courseworkstream.controller;

import com.example.courseworkstream.Employee;
import com.example.courseworkstream.exception.EmployeeAlreadyAddedException;
import com.example.courseworkstream.exception.EmployeeNotFoundException;
import com.example.courseworkstream.exception.EmployeeStorageIsFullException;
import com.example.courseworkstream.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @RestController
 @RequestMapping("/employee")
 public class EmployeeController {

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(EmployeeStorageIsFullException.class)
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

     private final EmployeeService employeeService;

     public EmployeeController(EmployeeService employeeService) {
         this.employeeService = employeeService;
     }

     @GetMapping(path = "/add")
     public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
         return employeeService.add(firstName, lastName);
     }

     @GetMapping(path = "/find")
     public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
         return employeeService.find(firstName, lastName);
     }

     @GetMapping(path = "/remove")
     public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
         return employeeService.remove(firstName, lastName);
     }

     @GetMapping(path = "/findAll")
     public List<Employee> getEmployees() {
         return employeeService.getAll();
     }
}
