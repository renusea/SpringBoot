package com.springboot.poc.demo.springboot.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.StringJoiner;

public class User {


    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @Past(message = "birthDate should not be future date")
    private Date birthDate;

    protected User() {}

    User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
