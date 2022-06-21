package com.example.kotlinspringbootboard.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import javax.persistence.*

@Entity
@Table(name = "TB_USER")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userNo: Long? = 0,

    var userId: String? = null,
    var userPw: String? = null,
    var userName: String? = null,
    var userAuth: String? = null
) : UserDetails, BaseTimeEntity() {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singletonList(SimpleGrantedAuthority(this.userAuth))
    }

    override fun getPassword(): String? {
        return this.password
    }

    override fun getUsername(): String? {
        return userId
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