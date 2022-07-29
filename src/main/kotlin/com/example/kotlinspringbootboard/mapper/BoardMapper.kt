package com.example.kotlinspringbootboard.mapper

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import org.apache.ibatis.annotations.Mapper

@Mapper
interface BoardMapper {

    fun insert(boardRequestDto: BoardRequestDto): Int

    fun update(boardRequestDto: BoardRequestDto): Int

    fun findAll(): List<BoardResponseDto>

    fun findById(no: Long): BoardResponseDto

    fun updateReadCount(no: Long): Int

    fun deleteById(no: Long): Int
}