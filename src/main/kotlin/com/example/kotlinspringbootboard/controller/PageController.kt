package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.dto.CustomUserDetails
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class PageController {

    @GetMapping("/board/write")
    fun write(): String {
        return "board/write"
    }

    @GetMapping("/")
    fun root(model: Model, authentication: Authentication?): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
            val user: CustomUserDetails = authentication.principal as CustomUserDetails
            model.addAttribute("user", user)

            "index"
        }
    }

    @GetMapping("/index")
    fun index(model: Model, authentication: Authentication?): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
            val user: CustomUserDetails = authentication.principal as CustomUserDetails
            model.addAttribute("user", user)

            "index"
        }
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

    @GetMapping("/forgot-password")
    fun forgotPasswordForm(): String {
        return "/user/forgot-password"
    }

    @GetMapping("/update-password/{email}")
    fun updatePasswordForm(model: Model, @PathVariable email: String): String {
        model.addAttribute("email", email)

        return "/user/update-password"
    }

    @GetMapping("/login/kakao")
    fun kakaoLogin(): String {
        val REST_API_KEY: String = "90b37669abce7ae3fe1d94c0cd666d30"
        val REDIRECT_URI: String = "http://localhost:1234/app/kakao"

        return "kauth.kakao.com/oauth/authorize?client_id=$REST_API_KEY&redirect_uri=$REDIRECT_URI&response_type=code"
    }

    @RequestMapping("/app/kakao")
    @ResponseBody
    fun kakaoCallback(@RequestParam code: String) {
        println("code : $code")
    }
}