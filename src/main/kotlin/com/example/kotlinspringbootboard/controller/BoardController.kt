package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BoardController(@Autowired private val boardService: BoardService) {

    @GetMapping("/")
    fun list(): String {
        return "board/list.html"
    }

    @GetMapping("/post")
    fun write(): String {
        return "board/write.html"
    }
}