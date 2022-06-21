package com.example.kotlinspringbootboard.mapper

import com.example.kotlinspringbootboard.entity.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {

    fun getUserAccount(userId: String): User

    fun saveUser(user: User)
}