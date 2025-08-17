package com.request_processor.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.request_processor.entity.RequestProcessor;
import com.request_processor.model.MessageDto;
import com.request_processor.producers.KafkaProducer;
import com.request_processor.repository.RequestProcessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestProcessorScheduler {

    private static final Logger log = LoggerFactory.getLogger(RequestProcessorScheduler.class);
    @Autowired
    private RequestProcessorRepository repository;

    @Autowired
    @Qualifier("TgMessageKafkaProducer")
    private KafkaProducer tgMessageKafkaProducer;

    @Autowired
    @Qualifier("EmailKafkaProducer")
    private KafkaProducer emailKafkaProducer;

    @Autowired
    @Qualifier("SmsKafkaProducer")
    private  KafkaProducer smsKafkaProducer;

    @Autowired
    @Qualifier("PushKafkaProducer")
    private KafkaProducer pushKafkaProducer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 1000)
    public void sendNotification() throws JsonProcessingException {

        List<RequestProcessor> requestProcessors = repository.findTop50BySentFalseOrderByCreatedAtAsc();
        for (RequestProcessor requestProcessor : requestProcessors) {
            log.info(requestProcessor.getTopic());
            MessageDto messageDto = new MessageDto(requestProcessor.getTopic(), requestProcessor.getKey(), requestProcessor.getText());

            String s = objectMapper.writeValueAsString(messageDto);

            try {
                switch(requestProcessor.getTopic()) {
                    case "SMS" :
                        smsKafkaProducer.send(s);
                        break;
                    case "EMAIL" :
                        emailKafkaProducer.send(s);
                        break;
                    case "PUSH" :
                        pushKafkaProducer.send(s);
                        break;
                    case "TG_MESSAGE" :
                        tgMessageKafkaProducer.send(s);
                        break;
                    }
                } catch(Exception exception){
                log.error("Message failed to send", exception);
                requestProcessor.setAttempt(requestProcessor.getAttempt()+1);
                repository.save(requestProcessor);
            }
            requestProcessor.setSent(true);
            repository.save(requestProcessor);
            }
        }
    }

