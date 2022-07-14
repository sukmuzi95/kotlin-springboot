package com.example.kotlinspringbootboard.common

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import java.util.*
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

open class ApiResponse {

    var code = ApiResponseType.SUCCESS.code
    var msg = ApiResponseType.SUCCESS.message

    constructor(code: Int, message: String) : this() {
        this.code = code
        this.msg = message
    }

    constructor() {

    }

    constructor(message: String) {

    }

    companion object {
        fun error(apiResponseType: ApiResponseType): ApiResponse {
            return ApiResponse(apiResponseType.code, apiResponseType.message)
        }

        fun error(apiResponseType: ApiResponseType, message: String): ApiResponse {
            return ApiResponse(apiResponseType.code, message)
        }

        fun error(apiResponseType: ApiResponseType, replaceStringList: List<ReplaceString>): ApiResponse {
            var message = apiResponseType.message

            for (replaceString in replaceStringList) {
                message = message.replace(replaceString.key, replaceString.value)
            }

            return ApiResponse(apiResponseType.code, message)
        }

        fun error(response: ServletResponse, apiResponseType: ApiResponseType) {
            val objectMapper = ObjectMapper()

            val httpServletResponse: HttpServletResponse = response as HttpServletResponse
            httpServletResponse.contentType = MediaType.APPLICATION_JSON_VALUE
            httpServletResponse.characterEncoding = "UTF-8"
            httpServletResponse.status = apiResponseType.code
            httpServletResponse.writer.write(Objects.requireNonNull(objectMapper.writeValueAsString(error(apiResponseType))))
        }

        fun token(response: ServletResponse, token: String) {
            val objectMapper = ObjectMapper()

            val httpServletResponse: HttpServletResponse = response as HttpServletResponse
            httpServletResponse.contentType = MediaType.APPLICATION_JSON_VALUE
            httpServletResponse.characterEncoding = "UTF-8"
            httpServletResponse.status = ApiResponseType.SUCCESS.code
            httpServletResponse.writer.write(Objects.requireNonNull(objectMapper.writeValueAsString(DataApiResponse<String>(token))))
        }
    }

    class ReplaceString {
        var key: String = ""
        var value: String = ""
    }
}