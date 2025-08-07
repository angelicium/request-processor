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
@Qualifier("PushKafkaProducer")
@Slf4j
public class PushKafkaProducer implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String string) throws Exception {
        CompletableFuture<SendResult<String, String>> send =
                kafkaTemplate.send("push-events", string);
        log.info("Сообщение Push успешно отправлено");


    }
}
