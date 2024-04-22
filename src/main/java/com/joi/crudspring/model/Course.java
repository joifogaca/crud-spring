package com.joi.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joi.crudspring.enums.Category;
import com.joi.crudspring.enums.Status;
import com.joi.crudspring.enums.converters.CategoryConverter;
import com.joi.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // Lombok gerar gette e setter
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status <> 'Inativo'")

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    // @Length(max = 10)
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    // @Length(max = 10)
    @Column(length = 10, nullable = false)
    // @Pattern(regexp = "Ativo|Inativo")
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name = "course_id") E ruim para performace
    private List<Lesson> lessons = new ArrayList<>();
}
