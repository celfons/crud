package br.com.celfons.crud.interfaces

import br.com.celfons.crud.domain.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun login(@RequestBody request: UserRequest) {}

}
