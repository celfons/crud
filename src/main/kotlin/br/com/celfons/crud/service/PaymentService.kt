package br.com.celfons.crud.service

import br.com.celfons.crud.domain.PaymentRequest
import br.com.celfons.crud.jpa.PaymentRepository
import br.com.uol.pagseguro.api.common.domain.enums.Currency
import org.springframework.stereotype.Service
import br.com.uol.pagseguro.api.transaction.register.DirectPaymentRegistrationBuilder
import br.com.uol.pagseguro.api.PagSeguro

@Service
class PaymentService(
    private var repository: PaymentRepository,
    private var pagSeguro: PagSeguro
) {

    fun pay(request: PaymentRequest) {

        val directPayment: DirectPaymentRegistrationBuilder = DirectPaymentRegistrationBuilder()
            .withPaymentMode("default")
            .withCurrency(Currency.BRL)
            .withExtraAmount(request.amount)
            .withSender(request.sender)
            .withShipping(request.shipping)
            .addItems(request.items)
            .withReceiverEmail(request.receiverEmail)

        pagSeguro.transactions()
            .register(directPayment)
            .withCreditCard(request.creditCard)

        repository.save(request.toEntity())

    }

}
