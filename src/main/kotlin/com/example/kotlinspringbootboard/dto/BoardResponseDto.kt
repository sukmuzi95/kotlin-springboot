package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Board
import java.time.LocalDateTime

class BoardResponseDto(

) {
    var id: Long? = null
    var title: String? = null
    var content: String? = null
    var readCnt: Int? = null
    var registerId: String? = null
    var registerTime: LocalDateTime? = null

    constructor(entity: Board) : this() {
        this.id = entity.id
        this.title = entity.title
        this.content = entity.content
        this.readCnt = entity.readCnt
        this.registerId = entity.registerId
        this.registerTime = entity.registerTime
    }
}