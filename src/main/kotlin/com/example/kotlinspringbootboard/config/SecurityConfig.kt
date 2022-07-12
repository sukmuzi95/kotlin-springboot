package com.example.kotlinspringbootboard.config

import com.example.kotlinspringbootboard.handler.AuthFailureHandler
import com.example.kotlinspringbootboard.handler.AuthSuccessHandler
import com.example.kotlinspringbootboard.jwt.JwtAuthenticationEntryPoint
import com.example.kotlinspringbootboard.filter.JwtRequestFilter
import com.example.kotlinspringbootboard.handler.WebAccessDeniedHandler
import com.example.kotlinspringbootboard.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
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
    private val jwtRequestFilter: JwtRequestFilter,
    private val webAccessDeniedHandler: WebAccessDeniedHandler
) : WebSecurityConfigurerAdapter() {


    override fun configure(builder: AuthenticationManagerBuilder) {
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
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
            .authorizeRequests()
            .antMatchers("/", "/login/**", "/signup", "/user/**", "/js/**", "/css/**", "/app/**", "/forgot-password",
                "/user/mail-auth", "/update-password/**", "/api/v1/authenticate", "/authenticate", "/api/v1/user")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(webAccessDeniedHandler)
            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
//    override fun configure(http: HttpSecurity?) {
//        http?.csrf()?.disable()
//            ?.authorizeRequests()
//            ?.antMatchers(
//                "/", "/login/**", "/signup", "/user/**", "/js/**", "/css/**", "/app/**", "/forgot-password",
//                "/user/mail-auth", "/update-password/**", "/api/v1/authenticate", "/authenticate", "/api/v1/user"
//            )?.permitAll()
//            ?.anyRequest()?.authenticated()
//            ?.and()
//            ?.formLogin()
//            ?.disable()
//            ?.headers()
//            ?.frameOptions()
//            ?.disable()
////                ?.loginPage("/login")
////                ?.loginProcessingUrl("/login/action")
////                ?.successHandler(authSuccessHandler)
//            //?.failureHandler(authFailureHandler)
//            ?.and()
//            ?.exceptionHandling()
//            ?.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            ?.and()
//            ?.sessionManagement()
//            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//        http?.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
////            ?.and()
////                ?.logout()
////                ?.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
////                ?.logoutSuccessUrl("/login")
////                ?.invalidateHttpSession(true)
////                ?.deleteCookies("JSESSIONID", "remember-me")
////                ?.permitAll()
////            ?.and()
////                ?.sessionManagement()
////                ?.maximumSessions(1)
////                ?.maxSessionsPreventsLogin(false)
////                ?.expiredUrl("/login?error=true&exception=Have been attemted to login from a new place or session expired")
////            ?.and()
////            ?.and()
////                ?.rememberMe()
////                ?.alwaysRemember(false)
////                ?.tokenValiditySeconds(43200)
////                ?.rememberMeParameter("remember-me")
////            ?.and()
////                ?.authenticationProvider(authenticationProvider())
//    }


//    @Bean
//    fun authenticationProvider(): DaoAuthenticationProvider {
//        var authProvider = DaoAuthenticationProvider()
//
//        authProvider.setUserDetailsService(customUserDetailsService)
//        authProvider.setPasswordEncoder(BCryptPasswordEncoder())
//
//        return authProvider
//    }



//    @Autowired
//    fun configureGlobal(authenticationManagerBuilder: AuthenticationManagerBuilder) {
//        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
//            .passwordEncoder(passwordEncoder())
//    }






}