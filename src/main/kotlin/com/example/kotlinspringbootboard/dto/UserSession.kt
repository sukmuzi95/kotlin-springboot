package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.Role
import com.example.kotlinspringbootboard.entity.User
import java.io.Serializable

class UserSession : Serializable {

    var userId: String? = null
    var userPw: String? = null
    var userName: String? = null
    var userEmail: String? = null
    var role: Role? = null

    constructor(user: User) {
        this.userId = user.userId
        this.userPw = user.userPw
        this.userName = user.userName
        this.userEmail = user.userEmail
        this.role = user.role
    }
}