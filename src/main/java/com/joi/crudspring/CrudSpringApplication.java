package com.joi.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joi.crudspring.enums.Category;
import com.joi.crudspring.model.Course;
import com.joi.crudspring.model.Lesson;
import com.joi.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);
			courseRepository.save(c);

			Lesson lesson = new Lesson();
			lesson.setName("Primeiro Modulo");
			lesson.setYoutubeUrl("https://www");
			lesson.setCourse(c);
			c.getLessons().add(lesson);
			courseRepository.save(c);
			
	
			Course c1 = new Course();
			c1.setName("Entity Framework");
			c1.setCategory(Category.BACK_END);
			courseRepository.save(c1);
			Lesson lesson1 = new Lesson();
			lesson1.setName("Primeiro Modulo Back");
			lesson1.setYoutubeUrl("https://www");
			lesson1.setCourse(c1);
			c1.getLessons().add(lesson1);
			courseRepository.save(c1);
		};
	}
}
