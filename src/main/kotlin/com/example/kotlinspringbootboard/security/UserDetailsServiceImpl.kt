package com.example.kotlinspringbootboard.security

import com.example.kotlinspringbootboard.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email).orElseThrow {
            Throwable()
            UsernameNotFoundException("해당 사용자가 존재하지 않습니다. $email")
        }

        return UserDetailsImpl().apply {
            this.id = user.id
            this.email = user.email
            this.password = user.password
            this.nickname = user.nickname
            this.role = user.role!!
        }
    }
}