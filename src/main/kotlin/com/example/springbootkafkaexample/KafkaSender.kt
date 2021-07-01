package com.example.springbootkafkaexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.util.*


@Service
class KafkaSender(
    private val kafkaTemplate: KafkaTemplate<String, String>? = null
) {
    private val logger: Logger = LoggerFactory.getLogger(KafkaSender::class.java)

    @Value("\${app.topic-name}")
    private val topic: String? = null

    fun send(payload: MessagePayload) {
        val key = UUID.randomUUID().toString()
        val message: Message<MessagePayload> = MessageBuilder
            .withPayload(payload)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .setHeader(KafkaHeaders.PARTITION_ID, 0)
            .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
            .build()
        logger.info("sending message='{}' to topic='{}'", message, topic)
        kafkaTemplate!!.send(message)
    }
}