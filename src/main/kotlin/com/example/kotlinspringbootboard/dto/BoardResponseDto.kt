package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Board
import java.time.LocalDateTime

data class BoardResponseDto(
    var id: Long? = null,
    var title: String? = null,
    var content: String? = null,
    var readCnt: Int? = null,
    var registerId: String? = null,
    var registerTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null
) {
    constructor(entity: Board) : this() {
        this.id = entity.boardNo
        this.title = entity.boardTitle
        this.content = entity.boardContent
        this.readCnt = entity.readCount
        this.registerId = entity.registerId
        this.registerTime = entity.registerTime
        this.updateTime = entity.updateTime
    }
}

//fun toResponse(board: Board): BoardResponseDto {
//    return BoardResponseDto().apply {
//        this.id = board.id
//        this.title = board.title
//        this.content = board.content
//        this.readCnt = board.readCnt
//        this.registerId = board.registerId
//        this.registerTime = board.registerTime
//        this.updateTime = board.updateTime
//    }
//}