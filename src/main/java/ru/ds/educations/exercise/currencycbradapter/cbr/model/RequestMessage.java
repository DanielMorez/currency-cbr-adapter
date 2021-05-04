package ru.ds.educations.exercise.currencycbradapter.cbr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestMessage {
    @JsonProperty("ID")
    private int id; // optional
    @JsonProperty("Currency")
    private String currency; // optional
    @JsonProperty("onDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onDate;
}
