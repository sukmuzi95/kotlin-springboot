package com.example.kotlinspringbootboard.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthenticationProviderImpl(private val userDetailsService: UserDetailsService) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val token: UsernamePasswordAuthenticationToken = authentication as UsernamePasswordAuthenticationToken

        val username = token.name
        val password = token.credentials as String
        val userDetail: UserDetailsImpl = userDetailsService.loadUserByUsername(username) as UserDetailsImpl

        if (!BCryptPasswordEncoder().matches(password, userDetail.password)) {
            throw BadCredentialsException("${userDetail.username} Invalid Password")
        }

        return UsernamePasswordAuthenticationToken(userDetail.username, "", userDetail.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}