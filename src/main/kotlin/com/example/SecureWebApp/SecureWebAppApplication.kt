package com.example.SecureWebApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecureWebAppApplication

fun main(args: Array<String>) {
	runApplication<SecureWebAppApplication>(*args)
}
