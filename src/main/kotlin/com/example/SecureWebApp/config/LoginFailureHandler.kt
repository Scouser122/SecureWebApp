package com.example.SecureWebApp.config

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.ui.Model
import java.io.IOException


@Component
class LoginFailureHandler() : SimpleUrlAuthenticationFailureHandler() {
    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(
        request: HttpServletRequest, response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        val authException: AuthenticationException? = exception
        val url = StringBuilder()
        url.append("/login?")
        val username: String? = request.getParameter("username")
        if (username != null) {
            url.append("username=").append(request.getParameter("username")).append("&")
        }
        url.append("error=${authException?.message}")
        super.setDefaultFailureUrl(url.toString())
        super.onAuthenticationFailure(request, response, exception)
    }
}