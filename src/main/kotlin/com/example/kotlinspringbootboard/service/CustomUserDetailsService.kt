package com.example.kotlinspringbootboard.service

import com.example.kotlinspringbootboard.dto.CustomUserDetails
import com.example.kotlinspringbootboard.entity.User
import com.example.kotlinspringbootboard.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepository.findByEmail(email)
            .orElseThrow {
                Throwable()
                UsernameNotFoundException("해당 사용자가 존재하지 않습니다. $email")
            }

        return CustomUserDetails(user)
    }
}