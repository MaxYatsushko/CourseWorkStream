package com.example.courseworkstream.exception;

public class EmployeeNotFoundException extends EmployeeException {
    public EmployeeNotFoundException(String message, int errorCode) {
        super(message, errorCode);
    }
}
