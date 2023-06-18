package com.example.SecureWebApp.controllers

import com.example.SecureWebApp.config.LoginSuccessHandler
import com.example.SecureWebApp.interfaces.UserService
import com.example.SecureWebApp.model.Roles
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal

@Controller
class SignUpController(
    private val userService: UserService,
    private val loginSuccessHandler: LoginSuccessHandler
) {
    @GetMapping("/signup")
    fun getSignUpPage(principal: Principal?): String {
        if (principal != null) {
            return loginSuccessHandler.choosePageForUser(principal)
        }
        return "signup"
    }

    @PostMapping("/signup")
    fun signUp(
        @RequestParam username: String,
        @RequestParam password: String,
        model: Model
    ): String {
        try {
            userService.addUser(username, password, Roles.USER.name)
            model.addAttribute("success", "You have successfully signed up!")
        } catch (ex: Exception) {
            model.addAttribute("error", ex.message)
        }
        return "signup"
    }
}