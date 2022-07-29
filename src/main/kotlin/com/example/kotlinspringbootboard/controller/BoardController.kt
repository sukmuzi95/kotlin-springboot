package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.service.BoardService
import com.example.kotlinspringbootboard.dto.BoardRequestDto
import com.example.kotlinspringbootboard.dto.BoardResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class BoardController(@Autowired private val boardService: BoardService) {

    @GetMapping("/board")
    fun list(model: Model, authentication: Authentication?): String {
        return if (authentication?.isAuthenticated == false || authentication == null) {
            "redirect:/login"
        } else {
            println("123")
            val boardList: List<BoardResponseDto> = boardService.findAll()

            model.addAttribute("boardList", boardList)

            "/board/list"
        }
    }

    @PostMapping("/board")
    @ResponseBody
    fun insert(@RequestBody boardRequestDto: BoardRequestDto): Int {
        return boardService.insert(boardRequestDto)
    }

    @GetMapping("/board/{no}")
    fun detail(@PathVariable("no") no: Long, model: Model): String {
        val boardResponseDto: BoardResponseDto = boardService.findById(no)
        boardService.updateReadCount(no)

        model.addAttribute("board", boardResponseDto)

        return "/board/detail"
    }

    @GetMapping("/board/edit/{no}")
    fun edit(@PathVariable("no") no: Long, model: Model): String {
        val boardResponseDto: BoardResponseDto = boardService.findById(no)
        model.addAttribute("board", boardResponseDto)

        return "/board/update"
    }

    @PutMapping("/board/edit")
    @ResponseBody
    fun update(@RequestBody boardRequestDto: BoardRequestDto): Int {
        return boardService.update(boardRequestDto)
    }

    @DeleteMapping("/board/{no}")
    fun delete(@PathVariable("no") no: Long): String {
        boardService.deleteById(no)

        return "redirect:/board"
    }
}