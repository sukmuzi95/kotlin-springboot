package com.example.kotlinspringbootboard.security.handler

import com.example.kotlinspringbootboard.common.ApiResponse
import com.example.kotlinspringbootboard.common.ApiResponseType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AccessDeniedHandlerImpl : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        ApiResponse.error(response, ApiResponseType.FORBIDDEN_RESPONSE)
    }

}