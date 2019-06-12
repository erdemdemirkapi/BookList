package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{
	public Book findByName(String name);
	public List<Book> findByAuthor(String author);
	public List<Book> findByPublisher(String publisher);

}
