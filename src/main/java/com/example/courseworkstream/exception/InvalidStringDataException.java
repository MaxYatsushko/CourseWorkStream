package com.example.courseworkstream.exception;

public class InvalidStringDataException extends EmployeeException {
    public InvalidStringDataException(String message, int errorCode) {
        super(message, errorCode);
    }
}
