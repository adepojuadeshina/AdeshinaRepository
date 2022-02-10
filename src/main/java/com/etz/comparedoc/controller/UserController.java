package com.etz.comparedoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @RequestMapping("/adminlogin")
    public String adminValidation() {
        return "Admin user, Successfully logged in!";
    }


}


