package com.botscrew.task.dao;

import com.botscrew.task.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    void addNewBook(Book book) throws SQLException;

    void removeBook(String bookName) throws SQLException;

    void updateBook(String oldBookName, String newBookName) throws SQLException;

    List<Book> allBooks() throws SQLException;

    List<Book> findBookByName(String bookName) throws SQLException;

    void removeById(int id) throws SQLException;

    void updateById(int id,String newBookName) throws SQLException;

    void closeConnection() throws SQLException;
}
