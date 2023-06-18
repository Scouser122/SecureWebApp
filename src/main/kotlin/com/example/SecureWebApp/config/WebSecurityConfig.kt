package com.example.SecureWebApp.config

import com.example.SecureWebApp.components.CustomAuthenticationProvider
import com.example.SecureWebApp.model.Roles
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val authProvider: CustomAuthenticationProvider,
    private val loginFailureHandler: LoginFailureHandler,
    private val loginSuccessHandler: LoginSuccessHandler
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/", "/home").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers("/admin").hasAnyRole(Roles.ADMIN.name)
                    .requestMatchers("/user").hasAnyRole(Roles.USER.name, Roles.ADMIN.name)
                    .anyRequest().authenticated()
            }
            .formLogin { formLogin ->
                formLogin
                    .loginPage("/login")
                    .failureHandler(loginFailureHandler)
                    .successHandler(loginSuccessHandler)
                    .permitAll()
            }
            .logout { logout -> logout.permitAll() }
        return http.build()
    }

    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.authenticationProvider(authProvider)
        return authenticationManagerBuilder.build()
    }
}