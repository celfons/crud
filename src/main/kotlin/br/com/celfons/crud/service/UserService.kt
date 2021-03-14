package br.com.celfons.crud.service

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import br.com.celfons.crud.domain.UserRequest
import br.com.celfons.crud.domain.UserResponse
import br.com.celfons.crud.jpa.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private var repository: UserRepository,
    private var crypt: BCryptPasswordEncoder
): UserDetailsService {

    fun create(request: UserRequest): UserResponse? =
        repository.save(request.toEntity(crypt)).toResponse()

    fun update(request: UserRequest): UserResponse? =
        repository.save(request.toEntity(crypt)).toResponse()

    fun delete(id: Long) =
        repository.deleteById(id)

    fun getAll(): List<UserResponse> =
        repository.findAll().map {user -> user.toResponse() }

    fun getById(id: Long): UserResponse? =
        repository.findById(id).takeIf { user -> user.isPresent }?.get()?.toResponse()

    override fun loadUserByUsername(username: String?): UserDetails =
        repository.findByUsername(username) ?: throw UsernameNotFoundException(username)

}
