package com.example.kotlinspringbootboard

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import com.example.kotlinspringbootboard.repository.BoardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class BoardService(@Autowired private val boardRepository: BoardRepository) {

    @Transactional
    fun save(boardSaveDto: BoardRequestDto): Long? {
        return boardRepository.save(boardSaveDto).id
    }

    @Transactional
    fun findAll() : List<BoardResponseDto> {
        return boardRepository.findAll()
            .stream()
            .map(::BoardResponseDto)
            .collect(Collectors.toList())
    }
}