package com.request_processor.service;

import com.request_processor.model.request.RequestDto;
import org.springframework.http.ResponseEntity;

public interface RequestProcessorService {

    ResponseEntity<String> createMessage (RequestDto requestDto);
}
