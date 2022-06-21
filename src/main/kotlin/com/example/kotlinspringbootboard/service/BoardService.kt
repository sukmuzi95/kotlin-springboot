package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import com.example.kotlinspringbootboard.dto.toEntity
import com.example.kotlinspringbootboard.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class BoardService(@Autowired private val boardRepository: BoardRepository) {

    @Transactional
    fun save(boardRequestDto: BoardRequestDto): Any? {
        var result = boardRequestDto.id?.let { findById(it) }

        return if (result != null) {
            update(boardRequestDto)
        } else {
            boardRepository.save(boardRequestDto.toEntity(boardRequestDto)).id
        }

//        return boardRepository.save(boardRequestDto.toEntity(boardRequestDto)).id
    }

    @Transactional
    fun update(boardRequestDto: BoardRequestDto): Int {
        return boardRepository.updateBoard(boardRequestDto)
    }

    @Transactional
    fun findAll() : List<BoardResponseDto> {
        return boardRepository.findAll()
            .stream()
            .map(::BoardResponseDto)
            .collect(Collectors.toList())
    }

    @Transactional
    fun findById(no: Long): BoardResponseDto {
        return boardRepository.findById(no)
            .map(::BoardResponseDto)
            .get()
    }

    @Transactional
    fun delete(id: Long) {
        boardRepository.deleteById(id)
    }

    fun updateReadCount(no: Long) {
        boardRepository.updateReadCount(no)
    }
}