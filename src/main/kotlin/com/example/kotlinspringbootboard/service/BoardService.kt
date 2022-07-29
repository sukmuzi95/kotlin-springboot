package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import com.example.kotlinspringbootboard.mapper.BoardMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(private val boardMapper: BoardMapper) {

    @Transactional
    fun insert(boardRequestDto: BoardRequestDto): Int {
        return boardMapper.insert(boardRequestDto)
    }

    @Transactional
    fun update(boardRequestDto: BoardRequestDto): Int {
        return boardMapper.update(boardRequestDto)
    }

    @Transactional
    fun findAll(): List<BoardResponseDto> {
        return boardMapper.findAll()
    }

    @Transactional
    fun findById(no: Long): BoardResponseDto {
        return boardMapper.findById(no)
    }

    @Transactional
    fun deleteById(id: Long) {
        boardMapper.deleteById(id)
    }

    @Transactional
    fun updateReadCount(no: Long) {
        boardMapper.updateReadCount(no)
    }
}