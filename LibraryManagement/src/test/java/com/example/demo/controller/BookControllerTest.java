package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;


public class BookControllerTest {
	
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAll() {
		when(bookService.getAll()).thenReturn(null);
		
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		when(bookService.getAll()).equals(book);
	}
	
	@Test
	public void testGetByName() {		
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		when(bookService.getByName("bookName")).thenReturn(book);
	}
	
	@Test
	public void testGetByAuthor() {		
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		
		when(bookService.getByAuthor("bookAuthor")).thenReturn(bookList);
	}

	@Test
	public void testGetByPublisher() {		
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		
		when(bookService.getByPublisher("bookPublisher")).thenReturn(bookList);
	}
	
	@Test
	public void testGetBookValues() {
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		assertEquals(book.getName(), "bookName");
		assertEquals(book.getAuthor(), "bookAuthor");
		assertEquals(book.getPublisher(), "bookPublisher");
	}
	
	@Test
	public void testSetBookValues() {
		Book book = new Book("bookName", "bookAuthor", "bookPublisher");
		book.setName("newName");
		book.setAuthor("newAuthor");
		book.setPublisher("newPublisher");
		assertEquals(book.getName(), "newName");
		assertEquals(book.getAuthor(), "newAuthor");
		assertEquals(book.getPublisher(), "newPublisher");
	}

}
