package com.example.kotlinspringbootboard.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class EmailService {

    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    @Bean
    fun mailSender(): JavaMailSenderImpl {
        var mailSender = JavaMailSenderImpl()

        mailSender.protocol = "smtp"
        mailSender.host = "127.0.0.1"
        mailSender.port = 587

        return mailSender
    }

    fun createMessage(to: String, subject: String): String {
        val random: Random = Random
        var message: String = random.nextInt(999999).toString()
        var email = SimpleMailMessage()

        email.setSubject("dashboard 인증번호 발송")
        email.setText(message)
        email.setTo(to)

        javaMailSender.send(email)

        return message
    }
}