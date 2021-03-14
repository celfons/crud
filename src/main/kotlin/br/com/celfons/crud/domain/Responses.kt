package br.com.celfons.crud.domain

import java.time.LocalDateTime

data class UserResponse(
    val id: Long? = 0,
    val username: String? = null,
    var password: String? = null,
    val created: LocalDateTime?,
    var updated: LocalDateTime?
)

data class ErrorResponse(
    val message: String? = null
)
