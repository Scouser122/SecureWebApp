package com.example.SecureWebApp.controllers

import com.example.SecureWebApp.config.LoginSuccessHandler
import com.example.SecureWebApp.model.Roles
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal

@Controller
class HomeController(
    private val loginSuccessHandler: LoginSuccessHandler
) {
    @GetMapping("/")
    fun getStartPage(principal: Principal?, model: Model): String {
        if (principal != null) {
            model.addAttribute("userRole", loginSuccessHandler.getUserRole(principal))
        }
        return "home"
    }

    @GetMapping("/home")
    fun getHomePage(): String {
        return "home"
    }
}