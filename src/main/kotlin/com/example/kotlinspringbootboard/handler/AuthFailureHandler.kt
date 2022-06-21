package com.example.kotlinspringbootboard.handler

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthFailureHandler : SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        var msg = "Invalid Id or Password"

        if (exception is DisabledException) {
            msg = "DisabledException account"
        } else if (exception is CredentialsExpiredException) {
            msg = "CredentialsExpiredException account"
        } else if (exception is BadCredentialsException) {
            msg = "BadCredentialsException account"
        }

        setDefaultFailureUrl("/login?error=true&exception=$msg")

        super.onAuthenticationFailure(request, response, exception)
    }
}