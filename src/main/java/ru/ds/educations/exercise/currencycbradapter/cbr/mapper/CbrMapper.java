package ru.ds.educations.exercise.currencycbradapter.cbr.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.ds.educations.exercise.currencycbradapter.cbr.model.RequestMessage;
import ru.ds.educations.exercise.currencycbradapter.cbr.model.ResponseMessage;

import java.time.LocalDate;

@Component
public class CbrMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.classMap(RequestMessage.class, ResponseMessage.class)
                .byDefault()
                .register();
    }
}
