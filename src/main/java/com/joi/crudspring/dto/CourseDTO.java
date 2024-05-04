package com.joi.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joi.crudspring.enums.Category;
import com.joi.crudspring.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Valid @Length(min = 3, max = 100) String name,
        @NotNull @NotBlank @Valid @ValueOfEnum(enumClass = Category.class) String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
