package com.joi.crudspring.enums.converters;

import com.joi.crudspring.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.stream.Stream;

@Convert
public class StatusConverter implements AttributeConverter<Status, String>{

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(status == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if(value == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return Stream.of(Status.values())
        .filter(c -> c.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
}
