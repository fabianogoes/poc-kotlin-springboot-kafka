package com.example.springbootkafkaexample

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MessagePayload(
    @JsonProperty("message") var message: String,
    @JsonProperty("identifier") var identifier: String? = UUID.randomUUID().toString()
)