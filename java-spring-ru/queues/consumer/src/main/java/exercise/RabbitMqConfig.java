package exercise;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

@Configuration
public class RabbitMqConfig {

    @Bean
    Queue queue() {
        return new Queue("queue", false);
    }

    @Bean
    TopicExchange exchange() {
        // Задаём имя "обменника". Как и имя очереди, оно может быть любым
        return new TopicExchange("exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("key");
    }

}
