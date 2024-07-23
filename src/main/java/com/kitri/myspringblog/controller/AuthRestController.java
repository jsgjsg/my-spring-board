package com.kitri.myspringblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @GetMapping("/admin")
    public String admin() {
        return "admin 페이지";
    }

    @GetMapping("/user")
    public String user() {
        return "user 페이지";
    }

    @GetMapping("/any")
    public String any() {
        return "any 페이지";
    }

}
