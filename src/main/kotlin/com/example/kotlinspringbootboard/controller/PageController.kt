package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.dto.UserSession
import com.example.kotlinspringbootboard.entity.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpSession

@Controller
class PageController {

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write"
    }

    @GetMapping("/")
    fun root(@AuthenticationPrincipal user: User?): String {
        println(user?.userId)
        if (user == null) {
            return "redirect:/login"
        }
        return "index"
    }

    @GetMapping("/index")
    fun index(model: Model, @AuthenticationPrincipal user: User?): String {
        println(user?.userId)
        if (user == null) {
            return "redirect:/login"
        }

        return "index"
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