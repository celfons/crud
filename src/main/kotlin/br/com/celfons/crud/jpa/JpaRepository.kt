package br.com.celfons.crud.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String?): UserEntity?
}

interface PaymentRepository: JpaRepository<PaymentEntity, Long>
