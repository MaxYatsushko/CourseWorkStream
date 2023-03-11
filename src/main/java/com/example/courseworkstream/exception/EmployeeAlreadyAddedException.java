package com.example.courseworkstream.exception;

public class EmployeeAlreadyAddedException extends EmployeeException {
    public EmployeeAlreadyAddedException(String message, int errorCode) {
        super(message, errorCode);
    }
}
