package br.com.celfons.crud.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.Date

@Component
class JWTUtil(
    @Value("\${jwt.secret}") private var secret: String,
    @Value("\${jwt.expiration}") private var expiration: Long? = 60000
) {

    fun generateToken(username: String): String =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()

    fun isTokenValid(token: String): Boolean =
        run {
            val claims = getClaimsToken(token)
            if (claims != null) {
                val username = claims.subject
                val expirationDate = claims.expiration
                val now = Date(System.currentTimeMillis())
                if (username != null && expirationDate != null && now.before(expirationDate)) {
                    return true
                }
            }
            return false
        }

    private fun getClaimsToken(token: String): Claims? =
        try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }

    fun getUserName(token: String): String? =
        getClaimsToken(token)?.subject

}
