package com.example.kotlinspringbootboard.config

import com.example.kotlinspringbootboard.handler.AuthFailureHandler
import com.example.kotlinspringbootboard.handler.AuthSuccessHandler
import com.example.kotlinspringbootboard.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired private val customUserDetailsService: CustomUserDetailsService,
    @Autowired private val authSuccessHandler: AuthSuccessHandler,
    @Autowired private val authFailureHandler: AuthFailureHandler
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
            ?.authorizeRequests()
                ?.antMatchers("/", "/login/**", "/signup", "/user", "/js/**", "/css/**", "/app/**", "/forgot-password")?.permitAll()
                ?.anyRequest()?.authenticated()
            ?.and()
                ?.formLogin()
                ?.loginPage("/login")
                ?.loginProcessingUrl("/login/action")
                ?.successHandler(authSuccessHandler)
                ?.failureHandler(authFailureHandler)
            ?.and()
                ?.logout()
                ?.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                ?.logoutSuccessUrl("/login")
                ?.invalidateHttpSession(true)
                ?.deleteCookies("JSESSIONID", "remember-me")
                ?.permitAll()
            ?.and()
                ?.sessionManagement()
                ?.maximumSessions(1)
                ?.maxSessionsPreventsLogin(false)
                ?.expiredUrl("/login?error=true&exception=Have been attemted to login from a new place or session expired")
            ?.and()
            ?.and()
                ?.rememberMe()
                ?.alwaysRemember(false)
                ?.tokenValiditySeconds(43200)
                ?.rememberMeParameter("remember-me")
            ?.and()
                ?.authenticationProvider(authenticationProvider())
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/resources/**")
    }

//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.userDetailsService(customUserDetailsService)?.passwordEncoder(BCryptPasswordEncoder())
//    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        var authProvider: DaoAuthenticationProvider = DaoAuthenticationProvider()

        authProvider.setUserDetailsService(customUserDetailsService)
        authProvider.setPasswordEncoder(BCryptPasswordEncoder())

        return authProvider
    }
}