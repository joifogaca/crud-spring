package com.joi.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joi.crudspring.enums.Category;
import com.joi.crudspring.model.Lesson;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Length(min = 3, max = 100) String name,
        @NotNull @NotBlank   String category,
        List<LessonDTO> lessons) {
}
