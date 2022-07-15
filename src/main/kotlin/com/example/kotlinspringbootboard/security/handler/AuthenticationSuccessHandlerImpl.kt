package com.example.kotlinspringbootboard.security.handler

import com.example.kotlinspringbootboard.jwt.JwtProvider
import com.example.kotlinspringbootboard.common.ApiResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationSuccessHandlerImpl(private val jwtProvider: JwtProvider) :
    AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        println("AuthSuccessHandler")
        SecurityContextHolder.getContext().authentication = authentication
        val token = jwtProvider.generateToken(authentication)

//        response.addHeader("Authorization", "Bearer $token")
//        println(response.getHeader("Authorization"))
        ApiResponse.token(response, token)
        val cookie = Cookie("Authorization", "Bearer$token")
        println(cookie.value)
        println(cookie.name)
        cookie.maxAge = JwtProvider.ACCESS_EXPIRE_TIME
        cookie.path = "/"
        response.addCookie(cookie)
    }
}