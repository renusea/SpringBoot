package com.springboot.poc.demo.springboot.helloworld;

import java.util.StringJoiner;

public class HelloWorldBean {
    private String message;


    HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HelloWorldBean.class.getSimpleName() + "[", "]")
                .add("message='" + message + "'")
                .toString();
    }
}
