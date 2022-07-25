package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import com.example.kotlinspringbootboard.mapper.BoardMapper
import com.example.kotlinspringbootboard.repository.BoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(private val boardRepository: BoardRepository, private val boardMapper: BoardMapper) {

//    @Transactional
//    fun save(boardRequestDto: BoardRequestDto): Any? {
//        var result = boardRequestDto.id?.let { findById(it) }
//
//        return if (result != null) {
//            update(boardRequestDto)
//        } else {
//            boardRepository.save(boardRequestDto.toEntity(boardRequestDto)).boardNo
//        }
//    }

    @Transactional
    fun insert(boardRequestDto: BoardRequestDto): Int {
        return boardMapper.insert(boardRequestDto)
    }

    @Transactional
    fun update(boardRequestDto: BoardRequestDto): Int {
        return boardMapper.update(boardRequestDto)
    }

//    @Transactional
//    fun update(boardRequestDto: BoardRequestDto): Int {
//        return boardRepository.updateBoard(boardRequestDto)
//    }

    @Transactional
    fun findAll(): List<BoardResponseDto> {
        return boardMapper.findAll()
    }

//    @Transactional
//    fun findAll() : List<BoardResponseDto> {
//        return boardRepository.findAll()
//            .stream()
//            .map(::BoardResponseDto)
//            .collect(Collectors.toList())
//    }

//    @Transactional
//    fun findById(no: Long): BoardResponseDto {
//        return boardRepository.findById(no)
//            .map(::BoardResponseDto)
//            .get()
//    }

    @Transactional
    fun findById(no: Long): BoardResponseDto {
        //boardRepository.updateReadCount(no)

        return boardMapper.findById(no)
    }

    @Transactional
    fun delete(id: Long) {
        boardRepository.deleteById(id)
    }

    @Transactional
    fun updateReadCount(no: Long) {
        //boardRepository.updateReadCount(no)
        boardMapper.updateReadCount(no)
    }
}