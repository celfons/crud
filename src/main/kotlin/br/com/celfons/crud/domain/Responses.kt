package br.com.celfons.crud.domain

data class UserResponse(
    val id: Long? = 0,
    val username: String? = null,
    var password: String? = null
)

data class ErrorResponse(
    val message: String? = null
)
