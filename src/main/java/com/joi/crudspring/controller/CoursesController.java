package com.joi.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joi.crudspring.dto.CourseDTO;
import com.joi.crudspring.model.Course;

import com.joi.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> list() {
        return courseService.findAll();
    }

    // @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    // public ResponseEntity<Course> Create(@RequestBody Course record) {

    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(courseRepository.save(record));
    // }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO Create(@RequestBody @Valid CourseDTO record) {

        return courseService.Create(record);
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable("id") 
    @NotNull @Positive Long _id) {
        return courseService.findById(_id);

    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Long id,
    @NotNull @RequestBody @Valid CourseDTO record) {
        return courseService.update(id, record);
                }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        courseService.delete(id);
    }

}
