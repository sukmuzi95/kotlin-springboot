package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Board

data class BoardRequestDto(
    var id: Long? = null,
    var title: String? = null,
    var content: String? = null,
    var registerId: String? = null
) {
    fun toEntity(): Board {
        return Board().apply {
            this.title = title
        }
    }
}