package com.example.kotlinspringbootboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class KotlinSpringbootBoardApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringbootBoardApplication>(*args)
}
