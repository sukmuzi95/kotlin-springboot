package com.example.kotlinspringbootboard.security.dto

import com.example.kotlinspringbootboard.entity.Role
import com.example.kotlinspringbootboard.entity.User

class UserDto {

    var email: String = ""
    var password: String = ""
    var nickname: String = ""
    lateinit var role: Role
}