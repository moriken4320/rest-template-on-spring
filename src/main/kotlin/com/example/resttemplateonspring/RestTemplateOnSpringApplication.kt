package com.example.resttemplateonspring

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class RestTemplateOnSpringApplication {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

    @Bean
    @Profile("!test")
    @Throws(Exception::class)
    fun run(restTemplate: RestTemplate): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            val quote: Quote? = restTemplate.getForObject(
                "http://localhost:8080/api/random", Quote::class.java
            )
            log.info(quote.toString())
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RestTemplateOnSpringApplication::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(RestTemplateOnSpringApplication::class.java, *args)
        }
    }
}
