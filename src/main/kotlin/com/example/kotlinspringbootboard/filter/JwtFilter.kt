package com.example.kotlinspringbootboard.filter

import com.example.kotlinspringbootboard.jwt.JwtProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(private val jwtProvider: JwtProvider) : OncePerRequestFilter() {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer "
    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("JwtFilter")

        val jwt = resolveToken(request)
        println("jwt : $jwt")

        if (StringUtils.hasText(jwt) && jwtProvider.isValidToken(jwt)) {
            println("jwt 통과")
            val authentication = jwtProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            println("jwt 실패")
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken.substring(7)
        } else {
            ""
        }
    }
}