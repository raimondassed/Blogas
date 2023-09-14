package com.raimis.Blogas.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ModelAndView handleWhateverException(Exception exception, Model model) {
        model.addAttribute("errorId", UUID.randomUUID().toString());
        model.addAttribute("message", exception.getMessage());
        return new ModelAndView("customError", model.asMap());
    }
}
