package com.request_processor.scheduler;

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

    @Scheduled(fixedRate = 1000)
    public void sendNotification() {

        List<RequestProcessor> requestProcessors = repository.findTop50BySentFalseOrderByCreatedAtAsc();
        for (RequestProcessor requestProcessor : requestProcessors) {
            log.info(requestProcessor.getTopic());
            MessageDto messageDto = new MessageDto(requestProcessor.getTopic(), requestProcessor.getKey(), requestProcessor.getText());
            // сделать поле
            try {
                switch(requestProcessor.getTopic()) {
                    case "SMS" :
                        smsKafkaProducer.send();
                        break;
                    case "EMAIL" :
                        emailKafkaProducer.send("Сообщение успешно отправлено");
                        break;
                    case "PUSH" :
                        pushKafkaProducer.send("Сообщение успешно отправлено");
                        break;
                    case "TG_MESSAGE" :
                        tgMessageKafkaProducer.send("Сообщение успешно отправлено");
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

