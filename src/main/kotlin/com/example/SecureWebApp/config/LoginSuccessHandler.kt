package com.example.SecureWebApp.config

import com.example.SecureWebApp.model.Roles
import com.example.SecureWebApp.model.User
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import java.security.Principal


@Component
class LoginSuccessHandler() : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse?,
        authentication: Authentication
    ) {
        val pagePath = choosePageForUser(authentication.principal)
        super.setDefaultTargetUrl(pagePath)
        super.onAuthenticationSuccess(request, response, authentication)
    }

    fun choosePageForUser(principal: Any): String {
        val userDetails = if (principal is UsernamePasswordAuthenticationToken) {
            principal.principal as UserDetails
        } else {
            principal as UserDetails
        }
        return if (userDetails.authorities.contains(SimpleGrantedAuthority("ROLE_ADMIN"))) {
            "/admin"
        } else {
            "/user"
        }
    }

    fun getUserRole(principal: Principal): String {
        val userDetails = if (principal is UsernamePasswordAuthenticationToken) {
            principal.principal as UserDetails
        } else {
            principal as UserDetails
        }
        return if (userDetails.authorities.contains(SimpleGrantedAuthority("ROLE_ADMIN"))) {
            Roles.ADMIN.name
        } else {
            Roles.USER.name
        }
    }
}