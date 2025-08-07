package com.request_processor.service.impl;

import com.request_processor.entity.RequestProcessor;
import com.request_processor.model.request.RequestDto;
import com.request_processor.repository.RequestProcessorRepository;
import com.request_processor.service.RequestProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class RequestProcessorServiceImpl implements RequestProcessorService {

    @Autowired
    private RequestProcessorRepository repository;

    @Override
    public ResponseEntity<String> createMessage(RequestDto requestDto) {
        RequestProcessor requestProcessor = new RequestProcessor();
        requestProcessor.setText(requestDto.getMessage());
        requestProcessor.setTopic(requestDto.getType().name());
        requestProcessor.setKey(UUID.randomUUID().toString());
        requestProcessor.setSent(false);
        requestProcessor.setAttempt(1);

        repository.save(requestProcessor);

        return ResponseEntity.ok("Успешно сохранено");
    }
}
