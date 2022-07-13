package com.example.kotlinspringbootboard.handler

import com.example.kotlinspringbootboard.response.ApiResponse
import com.example.kotlinspringbootboard.response.ApiResponseType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AccessDeniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        ApiResponse.error(response, ApiResponseType.FORBIDDEN_RESPONSE)
    }

}