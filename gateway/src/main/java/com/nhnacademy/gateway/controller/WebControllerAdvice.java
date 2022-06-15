package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.exception.NotFoundMemberException;
import com.nhnacademy.gateway.exception.NotFoundProjectException;
import com.nhnacademy.gateway.exception.NotFoundProjectMemberException;
import com.nhnacademy.gateway.exception.NotFoundTaskException;
import com.nhnacademy.gateway.exception.NotProjectAdministratorException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler({NotFoundMemberException.class, NotFoundProjectException.class,
        NotFoundProjectMemberException.class, NotFoundTaskException.class,
        NotProjectAdministratorException.class})
    public String handleException(Exception ex, Model model) {

        model.addAttribute("exception", ex);
        return "error";
    }
}
