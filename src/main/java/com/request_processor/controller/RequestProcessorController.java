package com.request_processor.controller;


import com.request_processor.model.request.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/notifications")
public interface RequestProcessorController {

   @PostMapping
   ResponseEntity<String> recieveMessage(RequestDto requestDto);
   }

