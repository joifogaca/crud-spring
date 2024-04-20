package com.joi.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.joi.crudspring.dto.CourseDTO;
import com.joi.crudspring.dto.mapper.CourseMapper;
import com.joi.crudspring.exception.RecordNotFoundException;
import com.joi.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                // .map(course -> courseMapper.toDTO(course))
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));

    }

    public CourseDTO create(@Valid @NotNull CourseDTO record) {
        return courseMapper.toDTO(
                courseRepository.save(
                        courseMapper.toEntity(record)));
    }

    public CourseDTO update(@NotNull @Positive Long id,
            @Valid @NotNull CourseDTO record) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(record.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(record.category()));
                    return courseRepository.save(recordFound);
                }).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
