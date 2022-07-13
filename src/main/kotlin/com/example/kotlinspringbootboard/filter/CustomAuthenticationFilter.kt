package com.example.kotlinspringbootboard.filter

import com.example.kotlinspringbootboard.dto.UserDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter() {

    init {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authRequest: UsernamePasswordAuthenticationToken
        println("CustomAuthenticationFilter")

        try {
            val userDto: UserDto = ObjectMapper().readValue(request.inputStream, UserDto::class.java)
            val username = userDto.email
            val password = userDto.password

            authRequest = UsernamePasswordAuthenticationToken(username, password)
        } catch (e: IOException) {
            throw RuntimeException("토큰 발급 실패 $e")
        }
        setDetails(request, authRequest)

        return this.authenticationManager.authenticate(authRequest)
    }
}