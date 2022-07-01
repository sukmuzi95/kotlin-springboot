package com.example.kotlinspringbootboard.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class EmailConfig {

    @Value("\${spring.mail.transport.protocol}")
    private val protocol: String = ""

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private val auth: Boolean = true

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    private val starttls: Boolean = true

    @Value("\${spring.mail.host}")
    private val host: String = ""

    @Value("\${spring.mail.port}")
    private val port: Int = 0

    @Value("\${spring.mail.username}")
    private val username: String = ""

    @Value("\${spring.mail.password}")
    private val password: String = ""

    @Bean
    fun mailSender(): JavaMailSender {
        val properties = Properties()
        properties["mail.transport.protocol"] = protocol
        properties["mail.smtp.auth"] = auth
        properties["mail.smtp.starttls.enable"] = starttls

        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.username = username
        mailSender.password = password
        mailSender.port = port
        mailSender.javaMailProperties = properties

        return mailSender
    }
}