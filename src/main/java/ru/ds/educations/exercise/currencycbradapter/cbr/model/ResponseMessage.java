package ru.ds.educations.exercise.currencycbradapter.cbr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ResponseMessage {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onDate;
    private List<Map> rates;
}
