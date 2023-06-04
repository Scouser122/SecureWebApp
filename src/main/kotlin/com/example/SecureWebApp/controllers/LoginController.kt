package com.example.SecureWebApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun getLogin(): String {
        return "login"
    }
}