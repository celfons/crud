package br.com.celfons.crud.interfaces

import br.com.celfons.crud.config.authorization
import br.com.celfons.crud.domain.UserResponse
import br.com.celfons.crud.service.UserService
import br.com.celfons.crud.domain.UserRequest
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val service: UserService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UserRequest): UserResponse? =
        service.create(request)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestHeader(authorization) auth: String, @RequestBody request: UserRequest): UserResponse? =
        service.update(request)

    @DeleteMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@RequestHeader(authorization) auth: String, @PathVariable("id") id: Long) =
        service.delete(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(@RequestHeader(authorization) auth: String): List<UserResponse> =
        service.getAll()

    @GetMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    fun getById(@RequestHeader(authorization) auth: String, @PathVariable("id") id: Long) =
        service.getById(id)

}
