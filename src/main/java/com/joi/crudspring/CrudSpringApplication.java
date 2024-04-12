package com.joi.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joi.crudspring.model.Course;
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
			 c.setCategory("Front-end");

			 courseRepository.save(c);

			 Course c1 = new Course();
			 c1.setName("Entity Framework");
			 c1.setCategory("Back-end");

			 courseRepository.save(c1);
 
		};
	}
}
