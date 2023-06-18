package com.example.SecureWebApp.services

import com.example.SecureWebApp.interfaces.UserService
import com.example.SecureWebApp.model.User
import com.example.SecureWebApp.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getUser(login: String): User? {
        return userRepository.findByLogin(login)
    }
    override fun addUser(login: String, password: String, role: String) {
        if (userRepository.findByLogin(login) != null) {
            throw Exception("User with login '$login' already exist")
        }
        val user = User()
        user.login = login
        val charset = Charset.forName("UTF-8")
        val encodedPass = Base64.getEncoder().encode(password.toByteArray())
        user.password = charset.decode(ByteBuffer.wrap(encodedPass)).array()
        user.role = role
        userRepository.save(user)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    override fun deleteUser(login: String) {
        if (login == ADMIN_LOGIN) {
            throw Exception("Cannot delete 'admin' user")
        }
        userRepository.deleteByLogin(login)
    }

    companion object {
        const val ADMIN_LOGIN = "admin"
    }
}