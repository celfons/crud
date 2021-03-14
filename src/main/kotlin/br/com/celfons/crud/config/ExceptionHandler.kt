package br.com.celfons.crud.config

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import br.com.celfons.crud.commons.BusinessException
import br.com.celfons.crud.domain.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.slf4j.LoggerFactory

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [(BusinessException::class)])
    fun handler(exception: BusinessException): ResponseEntity<ErrorResponse> {
        logger.info(exception.message)
        val result = ErrorResponse(message = exception.message!!)
        return ResponseEntity(result, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}
