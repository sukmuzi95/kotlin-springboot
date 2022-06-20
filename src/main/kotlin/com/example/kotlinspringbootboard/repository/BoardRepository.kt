package com.example.kotlinspringbootboard.repository

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface BoardRepository : JpaRepository<Board, Long> {

    companion object{
        const val UPDATE_BOARD: String = "UPDATE BOARD " +
                "SET TILE = :#{#boardRequestDto.title}, " +
                "CONTENT = :#{#boardRequestDto.content}, " +
                "UPDATE_TIME = NOW() " +
                "WHERE ID = :#{#boardRequestDto.id}"
    }

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD, nativeQuery = true)
    fun updateBoard(@Param("boardRequestDto") boardRequestDto: BoardRequestDto): Int
}