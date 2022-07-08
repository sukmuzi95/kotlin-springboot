package com.example.kotlinspringbootboard.dto

import com.example.kotlinspringbootboard.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(@Autowired private val user: User) : UserDetails {

    override fun getAuthorities(): Collection<out GrantedAuthority> {
        var collectors: Collection<GrantedAuthority> = ArrayList()
        collectors.plus("ROLE_${user.role}")

        return collectors
    }

    override fun getPassword(): String? {
        return user.userPw
    }

    override fun getUsername(): String? {
        return user.userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}