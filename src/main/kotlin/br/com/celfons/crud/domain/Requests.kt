package br.com.celfons.crud.domain

import br.com.celfons.crud.jpa.PaymentEntity
import br.com.celfons.crud.jpa.UserEntity
import br.com.uol.pagseguro.api.common.domain.CreditCard
import br.com.uol.pagseguro.api.common.domain.PaymentItem
import br.com.uol.pagseguro.api.common.domain.Sender
import br.com.uol.pagseguro.api.common.domain.Shipping
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import java.time.LocalDateTime

data class UserRequest (
    val username: String? = null,
    var password: String? = null
) {

    fun toEntity(crypt: BCryptPasswordEncoder): UserEntity =
        UserEntity(
            username = username,
            password = crypt.encode(password),
            created = LocalDateTime.now()
        )

}

data class PaymentRequest(
    val amount: BigDecimal,
    val shipping: Shipping,
    val sender: Sender,
    val items: List<PaymentItem>,
    val creditCard: CreditCard,
    val receiverEmail: String
) {
    fun toEntity(): PaymentEntity =
        PaymentEntity(amount = amount)
}
