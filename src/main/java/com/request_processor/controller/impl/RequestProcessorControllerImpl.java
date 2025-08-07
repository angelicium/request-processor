package com.request_processor.controller.impl;

import com.request_processor.controller.RequestProcessorController;
import com.request_processor.model.request.RequestDto;
import com.request_processor.service.RequestProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestProcessorControllerImpl implements RequestProcessorController {

    @Autowired
    private RequestProcessorService requestProcessorService;
    @Override
    public ResponseEntity<String> recieveMessage(@RequestBody RequestDto requestDto) {

        return requestProcessorService.createMessage(requestDto);
    }
}
