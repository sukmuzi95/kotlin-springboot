package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.service.BoardService
import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

@Controller
class BoardController(@Autowired private val boardService: BoardService) {

    @GetMapping("/board")
    fun list(model: Model): String {
        var boardList: List<BoardResponseDto> = boardService.findAll()
        model.addAttribute("boardList", boardList)

        return "/board/list"
    }

    @PostMapping("/board")
    fun write(boardRequestDto: BoardRequestDto): String {
        boardService.save(boardRequestDto)

        return "redirect:/board"
    }

    @GetMapping("/board/{no}")
    fun detail(@PathVariable("no") no: Long, model: Model): String {
        boardService.updateReadCount(no)
        var boardResponseDto: BoardResponseDto = boardService.findById(no)
        model.addAttribute("boardResponseDto", boardResponseDto)

        return "/board/detail"
    }

    @GetMapping("/board/edit/{no}")
    fun edit(@PathVariable("no") no: Long, model: Model): String {
        var boardResponseDto: BoardResponseDto = boardService.findById(no)
        model.addAttribute("boardResponseDto", boardResponseDto)

        return "/board/update"
    }

    @PutMapping("/board/edit/{no}")
    fun update(boardRequestDto: BoardRequestDto): String {
        boardService.update(boardRequestDto)

        return "redirect:/board"
    }

    @DeleteMapping("/board/{no}")
    fun delete(@PathVariable("no") no: Long): String {
        boardService.delete(no)

        return "redirect:/board"
    }
}