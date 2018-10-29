package com.example.Bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.models.Book;
import com.example.Bookstore.models.BookRepository;

@Controller
public class BookController{
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/") // index
		public String doSomething(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/booklist", method= RequestMethod.GET)
	public String bookList(Model model){
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//RESTful service
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service to get book by id
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId){
		return repository.findOne(bookId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
		repository.delete(bookId);
		return "redirect:../booklist";
	}
	
	
}