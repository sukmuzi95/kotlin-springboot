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
        const val UPDATE_BOARD: String = "UPDATE TB_BOARD " +
                "SET TITLE = :#{#boardRequestDto.title}, " +
                "CONTENT = :#{#boardRequestDto.content}, " +
                "UPDATE_TIME = GETDATE() " +
                "WHERE ID = :#{#boardRequestDto.id}"

        const val UPDATE_READCOUNT: String = "UPDATE TB_BOARD " +
                "SET READ_COUNT = READ_COUNT + 1 " +
                "WHERE BOARD_NO = :#{#no}"
    }

    @Transactional
    @Modifying
    @Query(value = UPDATE_READCOUNT, nativeQuery = true)
    fun updateReadCount(no: Long)

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD, nativeQuery = true)
    fun updateBoard(@Param("boardRequestDto") boardRequestDto: BoardRequestDto): Int
}