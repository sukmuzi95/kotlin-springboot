package com.example.kotlinspringbootboard.jwt

import com.example.kotlinspringbootboard.response.ApiResponse
import com.example.kotlinspringbootboard.response.ApiResponseType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        println("JwtAuthenticationEntryPoint")
        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED_RESPONSE)
    }
}