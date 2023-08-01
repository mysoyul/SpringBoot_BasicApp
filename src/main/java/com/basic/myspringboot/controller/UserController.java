package com.basic.myspringboot.controller;

import com.basic.myspringboot.repoository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.basic.myspringboot.entity.User;
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User myUser) {  //UserDto  ${userDto} , MyUserVO ${myUserVO}
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users",
                userRepository.findAll());
        return "list-user";
    }

    @GetMapping("/leaf")
    public String leaf(Model model) {
        model.addAttribute("name","basic");
        return "leaf";
    }
}
