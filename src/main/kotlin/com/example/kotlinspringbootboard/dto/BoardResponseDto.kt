package com.example.kotlinspringbootboard.dto

import java.time.LocalDateTime

class BoardResponseDto {

    var boardNo: Long? = null
    var boardTitle: String? = null
    var boardContent: String? = null
    var readCount: Int? = null
    var registerId: String? = null
    var registerTime: LocalDateTime? = null
    var updateTime: LocalDateTime? = null
}