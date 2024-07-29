package com.edigest.journalApp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
}
