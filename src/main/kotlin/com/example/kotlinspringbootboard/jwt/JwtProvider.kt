package com.example.kotlinspringbootboard.jwt

import com.example.kotlinspringbootboard.dto.CustomUserDetails
import com.example.kotlinspringbootboard.dto.UserDto
import com.example.kotlinspringbootboard.service.CustomUserDetailsService
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest
import javax.xml.bind.DatatypeConverter
import kotlin.collections.HashMap

@Component
class JwtProvider(private val customUserDetailsService: CustomUserDetailsService) {

    private final val accessExpireTime = 2 * 60 * 60 * 1000L
    private final val refreshExpireTime = 5 * 60 * 60

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
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

//    fun refreshAccessToken(userDto: UserDto): String {
//        val headers = mutableMapOf<String, Any>()
//        headers["type"] = "token"
//
//        val payloads = mutableMapOf<String, Any>()
//        payloads["email"] = userDto.userEmail
//
//        val expiration = Date()
//        expiration.time = expiration.time + refreshExpireTime
//
//        return Jwts
//            .builder()
//            .setHeader(headers)
//            .setClaims(payloads)
//            .setSubject("user")
//            .setExpiration(expiration)
//            .signWith(SignatureAlgorithm.ES256, secret)
//            .compact()
//    }

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).body
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = customUserDetailsService.loadUserByUsername(this.getSubject(token))

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getSubject(token: String): String {
        return getClaimsFromToken(token).subject
    }

    fun isValidToken(token: String): Boolean {
        return try {
            val claims = getClaimsFromToken(token)

            !claims.expiration.before(Date())
        } catch (e: JwtException) {
            false
        }
    }

//    fun resolveToken(request: HttpServletRequest): String {
//        return request.getHeader("token")
//    }
//
//    fun validateJwtToken(request: ServletRequest, authToken: String): Boolean {
//        try {
//            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken)
//
//            return true
//        } catch (e: MalformedJwtException) {
//            request.setAttribute("exception", "MalformedJwtException")
//            logger.error("위조된 JWT 서명입니다.")
//        } catch (e: ExpiredJwtException) {
//            request.setAttribute("exception", "ExpiredJwtException")
//            logger.error("만료된 JWT 토큰입니다.")
//        } catch (e: UnsupportedJwtException) {
//            request.setAttribute("exception", "UnsupportedJwtException")
//            logger.error("지원되지않는 JWT 토큰입니다.")
//        } catch (e: IllegalArgumentException) {
//            request.setAttribute("exception", "IllegalArgumentException")
//            logger.error("잘못된 JWT 토큰입니다.")
//        }
//
//        return false
//    }

//    /**
//     * jwt 토큰에서 username 검색
//     */
//    fun getUsernameFromToken(token: String): String {
//        try {
//            return getClaimFromToken(token, Claims::getSubject)
//        } catch (e: Exception) {
//            throw Exception("username from token exception")
//        }
//    }
//
//    /**
//     * jwt 토큰에서 날짜 만료 검색
//     */
//    fun getExpirationDateFromToken(token: String): Date {
//        return getClaimFromToken(token, Claims::getExpiration)
//    }
//
//    fun <T> getClaimFromToken(token: String, claimsResolver: java.util.function.Function<Claims, T>): T {
//        val claims: Claims = getAllClaimsFromToken(token)
//
//        return claimsResolver.apply(claims)
//    }
//
//    /**
//     * secret 키를 가지고 토큰에서 정보 검색
//     */
//    private fun getAllClaimsFromToken(token: String): Claims {
//        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).body
//    }
//
//    /**
//     * 토큰 만료 체크
//     */
//    private fun isTokenExpired(token: String): Boolean {
//        val expiration = getExpirationDateFromToken(token)
//
//        return expiration.before(Date())
//    }
//
//    /**
//     * username 으로 토큰 생성
//     */
//    fun generateToken(userDetails: CustomUserDetails): String {
//        val claims: Map<String, Any> = HashMap()
//
//        return doGenerateToken(claims, userDetails.username)
//    }
//
//    /**
//     * 토큰을 생성하는 동안
//     * 1. 토큰, Issuer, Expiration, Subject, ID로 claims 정의
//     * 2. HS512 알고리즘과 secret key 를 가지고 JWT 서명
//     * 3. JWS Compact Serialization 에 따라 JWT 를 URL 안전 문자열로 압축
//     */
//    private fun doGenerateToken(claims: Map<String, Any>, username: String): String {
//        return Jwts.builder()
//            .setClaims(claims)
//            .setSubject(username)
//            .setIssuedAt(Date(System.currentTimeMillis()))
//            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//            .signWith(SignatureAlgorithm.HS512, secret)
//            .compact()
//    }
//
//    /**
//     * 토큰 검증
//     */
//    fun validateToken(token: String, userDetails: UserDetails): Boolean {
//        val username = getUsernameFromToken(token)
//
//        return username == userDetails.username && !isTokenExpired(token)
//    }
}