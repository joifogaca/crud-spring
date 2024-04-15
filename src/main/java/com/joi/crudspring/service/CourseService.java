package com.joi.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.joi.crudspring.model.Course;
import com.joi.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id);

    }

    public Course Create(@Valid Course record) {

        return courseRepository.save(record);
    }

    public Optional<Course> update(@PathVariable Long id,
            @Valid Course record) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(record.getName());
                    recordFound.setCategory(record.getCategory());
                    return courseRepository.save(recordFound);
                });
    }

    public boolean delete(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.delete(recordFound);
                    return true;
                })
                .orElse(false);
    }
}
