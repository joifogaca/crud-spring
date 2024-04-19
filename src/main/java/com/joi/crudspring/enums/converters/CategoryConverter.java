package com.joi.crudspring.enums.converters;

import java.util.stream.Stream;

import com.joi.crudspring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class CategoryConverter implements AttributeConverter<Category, String>{

    @Override
    public String convertToDatabaseColumn(Category category) {
        if(category == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if(value == null) {
            return null;
        }
        // TODO Auto-generated method stub
        return Stream.of(Category.values())
        .filter(c -> c.getValue().equals(value))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }

}
