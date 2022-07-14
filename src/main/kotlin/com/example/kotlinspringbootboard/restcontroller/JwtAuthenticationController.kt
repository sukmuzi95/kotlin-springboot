//package com.example.kotlinspringbootboard.restcontroller
//
//import com.example.kotlinspringbootboard.dto.CustomUserDetails
//import com.example.kotlinspringbootboard.jwt.JwtProvider
//import com.example.kotlinspringbootboard.jwt.JwtRequest
//import com.example.kotlinspringbootboard.common.ApiResponse
//import com.example.kotlinspringbootboard.service.CustomUserDetailsService
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.BadCredentialsException
//import org.springframework.security.authentication.DisabledException
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.web.bind.annotation.CrossOrigin
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@CrossOrigin
//class JwtAuthenticationController(
//    private val authenticationManager: AuthenticationManager,
//    private val jwtProvider: JwtProvider,
//    private val customUserDetailsService: CustomUserDetailsService
//    ) {
//
//    @PostMapping("/api/v1/authenticate")
//    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<ApiResponse> {
//        authenticate(authenticationRequest.username, authenticationRequest.password)
//
//        val userDetails: CustomUserDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.username) as CustomUserDetails
//        val token = jwtProvider.generateToken(userDetails)
//        val response = ApiResponse(apiResponseType.code, apiResponseType.message)
//        response.data = token
//
//        println(token)
//        println("${authenticationRequest.username} ${authenticationRequest.password}")
//
//        return ResponseEntity(response, HttpStatus.OK)
//    }
//
//    private fun authenticate(username: String, password: String) {
//        try {
//            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
//        } catch (e: DisabledException) {
//            throw DisabledException("USER_DISABLED", e)
//        } catch (e: BadCredentialsException) {
//            throw BadCredentialsException("INVALID_CREDENTIALS", e)
//        }
//    }
//}