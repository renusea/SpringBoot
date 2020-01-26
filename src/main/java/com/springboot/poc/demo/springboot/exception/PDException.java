package com.springboot.poc.demo.springboot.exception;

import java.util.UUID;

public class PDException extends Exception{

    private ProblemDetails problemDetails;

    PDException(){
    }

    public PDException(String title, int status, String description){
        this.problemDetails = new ProblemDetails(title, status, description, "uuid:"+ UUID.randomUUID().toString());
    }

    public PDException(ProblemDetails problemDetails) {
        this.problemDetails = problemDetails;
    }

    public ProblemDetails getProblemDetails() {
        return problemDetails;
    }

    public void setProblemDetails(ProblemDetails problemDetails) {
        this.problemDetails = problemDetails;
    }
}
