package com.example.kotlinspringbootboard.security

import com.example.kotlinspringbootboard.security.dto.UserDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter() {

    init {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authRequest: UsernamePasswordAuthenticationToken
        val userDto: UserDto
        println("CustomAuthenticationFilter")

        try {
            userDto = ObjectMapper().readValue(request.inputStream, UserDto::class.java)

            authRequest = UsernamePasswordAuthenticationToken(userDto.email, userDto.password)
        } catch (e: IOException) {
            throw RuntimeException("토큰 발급 실패 $e")
        }
        setDetails(request, authRequest)

        return this.authenticationManager.authenticate(authRequest)
    }
}