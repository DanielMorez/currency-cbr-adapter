package ru.ds.educations.exercise.currencycbradapter.cbr.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CurrencyDto {
    private String currency;
    private BigDecimal curs;
    private LocalDate onDate;
}
