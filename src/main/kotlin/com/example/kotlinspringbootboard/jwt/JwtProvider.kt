package com.example.kotlinspringbootboard.jwt

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest
import javax.xml.bind.DatatypeConverter

@Component
class JwtProvider(private val userDetailsService: UserDetailsService) {

    private final val accessExpireTime = 2 * 60 * 60 * 1000L
    private final val refreshExpireTime = 2 * 60 * 60 * 1000L

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    companion object {
        const val ACCESS_EXPIRE_TIME = 2 * 60 * 60 * 1000
    }
    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).body
    }

    fun getSubject(token: String): String {
        return getClaimsFromToken(token).subject
    }

    fun getAuthentication(token: String): Authentication {
//        println("getAuthentication: ${this.getSubject(token)}")
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(this.getSubject(token))

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun generateToken(authentication: Authentication): String {
        val claims: Claims = Jwts.claims().setSubject(authentication.principal.toString())
        claims["roles"] = authentication.authorities
        val now = Date()

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(now.time + accessExpireTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            val claims = getClaimsFromToken(token)

            !claims.expiration.before(Date())
        } catch (e: JwtException) {
            false
        }
    }

    fun resolveToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(JwtFilter.AUTHORIZATION_HEADER)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtFilter.BEARER_PREFIX)) {
            bearerToken.substring(7)
        } else {
            ""
        }
    }
}