package com.example.SecureWebApp.interfaces

import com.example.SecureWebApp.model.User

interface UserService {
    fun getUser(login: String): User?

    /**
     * Добавление пользователя в БД
     * @throws IllegalArgumentException
     * @throws OptimisticLockingFailureException
     */
    fun addUser(login: String, password: String, role: String)

    fun getAllUsers(): List<User>

    fun deleteUser(login: String)
}