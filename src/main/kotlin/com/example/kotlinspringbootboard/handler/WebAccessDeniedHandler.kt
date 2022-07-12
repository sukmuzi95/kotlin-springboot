package com.example.kotlinspringbootboard.handler

import com.example.kotlinspringbootboard.exception.ErrorCode
import org.json.simple.JSONObject
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class WebAccessDeniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val errorCode = ErrorCode.ForbiddenException

        response.contentType = "application/json;charset=UTF-8"
        response.characterEncoding = "utf-8"
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        var json = JSONObject()
        json["code"] = errorCode.code
        json["message"] = errorCode.message
        response.writer.print(json)
    }

}