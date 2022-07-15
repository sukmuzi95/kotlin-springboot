package com.example.kotlinspringbootboard.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashSet

class JwtFilter(private val jwtProvider: JwtProvider) : OncePerRequestFilter() {

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer"
    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        
//        if (!shouldNotFilter(request)) {
            if (request.cookies != null) {
                val jwt = Arrays.stream(request.cookies).filter {
                    it.name == AUTHORIZATION_HEADER
                }.findFirst().map(Cookie::getValue).orElse(null)
                println("jwt : $jwt")

                if (StringUtils.hasText(jwt) && jwt.startsWith(BEARER_PREFIX)) {
                    println("jwt 통과")
                    val authentication = jwtProvider.getAuthentication(jwt.substring(6))
                    SecurityContextHolder.getContext().authentication = authentication
                } else {
                    println("jwt 실패")
                }
            }
//        }

        //val jwt = jwtProvider.resolveToken(request)

//        if (StringUtils.hasText(jwt) && jwtProvider.isValidToken(jwt)) {
//            println("jwt 통과")
//            val authentication = jwtProvider.getAuthentication(jwt)
//            SecurityContextHolder.getContext().authentication = authentication
//        } else {
//            println("jwt 실패")
//        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val skipUri: Set<String> = HashSet<String>(listOf("/signup", "/login"))
        val pathMatcher = AntPathMatcher()

        //return super.shouldNotFilter(request)
        return skipUri.stream().anyMatch {
            pathMatcher.match(it, request.requestURI)
        }
    }
}