package com.ms.user.producers;

import com.ms.user.domains.User;
import com.ms.user.dtos.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.email-register.name}")
    private String routingKey;

    public void publishMessageEmail(User user, String subject, String body) {
        EmailDto emailDto = new EmailDto(user.getId(), user.getEmail(),
                subject,
                body);
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
