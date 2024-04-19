package com.joi.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joi.crudspring.enums.Category;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    @JsonProperty("_id") Long id,
    @NotNull @NotBlank @Length(min = 3, max = 100) String name,
    @NotNull @Length(max = 10) @Column(length = 10, nullable = false) @Pattern(regexp = "Back-end|Front-end") Category category) 
    {
}
