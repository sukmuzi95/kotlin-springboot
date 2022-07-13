package com.example.kotlinspringbootboard.repository

import com.example.kotlinspringbootboard.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    @Modifying
    @Query("UPDATE User SET password = :password where email = :email")
    fun updateUser(@Param("email") email: String, @Param("password") password: String): Int
}