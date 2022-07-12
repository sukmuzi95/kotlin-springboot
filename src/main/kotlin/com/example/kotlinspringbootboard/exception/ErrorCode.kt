package com.example.kotlinspringbootboard.exception

import org.springframework.http.HttpStatus

enum class ErrorCode() {

    UsernameOrPasswordNotFoundException(400, "아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ForbiddenException(403, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UnAuthorizedException(401, "로그인 후 이용가능합니다.", HttpStatus.UNAUTHORIZED),
    ExpiredJwtException(444, "기존 토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    ReLogin(445, "토큰이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED);

    var code: Int = 0
    lateinit var message: String
    lateinit var httpStatus: HttpStatus

    constructor(code: Int, message: String, httpStatus: HttpStatus) : this() {
        this.code = code;
        this.message = message
        this.httpStatus = httpStatus
    }
}