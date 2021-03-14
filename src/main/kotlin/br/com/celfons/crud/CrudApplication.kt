package br.com.celfons.crud

import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableSwagger2
class CrudApplication

fun main(args: Array<String>) {
	runApplication<CrudApplication>(*args)
}
