package com.example.kotlinspringbootboard.controller

import com.example.kotlinspringbootboard.security.dto.UserDto
import com.example.kotlinspringbootboard.entity.Role
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.common.ApiResponse
import com.example.kotlinspringbootboard.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class UserController(
    private val userService: UserService
) {

    /**
     *  회원가입
     *  @param userDto
     *  @return page
     */
    @PostMapping("/api/v1/user")
    @ResponseBody
    fun signup(@RequestBody userDto: UserDto): ResponseEntity<ApiResponse> {
        val response = ApiResponse(200, "Success")
        val alreadyUser = userService.findByUserEmail(userDto.email)

        return if (alreadyUser.isPresent) {
            response.code = 201

            ResponseEntity(response, HttpStatus.OK)
        } else {
            var user = User().apply {
                this.email = userDto.email
                this.password = userDto.password
                this.nickname = userDto.nickname
                this.role = Role.USER
            }

            userService.save(user)

            ResponseEntity(response, HttpStatus.OK)
        }
    }

//    @PostMapping("/user")
//    @ResponseBody
//    fun signup(@RequestBody userDto: UserDto): Boolean {
//        userDto.userEmail?.let {
//            if (userService.findByUserEmail(it).isPresent) {
//                return false
//            } else {
//                userService.save(userDto)
//
//                return true
//            }
//        }?: kotlin.run {
//            return false
//        }
//    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().authentication)

        return "redirect:/login"
    }

    @PostMapping("/user/mail-auth")
    @ResponseBody
    fun sendAuthMail(@RequestParam(value = "email") email: String): String {
        return if (userService.findByUserEmail(email).isPresent) {
            userService.createMessage(email)
        } else {
            "false"
        }
    }

    @PutMapping("/user")
    @ResponseBody
    fun updatePassword(@RequestParam email: String, @RequestParam password: String): Int {
        return userService.update(email, password)
    }
}