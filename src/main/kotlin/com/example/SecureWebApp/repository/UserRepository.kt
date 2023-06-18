package com.example.SecureWebApp.repository

import com.example.SecureWebApp.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findByLogin(login: String): User?
    fun deleteByLogin(login: String)
}