package com.example.kotlinspringbootboard.security.handler

import com.example.kotlinspringbootboard.common.ApiResponse
import com.example.kotlinspringbootboard.common.ApiResponseType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFailureHandlerImpl : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        println("AuthFailureHandler")
        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED_RESPONSE)
    }
}