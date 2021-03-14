package br.com.celfons.crud.jpa

import org.springframework.security.core.userdetails.UserDetails
import br.com.uol.pagseguro.api.common.domain.PaymentItem
import org.springframework.security.core.GrantedAuthority
import br.com.uol.pagseguro.api.common.domain.CreditCard
import br.com.uol.pagseguro.api.common.domain.Shipping
import br.com.uol.pagseguro.api.common.domain.Sender
import br.com.celfons.crud.domain.UserResponse
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "user")
class UserEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private val id: Long? = 0,
    private var username: String? = null,
    private var password: String? = null,
    private val created: LocalDateTime,
    private var updated: LocalDateTime? = LocalDateTime.now()
) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername() = username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    fun toResponse(): UserResponse =
        UserResponse(
            id = id,
            username = username,
            password = password,
            created = created,
            updated = updated
        )

}

@Entity(name = "payment")
data class PaymentEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private val id: Long? = 0,
    val amount: BigDecimal,
    val created: LocalDateTime? = LocalDateTime.now()
)
