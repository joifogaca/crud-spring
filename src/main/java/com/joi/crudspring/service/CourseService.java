package com.joi.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.joi.crudspring.exception.RecordNotFoundException;
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

    public Course findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public Course Create(@Valid Course record) {

        return courseRepository.save(record);
    }

    public Course update(@PathVariable Long id,
            @Valid Course record) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(record.getName());
                    recordFound.setCategory(record.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
