package com.example.kotlinspringbootboard.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage
import kotlin.random.Random

@Service
class EmailService() {

    @Bean
    fun mailSender(): JavaMailSender {
        return JavaMailSenderImpl()
    }

    fun createMessage(to: String, subject: String): String {
        val javaMailSender: JavaMailSender = mailSender()
        var mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        var helper = MimeMessageHelper(mimeMessage, "utf-8")
        val random: Random = Random

        var message: String = random.nextInt(999999).toString()

        helper.setFrom("dashboard")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(message)

        javaMailSender.send(mimeMessage)

        return message
    }
}