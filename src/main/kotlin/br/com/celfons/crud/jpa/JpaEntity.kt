package br.com.celfons.crud.jpa

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority
import br.com.celfons.crud.domain.UserResponse
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "user")
class UserEntity(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id val id: Long? = 0,
    private var username: String? = null,
    private var password: String? = null
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
            password = password
        )

}
