package br.com.celfons.crud.config

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import br.com.celfons.crud.service.UserService
import org.springframework.http.HttpMethod

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private var authEntryPoint: RestAuthenticationEntryPoint,
    private var jwtUtil: JWTUtil
): WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var service: UserService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST,"/user").permitAll()
            .anyRequest().authenticated().and().formLogin()
            .permitAll()
        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil))
        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil, service))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.exceptionHandling().authenticationEntryPoint(authEntryPoint)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder =
        BCryptPasswordEncoder()

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder())
    }

}

const val authorization = "Authorization"
const val bearer = "Bearer"
