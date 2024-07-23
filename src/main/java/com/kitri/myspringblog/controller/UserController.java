package com.kitri.myspringblog.controller;

import com.kitri.myspringblog.domain.User;
import com.kitri.myspringblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {

        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {

        try {
            userService.signup(user);
            return "redirect:/login";
        }
        catch (Exception e) {
            return "user/signup";
        }

    }

    @GetMapping("/")
    public String home() {
        return "redirect:/board/list";
    }
}