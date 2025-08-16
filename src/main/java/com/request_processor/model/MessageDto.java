package com.request_processor.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageDto {
    public MessageDto(String topic, String key, String value) {
        this.topic = topic;
        this.key = key;
        this.value = value;
    }

    private String topic;

    private String key;

    private String value;
}
