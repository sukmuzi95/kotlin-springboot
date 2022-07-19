package com.example.kotlinspringbootboard.config

import com.example.kotlinspringbootboard.jwt.JwtFilter
import com.example.kotlinspringbootboard.jwt.JwtProvider
import com.example.kotlinspringbootboard.security.CustomAuthenticationFilter
import com.example.kotlinspringbootboard.security.handler.*
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
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
    private val authenticationSuccessHandler: AuthenticationSuccessHandlerImpl,
    private val authenticationFailureHandler: AuthenticationFailureHandlerImpl,
    private val authenticationEntryPointImpl: AuthenticationEntryPointImpl,
    private val accessDeniedHandlerImpl: AccessDeniedHandlerImpl,
    private val jwtProvider: JwtProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: WebSecurity) {
//        web.ignoring()
//            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//        web.ignoring().antMatchers("/resources/static/css/**")
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPointImpl)
            .accessDeniedHandler(accessDeniedHandlerImpl)
            .and().authorizeRequests()
            .antMatchers("/login", "/signup", "/user/**", "/js/**", "/css/**", "/app/**", "/forgot-password",
                "/user/mail-auth", "/update-password/**", "/api/v1/authenticate", "/authenticate", "/api/v1/user")
            .permitAll()
            .antMatchers("/css/**", "/js/**", "/img/**")
            .permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .permitAll()
//            .antMatchers("**").hasAnyRole("ROLE_USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .deleteCookies(JwtFilter.AUTHORIZATION_HEADER)
            .and()
            .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun authenticationFilter(): CustomAuthenticationFilter {
        val customAuthenticationFilter = CustomAuthenticationFilter(authenticationManager())
        customAuthenticationFilter.setFilterProcessesUrl("/loginProc")
        customAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler)
        customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler)
        customAuthenticationFilter.afterPropertiesSet()

        return customAuthenticationFilter
    }

    @Bean
    fun jwtFilter(): JwtFilter {
        return JwtFilter(jwtProvider)
    }
}