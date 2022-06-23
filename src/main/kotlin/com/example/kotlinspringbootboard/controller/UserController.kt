package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.dto.UserDto
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class UserController(@Autowired private val userService: UserService) {

    /**
     *  회원가입
     *  @param user
     *  @return page
     */
    @PostMapping("/user")
    @ResponseBody
    fun signup(@RequestBody userDto: UserDto): Boolean {
        userDto.userId?.let {
            if (userService.findByUserId(it).isPresent) {
                return false
            } else {
                userService.save(userDto)

                return true
            }
        }?: kotlin.run {
            return false
        }
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().authentication)

        return "redirect:/login"
    }
}