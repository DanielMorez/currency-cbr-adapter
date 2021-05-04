package ru.ds.educations.exercise.currencycbradapter.cbr.controller;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ds.educations.exercise.currencycbradapter.cbr.service.CbrService;

import javax.jms.*;
import java.util.Map;

@Component
public class CbrController {

    @Autowired
    private CbrService cbrService;

    private JMSContext jmsContext;

    @JmsListener(destination = "dev.cbr.request")
    @SendTo("dev.cbr.response")
    public Message<String> GetCursOnDate(@RequestBody String body, @Headers Map<String, Object> headers) {
        Message<String> message = MessageBuilder
                .withPayload(cbrService.getCursOnDate(body))
                .setHeader("CorrelationID", headers.get("CorrelationID"))
                .build();

        return message;
    }


}
