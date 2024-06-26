package com.joi.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joi.crudspring.dto.CourseDTO;
import com.joi.crudspring.dto.LessonDTO;
import com.joi.crudspring.enums.Category;
import com.joi.crudspring.enums.Status;
import com.joi.crudspring.model.Course;
import com.joi.crudspring.model.Lesson;
import com.joi.crudspring.model.Lesson;
import com.joi.crudspring.model.Lesson;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons()
        .stream()
        .map(lesson -> new LessonDTO(lesson.getId(), 
        lesson.getName(), 
        lesson.getYoutubeUrl()))
        .collect(Collectors.toList());
        
        return new CourseDTO(course.getId(), 
        course.getName(), 
        course.getCategory().getValue(),
        lessons);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        };
        
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        
        List<Lesson> lessons = courseDTO.lessons().stream().map(LessonDTO -> {
        var lesson = new Lesson();
        lesson.setId(LessonDTO.id());
        lesson.setName(LessonDTO.name());
        lesson.setYoutubeUrl(LessonDTO.youtubeUrl());
        lesson.setCourse(course);
        return lesson;

        }).collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    public Category convertCategoryValue(String value){
        if(value == null){ return null; }

        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria invalida: "+ value);
        };
    }

}
