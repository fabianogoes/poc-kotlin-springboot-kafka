package com.example.springbootkafkaexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service


@Service
class KafkaConsumerListener {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(
        topics = ["\${app.topic-name}"],
        groupId = "\${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consumeJson(@Payload message: MessagePayload) {
        logger.info("Message from kafka: $message")
    }

}