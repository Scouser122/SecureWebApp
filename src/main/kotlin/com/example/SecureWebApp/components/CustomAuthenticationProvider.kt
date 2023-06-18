package com.example.SecureWebApp.components

import com.example.SecureWebApp.interfaces.UserService
import com.example.SecureWebApp.repository.UserRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList

@Component
class CustomAuthenticationProvider(
    private val userService: UserService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val name = authentication?.name ?: ""
        val pass = authentication?.credentials?.toString() ?: ""

        val user = userService.getUser(name) ?: throw UsernameNotFoundException("Unknown user $name")

        val decodedPass = try {
            Base64.getDecoder().decode(user.password?.joinToString(""))
        } catch (ex: Exception) {
            throw BadCredentialsException("Password data corrupted")
        }

        if (!decodedPass.contentEquals(pass.toByteArray())) {
            throw BadCredentialsException("Incorrect password")
        }

        val principal = User.builder()
            .username(name)
            .password(String(decodedPass))
            .roles(user.role)
            .build()

        return UsernamePasswordAuthenticationToken(
            principal, String(decodedPass), principal.authorities
        )
    }

    override fun supports(authentication: Class<*>?): Boolean {
        val result = authentication?.equals(UsernamePasswordAuthenticationToken::class.java)
        return result ?: false
    }
}