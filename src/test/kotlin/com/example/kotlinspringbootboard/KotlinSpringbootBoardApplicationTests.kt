//package com.example.kotlinspringbootboard
//
//import com.example.kotlinspringbootboard.dto.BoardRequestDto
//import com.example.kotlinspringbootboard.dto.BoardResponseDto
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//
//@SpringBootTest
//class KotlinSpringbootBoardApplicationTests(@Autowired private val boardService: BoardService) {
//
//    @Test
//    fun save() {
//        var boardSaveDto: BoardRequestDto = BoardRequestDto().apply {
//            this.id = 0
//            this.title = "제목"
//            this.content = "내용"
//            this.registerId = "작성자"
//        }
//
//        var result: Any? = boardService.save(boardSaveDto)
//
//        if (result != null) {
//            if (result > 0) {
//                println("# Success save() ~ ")
//                findAll()
//            } else {
//                println("# Fail save() ~ ")
//            }
//        } else {
//            println("result is null")
//        }
//    }
//
//    fun findAll() {
//        var list: List<BoardResponseDto> = boardService.findAll()
//
//        println("# Success findAll() : $list")
//    }
//
//    @Test
//    fun contextLoads() {
//    }
//
//}
