package com.clean.architecture.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class QueueConfiguration {

    @Bean(name = "product.queue")
    public Queue productQueue() {
        return new Queue("product.queue", false);
    }
}
