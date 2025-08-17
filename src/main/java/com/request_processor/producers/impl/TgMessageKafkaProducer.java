package com.request_processor.producers.impl;

import com.request_processor.model.MessageDto;
import com.request_processor.producers.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TgMessageKafkaProducer")
@Slf4j
public class TgMessageKafkaProducer implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String s) throws Exception {
                kafkaTemplate.send("telegram-events", s);
        log.info("Сообщение TgMessage успешно отправлено");
    }
}
