package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Role
import com.example.kotlinspringbootboard.entity.User

class UserDto {

    var email: String = ""
    var password: String = ""
    var nickname: String = ""
    lateinit var role: Role
}