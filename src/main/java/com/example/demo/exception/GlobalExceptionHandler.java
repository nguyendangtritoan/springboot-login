package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public static ModelAndView handleException(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorMessage", "An error occurred: " + ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public static ModelAndView handleDuplicateException(DataIntegrityViolationException e, ModelAndView modelAndView, String message) {
        log.error("Duplicate error: {}", e.getMessage());
        modelAndView.addObject("exception", message);
        return modelAndView;
    }
}
