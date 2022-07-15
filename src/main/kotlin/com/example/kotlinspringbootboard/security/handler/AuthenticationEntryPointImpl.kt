package com.example.kotlinspringbootboard.security.handler

import com.example.kotlinspringbootboard.common.ApiResponse
import com.example.kotlinspringbootboard.common.ApiResponseType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationEntryPointImpl : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
//        println("AuthenticationEntryPointImpl")
//        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED_RESPONSE)
        response.sendRedirect("/login")
    }
}