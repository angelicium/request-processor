package com.request_processor.producers.impl;

import com.request_processor.producers.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Qualifier("EmailKafkaProducer")
@Slf4j
public class EmailKafkaProducer implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String string) throws Exception {
        CompletableFuture<SendResult<String, String>> send =
                kafkaTemplate.send("email-events", string);
        log.info("Сообщение Email успешно отправлено");
    }
}
