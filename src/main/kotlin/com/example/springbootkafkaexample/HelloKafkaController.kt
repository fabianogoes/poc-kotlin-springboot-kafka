package com.example.springbootkafkaexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloKafkaController(
    private val kafkaSender: KafkaSender
) {
    private val logger: Logger = LoggerFactory.getLogger(HelloKafkaController::class.java)

    @GetMapping("/event")
    fun event(
        @RequestParam(
            name = "message",
            required = false,
            defaultValue = "Spring Kafka and Spring Boot Configuration Example"
        ) message: String
    ): ResponseEntity<MessagePayload> {
        logger.info("Message sent to Kafka successfully!")
        val payload = MessagePayload(message = message)
        kafkaSender.send(payload)
        return ResponseEntity.ok(payload)
    }
}