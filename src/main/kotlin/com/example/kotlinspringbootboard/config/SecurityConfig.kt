package com.example.kotlinspringbootboard.config

import com.example.kotlinspringbootboard.filter.CustomAuthenticationFilter
import com.example.kotlinspringbootboard.handler.AuthFailureHandler
import com.example.kotlinspringbootboard.handler.AuthSuccessHandler
import com.example.kotlinspringbootboard.jwt.JwtAuthenticationEntryPoint
import com.example.kotlinspringbootboard.filter.JwtFilter
import com.example.kotlinspringbootboard.handler.AccessDeniedHandler
import com.example.kotlinspringbootboard.jwt.JwtProvider
import com.example.kotlinspringbootboard.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customUserDetailsService: CustomUserDetailsService,
    private val authSuccessHandler: AuthSuccessHandler,
    private val authFailureHandler: AuthFailureHandler,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
//    private val jwtFilter: JwtFilter,
    private val accessDeniedHandler: AccessDeniedHandler,
    private val jwtProvider: JwtProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/resources/**")
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler)
            .and().authorizeRequests()
            .antMatchers("/", "/login", "/signup", "/user/**", "/js/**", "/css/**", "/app/**", "/forgot-password",
                "/user/mail-auth", "/update-password/**", "/api/v1/authenticate", "/authenticate", "/api/v1/user", "/index")
            .permitAll()
//            .antMatchers("**").hasAnyRole("ROLE_USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")
            .and()
            .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun authenticationFilter(): CustomAuthenticationFilter {
        val customAuthenticationFilter = CustomAuthenticationFilter(authenticationManager())
        customAuthenticationFilter.setFilterProcessesUrl("/loginProc")
        customAuthenticationFilter.setAuthenticationSuccessHandler(authSuccessHandler)
        customAuthenticationFilter.setAuthenticationFailureHandler(authFailureHandler)
        customAuthenticationFilter.afterPropertiesSet()

        return customAuthenticationFilter
    }

    @Bean
    fun jwtFilter(): JwtFilter {
        return JwtFilter(jwtProvider)
    }
}