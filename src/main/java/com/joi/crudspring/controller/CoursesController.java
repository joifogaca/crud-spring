package com.joi.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joi.crudspring.model.Course;
import com.joi.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    // @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    // public ResponseEntity<Course> Create(@RequestBody Course record) {

    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(courseRepository.save(record));
    // }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course Create(@RequestBody Course record) {

        return courseRepository.save(record);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Long _id) {
        return courseRepository.findById(_id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course record) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(record.getName());
                    recordFound.setCategory(record.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());

    }

}
