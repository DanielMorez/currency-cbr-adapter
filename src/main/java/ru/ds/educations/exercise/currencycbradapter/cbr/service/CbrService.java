package ru.ds.educations.exercise.currencycbradapter.cbr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.cbr.web.*;
import ru.ds.educations.exercise.currencycbradapter.cbr.mapper.CbrMapper;
import ru.ds.educations.exercise.currencycbradapter.cbr.model.RequestMessage;
import ru.ds.educations.exercise.currencycbradapter.cbr.model.ResponseMessage;
import ru.ds.educations.exercise.currencycbradapter.cbr.parser.CbrParser;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Service
@Component
@Slf4j
public class CbrService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CbrMapper cbrMapper;

    @Autowired
    private CbrParser parser;

    public String getCursOnDate(String message) {
        GetCursOnDateXML getCursOnDateXML = new GetCursOnDateXML();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RequestMessage requestMessage = null;
        try {
            requestMessage = objectMapper.readValue(message, RequestMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Ошибка при десерилизации", e);
        }

        XMLGregorianCalendar date =
                parser.localDateToGregorianCalendarXml(requestMessage.getOnDate());
        List<Map> currencies = parser.getValuteDate(date);
        ResponseMessage responseMessage = cbrMapper.map(requestMessage, ResponseMessage.class);
        responseMessage.setRates(currencies);

        String response = null;
        try {
            response = objectMapper.writeValueAsString(responseMessage);
        } catch (JsonProcessingException e) {
            log.error("Ошибка при серилизации", e);
        }

        return response;

    }
}
