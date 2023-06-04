package com.example.SecureWebApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {
    @GetMapping("/hello")
    fun getHello(): String {
        return "hello"
    }
}