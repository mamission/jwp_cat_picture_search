package com.example.catpicture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CatPictureApplication {

	private final UserRepository userRepository;

	public CatPictureApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatPictureApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "hello";
	}

	@GetMapping("/{id}")
	public User test(@PathVariable Long id) {
		userRepository.save(new User(1L, "test user"));
		return userRepository.findById(id).get();
	}
}
