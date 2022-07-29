package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.jwt.JwtFilter
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

@Controller
class PageController {

    @GetMapping("/board/write")
    fun write(authentication: Authentication?, model: Model): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
            model.addAttribute("username", authentication.name)

            "/board/write"
        }
    }

    @GetMapping("/")
    fun root(authentication: Authentication?): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
            "main"
        }
    }

    @GetMapping("/main")
    fun index(authentication: Authentication?, request: HttpServletRequest): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
//            val token = Arrays.stream(request.cookies).filter {
//                it.name == JwtFilter.AUTHORIZATION_HEADER
//            }.findFirst().map(Cookie::getValue).orElse(null)

            "main"
        }
    }

    @GetMapping("/login")
    fun login(): String {
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

    @GetMapping("/profile")
    fun profile(): String {
        return "/user/profile"
    }
}