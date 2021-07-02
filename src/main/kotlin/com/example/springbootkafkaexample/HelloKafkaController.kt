package com.example.springbootkafkaexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloKafkaController(
    private val kafkaSender: KafkaSender
) {
    private val logger: Logger = LoggerFactory.getLogger(HelloKafkaController::class.java)

    @GetMapping("/event")
    fun event(): String? {
        logger.info("Sending message to Kafka")
        val payload = MessagePayload(message = "Spring Kafka and Spring Boot Configuration Example")
        kafkaSender.send(payload)
        return "Hello Kafka!"
    }
}