package com.example.courseworkstream.exception;

public class EmployeeStorageIsFullException extends EmployeeException {

    public EmployeeStorageIsFullException(String message, int errorCode) {
        super(message, errorCode);
    }
}
