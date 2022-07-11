package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Board

class BoardRequestDto {
    var no: Long? = null
    var title: String? = null
    var content: String? = null
    var registerId: String? = null

    fun toEntity(boardRequestDto: BoardRequestDto): Board {
        return Board().apply {
            this.boardTitle = boardRequestDto.title
            this.boardContent = boardRequestDto.content
            this.registerId = boardRequestDto.registerId
        }
    }
}