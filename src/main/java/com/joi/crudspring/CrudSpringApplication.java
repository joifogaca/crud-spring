package com.joi.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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
	@Profile("dev")
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for(int i =0; i<10; i++){
				Course c = new Course();
				c.setName("Angular com Spring" + i);
				c.setCategory(Category.FRONT_END);
				Lesson lesson = new Lesson();
				lesson.setName("Primeiro Modulo");
				lesson.setYoutubeUrl("12345678910");
				lesson.setCourse(c);
				c.getLessons().add(lesson);
				courseRepository.save(c);
				
		
				Course c1 = new Course();
				c1.setName("Ngx Angular" + i);
				c1.setCategory(Category.BACK_END);
				Lesson lesson1 = new Lesson();
				lesson1.setName("Primeiro Modulo Back");
				lesson1.setYoutubeUrl("12345678910");
				lesson1.setCourse(c1);
				c1.getLessons().add(lesson1);
				courseRepository.save(c1);
			}

			
		};
	}
}
