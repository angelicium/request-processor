package com.request_processor.producers.impl;

import com.request_processor.model.MessageDto;
import com.request_processor.producers.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Qualifier("SmsKafkaProducer")
@Slf4j
public class SmsKafkaProducer implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String s) throws Exception {
                kafkaTemplate.send("sms-events", s);
        log.info("Сообщение Sms успешно отправлено");
    }
}
