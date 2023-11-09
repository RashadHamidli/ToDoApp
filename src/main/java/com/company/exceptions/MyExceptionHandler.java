package com.company.exceptions;

public class MyExceptionHandler extends RuntimeException {

    public MyExceptionHandler() {
        super();
    }

    public MyExceptionHandler(String message) {
        super(message);
    }
}
