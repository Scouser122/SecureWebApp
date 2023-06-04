package com.example.SecureWebApp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun getStart(): String {
        return "home"
    }

    @GetMapping("/home")
    fun getHome(): String {
        return "home"
    }
}