package com.example.kotlinspringbootboard.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageController {

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write.html"
    }

    @GetMapping("/")
    fun root(): String {
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun login(): String {
        return "user/login"
    }

    @GetMapping("/signup")
    fun signupForm(): String {
        return "user/signup"
    }
}