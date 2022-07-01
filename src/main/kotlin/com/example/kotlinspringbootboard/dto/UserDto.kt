package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Role
import com.example.kotlinspringbootboard.entity.User

class UserDto {

    var userPw: String? = null
    var userName: String? = null
    var userEmail: String? = null
    lateinit var role: Role

    fun toEntity(userDto: UserDto): User {
        var user: User = User().apply {
            this.userPw = userDto.userPw
            this.userName = userDto.userName
            this.userEmail = userDto.userEmail
            this.role = Role.USER
        }

        return user
    }
}