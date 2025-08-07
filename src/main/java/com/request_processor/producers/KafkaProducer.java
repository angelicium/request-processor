package com.request_processor.producers;

public interface KafkaProducer {

    void send(String string) throws Exception;

}
