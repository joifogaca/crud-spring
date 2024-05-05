package com.joi.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.joi.crudspring.dto.CourseDTO;
import com.joi.crudspring.dto.CoursePageDTO;
import com.joi.crudspring.dto.mapper.CourseMapper;
import com.joi.crudspring.enums.Status;
import com.joi.crudspring.exception.RecordNotFoundException;
import com.joi.crudspring.model.Course;
import com.joi.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Service
@Validated
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CoursePageDTO findAll(@PositiveOrZero int pageNumber,@Positive @Max(100) int size) {
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber,size));
        List<CourseDTO> courses = page.get().map(courseMapper::toDTO)
        .collect(Collectors.toList());
         return  new CoursePageDTO(courses, page.getTotalElements(),page.getTotalPages());
        // return courseRepository.findAll().stream()
        //         // .map(course -> courseMapper.toDTO(course))
        //         .map(courseMapper::toDTO)
        //         .collect(Collectors.toList());
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));

    }

    public CourseDTO create(@Valid @NotNull CourseDTO record) {
        Course course = courseMapper.toEntity(record);
        course.setStatus(Status.ACTIVE);
        return courseMapper.toDTO(
                courseRepository.save(course)
        );
    }

    public CourseDTO update(@NotNull @Positive Long id,
            @Valid @NotNull CourseDTO recordDTO) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    Course course = courseMapper.toEntity(recordDTO);
                    courseFound.setName(recordDTO.name());
                    courseFound.setCategory(courseMapper.convertCategoryValue(recordDTO.category()));
                    //recordFound.setLessons(course.getLessons());
                    courseFound.getLessons().clear();
                    course.getLessons().forEach(courseFound.getLessons()::add);
                    return courseMapper.toDTO(courseRepository.save(courseFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
