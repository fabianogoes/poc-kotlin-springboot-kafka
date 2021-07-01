# Spring Boot Kafka Example

The purpose of this project is:

- Install and Configure a Kafka Cluster in an Instance Cloud
- Create a Spring Boot project to Producer and Consumer Messages to Kafka 

- dependency
```xml
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>
```

- properties

```yaml
spring:
  kafka:
    consumer:
      group-id: tpd-loggers
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
    bootstrap-servers: <SERVER_IP>:9092
```

## References

- [How To Install Apache Kafka on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-20-04)
- [Install Kafdrop - Kafka Web UI](https://github.com/obsidiandynamics/kafdrop)  
- [Spring Boot + Apache Kafka Hello World Example](https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world)
- [Configure Kafka Producer and Consumer in spring boot](https://codingnconcepts.com/spring-boot/configure-kafka-producer-and-consumer)
- [Spring Boot Kafka JsonSerializer Example](https://howtodoinjava.com/kafka/spring-boot-jsonserializer-example/)


## Problem

```shell
[2021-06-30 22:48:19,410] WARN [Consumer clientId=consumer-console-consumer-21667-1, groupId=console-consumer-21667] Connection to node -1 (/51.158.114.56:9092) could not be established. Broker may not be available. (org.apache.kafka.clients.NetworkClient)
```

## Solution

So how do we fix it? We go and speak to our lovely Kafka administrator (who may well be us) 
and fix the server.properties on the broker(s) so that advertised.listeners correctly provides the hostname 
and port on which the broker can be reached from clients. We saw above that it was returning localhost. 

```
advertised.listeners=PLAINTEXT://localhost:9092
listeners=PLAINTEXT://0.0.0.0:9092
```

And change the advertised.listeners configuration thus:

```
advertised.listeners=PLAINTEXT://asgard03.moffatt.me:9092
listeners=PLAINTEXT://0.0.0.0:9092
```

[Solution Reference](https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/)


