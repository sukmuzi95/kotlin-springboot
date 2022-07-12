package com.example.kotlinspringbootboard.filter

import com.example.kotlinspringbootboard.jwt.JwtProvider
import com.example.kotlinspringbootboard.service.CustomUserDetailsService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtProvider: JwtProvider
    ) : OncePerRequestFilter()
{
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestTokenHeader = request.getHeader("Authorization")
        var username: String? = null
        var token: String? = null

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7)

            try {
                username = jwtProvider.getUsernameFromToken(token)
            } catch (e: IllegalArgumentException) {
                println("Unable to get JWT token $e")
            } catch (e: ExpiredJwtException) {
                println("JWT token has expired $e")
            } catch (e: Exception) {
                println("token valid error $e")
            }
        } else {
//            println("JWT token does not begin with Bearer String")
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            var userDetails = this.customUserDetailsService.loadUserByUsername(username)

            if (token?.let { jwtProvider.validateToken(it, userDetails) } == true) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }

        filterChain.doFilter(request, response)
    }
}