package com.springboot.poc.demo.springboot.exception;

public class ProblemDetails {

    private String title;
    private int status;
    private String description;
    private String instance;

    ProblemDetails(){
    }

    public ProblemDetails(String title, int status, String description, String instance) {
        this.title = title;
        this.status = status;
        this.description = description;
        this.instance = instance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}
