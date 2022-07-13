package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.UserDto
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.repository.UserRepository

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.*
import kotlin.random.Random

@Service
class UserService(
    private val userRepository: UserRepository,
    private val javaMailSender: JavaMailSender
) {


    @Transactional
    fun save(user: User) {
        val alreadyUser = user.email.let { userRepository.findByEmail(it) }
        if (alreadyUser.isPresent) {
            throw Exception("email duplicated")
        }

        val encoder = BCryptPasswordEncoder()
        user.password = encoder.encode(user.password)

        userRepository.save(user)
    }

//    @Transactional
//    fun save(userDto: UserDto): User {
//        val encoder = BCryptPasswordEncoder()
//        userDto.userPw = encoder.encode(userDto.userPw)
//
//        return userRepository.save(userDto.toEntity(userDto))
//    }

    @Transactional
    fun update(email: String, password: String): Int {
        val encoder = BCryptPasswordEncoder()
        val password = encoder.encode(password)

        return userRepository.updateUser(email, password)
    }

    fun findByUserEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    fun createMessage(to: String): String {
        val random: Random = Random
        val message: String = random.nextInt(999999).toString()
        val email = SimpleMailMessage()

        email.setSubject("dashboard 인증번호 발송")
        email.setText(message)
        email.setTo(to)

        javaMailSender.send(email)

        return message
    }
}