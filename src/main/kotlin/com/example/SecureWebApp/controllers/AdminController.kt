package com.example.SecureWebApp.controllers

import com.example.SecureWebApp.interfaces.UserService
import com.example.SecureWebApp.model.Roles
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import kotlin.math.log

@Controller
class AdminController(
    private val userService: UserService
) {
    @GetMapping("/admin")
    fun getAdminPage(model: Model): String {
        fillModel(model)
        return "admin"
    }

    @PostMapping("/add_user")
    fun addUser(
        @RequestParam username: String,
        @RequestParam password: String,
        @RequestParam role: String,
        model: Model
    ): String {
        try {
            userService.addUser(login = username, password, role)
            model.addAttribute("message", "User with login '${username}' added successfully")
        } catch (ex: Exception) {
            model.addAttribute("error", "Error while adding user: ${ex.message}")
        }
        fillModel(model)
        return "admin"
    }

    @GetMapping("/delete_user")
    fun deleteUser(@RequestParam login: String, model: Model): String {
        try {
            userService.deleteUser(login)
            model.addAttribute("message", "User with login '${login}' deleted successfully")
        } catch (ex: Exception) {
            model.addAttribute("error", "Error while deleting user: ${ex.message}")
        }
        fillModel(model)
        return "admin"
    }

    private fun fillModel(model: Model) {
        model.addAttribute("users", userService.getAllUsers())
        model.addAttribute("roles", Roles.values())
        model.addAttribute("defaultRole", Roles.USER.name)
    }
}