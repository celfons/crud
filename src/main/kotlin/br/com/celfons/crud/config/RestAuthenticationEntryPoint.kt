package br.com.celfons.crud.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class RestAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        exception: AuthenticationException?
    ) = response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception?.message)

}
