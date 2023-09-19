package com.raimis.Blogas.controller;

import com.raimis.Blogas.entities.TestUserValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class TestUserValidationController {

    @GetMapping("/add")
    public String showAddUserForm(TestUserValidation user) {
        return "user/addUser";
    }

    @PostMapping("/add")
    public String addUser(@Valid TestUserValidation user, BindingResult result, Model model) {


        if (result.hasErrors()) {
            return "user/addUser";
        }

        return "user/home";
    }

}
