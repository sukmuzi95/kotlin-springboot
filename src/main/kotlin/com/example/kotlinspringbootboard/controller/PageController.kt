package com.example.kotlinspringbootboard.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

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
    fun login(model: Model,
              @RequestParam(value = "error", required = false) error: String?,
              @RequestParam(value = "exception", required = false) exception: String?
    ): String {
        model.addAttribute("error", error)
        model.addAttribute("exception", exception)

        return "/user/login"
    }

    @GetMapping("/signup")
    fun signupForm(): String {
        return "/user/signup"
    }
}