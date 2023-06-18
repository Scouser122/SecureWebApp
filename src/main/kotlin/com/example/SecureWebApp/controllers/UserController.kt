package com.example.SecureWebApp.controllers

import com.example.SecureWebApp.interfaces.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal

@Controller
class UserController {
    @GetMapping("/user")
    fun getUserPage(principal: Principal, model: Model): String {
        model.addAttribute("username", principal.name)
        return "user"
    }
}