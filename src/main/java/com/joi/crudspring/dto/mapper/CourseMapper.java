package com.joi.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.joi.crudspring.dto.CourseDTO;
import com.joi.crudspring.enums.Category;
import com.joi.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), Category.FRONT_END);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        ;
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONT_END);
        course.setStatus("Ativo");
        return course;
    }

}
