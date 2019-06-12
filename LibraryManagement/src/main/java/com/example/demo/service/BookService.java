package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book create(String name, String author, String publisher) {
		return bookRepository.save(new Book(name, author, publisher));
	}
	
	public List<Book> getAll(){
		return bookRepository.findAll();
	}
	
	public Book getByName(String name) {
		return bookRepository.findByName(name);
	}
	
	public List<Book> getByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public List<Book> getByPublisher(String publisher){
		return bookRepository.findByPublisher(publisher);
	}
	
	public Book update(String name, String author, String publisher) {
		Book b = bookRepository.findByName(name);
		b.setAuthor(author);
		b.setPublisher(publisher);
		return bookRepository.save(b);
	}
	
	public void deleteAll() {
		bookRepository.deleteAll();
	}
	
	public void delete(String name) {
		Book b = bookRepository.findByName(name);
		bookRepository.delete(b);
	}	
}