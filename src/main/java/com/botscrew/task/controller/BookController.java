package com.botscrew.task.controller;

import com.botscrew.task.dto.BookDto;
import com.botscrew.task.dto.DtoMaker;
import com.botscrew.task.entity.Book;
import com.botscrew.task.service.BookService;
import com.botscrew.task.service.implementation.BookServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class BookController {
    BookService bookService = new BookServiceImpl();

    public BookController() throws SQLException {
    }

    public void addBook(String author, String bookName) throws SQLException {
        bookService.addNewBook(new Book(bookName, author));
    }

    public void removeBook(String bookName) throws SQLException {
        bookService.removeBook(bookName);
    }

    public void editBook(String bookName) throws SQLException {
        bookService.updateBook(bookName);
    }

    public List<BookDto> allBooks() throws SQLException {
        return DtoMaker.booksToBooksDto(bookService.allBooks());
    }

    public void close() throws SQLException {
        bookService.closeConnection();
    }
}
