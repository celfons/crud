package br.com.celfons.crud.commons

import java.lang.RuntimeException

data class BusinessException(
    override val message: String? = null
): RuntimeException(message)
