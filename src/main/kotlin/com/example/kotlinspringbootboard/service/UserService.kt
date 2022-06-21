package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.config.SecurityConfig
import com.example.kotlinspringbootboard.dto.UserDto
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.mapper.UserMapper
import com.example.kotlinspringbootboard.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    @Autowired private val userMapper: UserMapper,
    @Autowired private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(userId: String): User? {
        return userRepository.findByUserId(userId)
            .orElseThrow {
                Throwable()
                UsernameNotFoundException("$userId")
            }
    }

    fun save(userDto: UserDto): Long? {
        var encoder = BCryptPasswordEncoder()
        userDto.userPw = encoder.encode(userDto.userPw)

        return userRepository.save(User().apply {
            this.userId = userDto.userId
            this.userPw = userDto.userPw
            this.userName = userDto.userName
            this.userAuth = "USER"
        }).userNo
    }
}