package com.lesson2.hw2;

public class HttpExсeption extends Exception {
    private int code;

    public HttpExсeption(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getStatusCode(){
        return this.code;
    }
}
