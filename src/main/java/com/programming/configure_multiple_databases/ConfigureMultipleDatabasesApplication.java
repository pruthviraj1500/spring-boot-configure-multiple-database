package com.programming.configure_multiple_databases;

import com.programming.configure_multiple_databases.book.repository.BookRepository;
import com.programming.configure_multiple_databases.model.book.Book;
import com.programming.configure_multiple_databases.model.user.User;
import com.programming.configure_multiple_databases.user.repository.UserRepository;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
//@ComponentScan("{com.programming.configure_multiple_databases.*}")
public class ConfigureMultipleDatabasesApplication {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final BookRepository bookRepository;

    public ConfigureMultipleDatabasesApplication(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
	public void addDataToDB(){
		userRepository.saveAll(
				         Stream.of(new User(500, "Pruthviraj"), new User(501, "Pruthvi"))
						.collect(Collectors.toList()));

		bookRepository.saveAll(
				         Stream.of(new Book(700, "Core_Java"),new Book(701, "Spring_Boot"))
						.collect(Collectors.toList()));
	}

	@GetMapping("/getUsers")
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConfigureMultipleDatabasesApplication.class, args);
	}

}
