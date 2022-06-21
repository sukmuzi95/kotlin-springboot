package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.config.SecurityConfig
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Component("userService")
class UserService(
    @Autowired private val userMapper: UserMapper,
    @Autowired private val securityConfig: SecurityConfig
) : UserDetailsService {

    @Transactional
    fun joinUser(user: User) {
        user.userAuth = "USER"
        user.userPw = securityConfig.passwordEncoder().encode(user.password)

        userMapper.saveUser(user)
    }

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails? {
        return userMapper.getUserAccount(username)
    }
}