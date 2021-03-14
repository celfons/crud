package br.com.celfons.crud.domain

import br.com.celfons.crud.jpa.UserEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

data class UserRequest (
    val username: String? = null,
    var password: String? = null
) {

    fun toEntity(crypt: BCryptPasswordEncoder): UserEntity =
        UserEntity(
            username = username,
            password = crypt.encode(password)
        )

}
