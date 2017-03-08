package com.botscrew.task.dao.implementation;

import com.botscrew.task.entity.Book;
import com.botscrew.task.constants.Constants;
import com.botscrew.task.dao.BookDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDaoImpl implements BookDao {
    Connection connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
    PreparedStatement preparedStatement;

    public BookDaoImpl() throws SQLException {
        createTableBook();
    }

    public void createTableBook() throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("CREATE TABLE IF NOT EXISTS book (book_id " +
                "INT PRIMARY KEY AUTO_INCREMENT, book_name VARCHAR(50), book_author_name VARCHAR(50))"));
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void addNewBook(Book book) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format("INSERT INTO book (book_name, book_author_name)" +
                " VALUES (?,?);"));
        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthorName());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void removeBook(String bookName) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM book WHERE book_name = ?;");
        preparedStatement.setString(1, bookName);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateBook(String oldBookName, String newBookName) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE book SET  book_name = ? WHERE book_name = ?;");
        preparedStatement.setString(1, newBookName);
        preparedStatement.setString(2, oldBookName);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public List<Book> allBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = connection.prepareStatement("SELECT * FROM book;").executeQuery();
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        Collections.sort(books);
        return books;
    }

    @Override
    public List<Book> findBookByName(String bookName) throws SQLException {
        List<Book> books = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE book_name = ?;");
        preparedStatement.setString(1, bookName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return books;
    }

    @Override
    public void removeById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM book WHERE book_id = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateById(int id, String newBookName) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE book SET  book_name = ? WHERE book_id = ?;");
        preparedStatement.setString(1, newBookName);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
