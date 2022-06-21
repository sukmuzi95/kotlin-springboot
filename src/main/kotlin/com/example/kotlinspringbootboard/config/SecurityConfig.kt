package com.example.kotlinspringbootboard.config

import com.example.kotlinspringbootboard.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests()
            .antMatchers("/login", "/signup", "/resources/**").permitAll()

            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/board")

            .and()
            .csrf()
            .disable()

        return http.build()
    }

//    @Bean
//    fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.userDetailsService(userService)?.passwordEncoder(BCryptPasswordEncoder())
//    }

//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.userDetailsService(userService)?.passwordEncoder(BCryptPasswordEncoder())
//    }
}