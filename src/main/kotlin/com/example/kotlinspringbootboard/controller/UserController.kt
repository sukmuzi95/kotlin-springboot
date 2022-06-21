package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(@Autowired private val userService: UserService) {

    /**
     *  회원가입
     *  @param user
     *  @return
     */
    @PostMapping("/user")
    fun signup(user: User): String {
        userService.joinUser(user)

        return "redirect:/login"
    }
}