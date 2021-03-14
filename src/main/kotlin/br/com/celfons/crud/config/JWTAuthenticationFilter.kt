package br.com.celfons.crud.config

import br.com.celfons.crud.commons.BusinessException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.http.HttpServletResponse
import br.com.celfons.crud.domain.UserRequest
import javax.servlet.http.HttpServletRequest
import br.com.celfons.crud.jpa.UserEntity
import javax.servlet.FilterChain

class JWTAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private var jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse?
    ): Authentication? =
        try {
            val (username, password) = ObjectMapper().readValue(request.inputStream, UserRequest::class.java)
            val token = UsernamePasswordAuthenticationToken(username, password)
            authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw BusinessException("User not found!")
        }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) =
        run {
            val username = (authResult.principal as UserEntity).username
            val token = jwtUtil.generateToken(username!!)
            response.addHeader(authorization, "$bearer $token")
        }

}
