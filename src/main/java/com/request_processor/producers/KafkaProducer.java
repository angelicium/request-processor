package com.request_processor.producers;

import com.request_processor.model.MessageDto;

public interface KafkaProducer {

    void send(String s) throws Exception;

}
