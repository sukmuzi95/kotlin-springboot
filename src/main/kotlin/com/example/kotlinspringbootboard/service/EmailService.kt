package com.example.kotlinspringbootboard.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmailService(@Autowired private val javaMailSender: JavaMail) {
}