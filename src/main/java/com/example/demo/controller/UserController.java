package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;

import com.example.demo.exception.ExceptionType;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.validation.UpdateValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home() {
        return "home";
    }


    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") @Valid User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("register");
        if (result.hasErrors()) {
            return modelAndView;
        }
        try {
            userService.save(user);
            modelAndView.setView(new RedirectView("login",true));
            return modelAndView;
        } catch (RuntimeException e) {
            if (e instanceof DataIntegrityViolationException) {
                return GlobalExceptionHandler.handleDuplicateException((DataIntegrityViolationException) e, modelAndView, "Email had been used");
            }
            return GlobalExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/account")
    public ModelAndView account(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("account");
        String email = principal.getName();
        User user = userService.findByEmail(email);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/account")
    public ModelAndView account(@Validated(UpdateValidationGroup.class) @ModelAttribute("user") @Valid User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("account");
        if (result.hasErrors()) {
            return modelAndView;
        }
        try {
            userService.update(user);
            modelAndView.addObject("updated", true);
            return modelAndView;
        } catch (RuntimeException e) {
            modelAndView.addObject("updated", false);
            if (e instanceof DataIntegrityViolationException) {
                return GlobalExceptionHandler.handleDuplicateException((DataIntegrityViolationException) e, modelAndView, "Email had been used");
            }
            return GlobalExceptionHandler.handleException(e);
        }
    }
}
