package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.UserDto
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun save(userDto: UserDto): User {
        var encoder = BCryptPasswordEncoder()
        userDto.userPw = encoder.encode(userDto.userPw)

        return userRepository.save(userDto.toEntity(userDto))
    }

    fun findByUserId(userId: String): Optional<User> {
        return userRepository.findByUserId(userId)
    }
}