package com.sparta.springbeginnerhomework.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (value = IllegalArgumentException.class)
    public String handleException(Exception e , Model model){
        model.addAttribute("errCode",4);
        model.addAttribute("errCodeMsg",e.getMessage());
        return "signup";
    }
}
