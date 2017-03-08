package com.botscrew.task.service;

import com.botscrew.task.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    void addNewBook(Book book) throws SQLException;

    void removeBook(String bookName) throws SQLException;

    void updateBook(String oldBookName) throws SQLException;

    List<Book> allBooks() throws SQLException;

    void closeConnection() throws SQLException;
}
