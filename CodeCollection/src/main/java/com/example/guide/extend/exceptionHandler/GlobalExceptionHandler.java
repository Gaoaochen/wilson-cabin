package com.example.guide.extend.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler({NullPointerException.class})
    public void handleException(NullPointerException nullPointerException){
        System.out.println("chuli");
    }
}
