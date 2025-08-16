package com.request_processor.producers.impl;

import com.request_processor.model.MessageDto;
import com.request_processor.producers.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("EmailKafkaProducer")
@Slf4j
public class EmailKafkaProducer implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    @Override
    public void send(MessageDto messageDto) throws Exception {
                kafkaTemplate.send("email-events", messageDto);
        log.info("Сообщение Email успешно отправлено");
    }
}
