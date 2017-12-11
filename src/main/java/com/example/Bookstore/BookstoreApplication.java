package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.models.Book;
import com.example.Bookstore.models.BookRepository;
import com.example.Bookstore.models.User;
import com.example.Bookstore.models.UserRepository;;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, UserRepository urepository){
		return (args) ->{
			log.info("save a couple of books");
			brepository.save(new Book(null, "A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 20.00));
			brepository.save(new Book(null, "Animal Farm", "George Orwell", 1945, "2212345-5", 29.99));

			User user1 = new User("user", "$2a$10$TBGA50vNgT6elbZuHw3pC.uDD90SlPiMnlvAb/hj6XxDqjOfawfP.", "USER");
			User user2 = new User("admin", "$2a$10$68sgSp8a6ofHdZwffk9/9uNnbiIcrQYFz/ssfIBlTglt/HLgA9Pwi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()){
				log.info(book.toString());
			}
		};
	}
}
