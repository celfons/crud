package br.com.celfons.crud.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import br.com.uol.pagseguro.api.PagSeguroEnv

import br.com.uol.pagseguro.api.http.JSEHttpClient

import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory

import br.com.uol.pagseguro.api.PagSeguro
import br.com.uol.pagseguro.api.credential.Credential
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean


@Configuration
@EnableWebMvc
class PaymentConfig(
    @Value("\${seller.email}") private var sellerEmail: String,
    @Value("\${seller.token}") private var sellerToken: String
) {

    @Bean
    fun getPagSeguro(): PagSeguro? {
        return PagSeguro
            .instance(
                SimpleLoggerFactory(), JSEHttpClient(),
                Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX
            )
    }

}