package com.springboot.poc.demo.springboot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;
import java.util.function.Supplier;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private Supplier<String> getUUID = () -> "/uuid:" + UUID.randomUUID().toString();

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ProblemDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ProblemDetails error;
        HttpStatus status;
        if (ex instanceof PDException) {
            PDException pdex = (PDException) ex;
            ProblemDetails problemDetail = pdex.getProblemDetails();
            status = HttpStatus.valueOf(problemDetail.getStatus());
            error = problemDetail;
        } else {
            error = new ProblemDetails("Internal Server Error", 500, "Internal processing error", getUUID.get());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(error, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ProblemDetails error = error = new ProblemDetails("Internal Server Error",
                400,
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                getUUID.get());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
