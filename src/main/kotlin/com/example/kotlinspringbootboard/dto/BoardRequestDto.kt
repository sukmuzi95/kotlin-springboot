package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Board

data class BoardRequestDto(
    var id: Long? = null,
    var title: String? = null,
    var content: String? = null,
    var registerId: String? = null
)

fun BoardRequestDto.toEntity(boardRequestDto: BoardRequestDto): Board {
    return Board().apply {
        this.boardTitle = boardRequestDto.title
        this.boardContent = boardRequestDto.content
        this.registerId = boardRequestDto.registerId
    }
}