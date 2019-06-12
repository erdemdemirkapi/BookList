package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/create")
	public String create(@RequestParam String name, @RequestParam String author, @RequestParam String publisher) {
		Book b = bookService.create(name, author, publisher);
		return b.toString();
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/getAll")
	public List<Book> getAll() {
		return bookService.getAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/get")
	public Book getBook(@RequestParam String name) {
		return bookService.getByName(name);
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/update")
	public String update(@RequestParam String name, @RequestParam String author, @RequestParam String publisher) {
		Book b = bookService.update(name, author, publisher);
		return b.toString();
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/delete")
	public String delete(@RequestParam String name) {
		bookService.delete(name);
		return "Deleted: " + name;
	}
	
	@CrossOrigin(origins = {"http://localhost:3000", "https://book-list-frontend.herokuapp.com"})
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		bookService.deleteAll();
		return "Deleted all items!";
	}
}
