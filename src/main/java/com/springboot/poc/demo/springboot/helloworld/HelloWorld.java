package com.springboot.poc.demo.springboot.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorld {


    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World at >> "+ LocalDateTime.now());
    }

    @GetMapping(path = "/hello-bean/name/{name}")
    public HelloWorldBean helloWorldPathVariableName(@PathVariable String name){
        return new HelloWorldBean("Hello "+ name);
    }

    @GetMapping(path = "/hello-bean/{id}")
    public HelloWorldBean helloWorldPathVariableName(@PathVariable int id){
        return new HelloWorldBean("Hello "+ id);
    }
}
