package br.com.celfons.crud.interfaces

import br.com.celfons.crud.config.authorization
import br.com.celfons.crud.domain.PaymentRequest
import br.com.celfons.crud.service.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pay")
class PaymentController(
    private val service: PaymentService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun pay(@RequestHeader(authorization) auth: String, @RequestBody request: PaymentRequest) {
        service.pay(request)
    }

}
