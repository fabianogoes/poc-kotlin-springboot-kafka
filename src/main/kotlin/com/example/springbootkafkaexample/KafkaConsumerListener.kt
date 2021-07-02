package com.example.springbootkafkaexample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
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
    fun consumeJson(
        @Payload message: MessagePayload,
        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partitions: List<Int>,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topics: List<String>,
        @Header(KafkaHeaders.OFFSET) offset: List<Long>
    ) {
        logger.info("Received Message: topic: ${topics[0]}, partition: ${partitions[0]}, offset: ${offset[0]}")
        logger.info("Message from kafka: $message")
    }

}