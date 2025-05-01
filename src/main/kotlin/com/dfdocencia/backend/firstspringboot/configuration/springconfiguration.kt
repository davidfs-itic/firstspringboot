package com.dfdocencia.backend.firstspringboot.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class springconfiguration {

    @Bean
    fun filterChain(httpsecurity: HttpSecurity): SecurityFilterChain {

        return httpsecurity
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS )}
            .build()
    }
}