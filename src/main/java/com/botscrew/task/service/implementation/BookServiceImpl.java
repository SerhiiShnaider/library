package com.botscrew.task.service.implementation;

import com.botscrew.task.dao.BookDao;
import com.botscrew.task.dao.implementation.BookDaoImpl;
import com.botscrew.task.entity.Book;
import com.botscrew.task.service.BookService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    Scanner scanner = new Scanner(System.in);

    public BookServiceImpl() throws SQLException {
    }

    @Override
    public void addNewBook(Book book) throws SQLException {
        bookDao.addNewBook(book);
        System.out.printf("book %s \"%s\" was added\n", book.getAuthorName(), book.getName());
    }

    @Override
    public void removeBook(String bookName) throws SQLException {
        List<Book> removeList = bookDao.findBookByName(bookName);
        if (removeList.size() == 1) {
            bookDao.removeBook(bookName);
            System.out.printf("book %s \"%s\" was removed\n", removeList.get(0).getAuthorName(), removeList.get(0)
                    .getName());
        } else if (removeList.size() > 1) {
            System.out.println("we have few books with such name please choose one by typing a number of book:");
            removeList.forEach(r -> System.out.printf("\t%s\n", r));
            System.out.print("> ");
            String removeNumber = scanner.nextLine();
            for (Book removeBook : removeList) {
                if (Integer.valueOf(removeNumber) == removeBook.getId()) {
                    System.out.printf("book %s \"%s\" was removed\n", removeBook.getAuthorName(), removeBook.getName());
                }
            }
            bookDao.removeById(Integer.valueOf(removeNumber));
        }
    }

    @Override
    public void updateBook(String oldBookName) throws SQLException {
        List<Book> updateList = bookDao.findBookByName(oldBookName);
        String newBookName;
        if (updateList.size() == 1) {
            newBookName = updatingText();
            bookDao.updateBook(oldBookName, newBookName);
            System.out.printf("book %s \"%s\" was updated\n", updateList.get(0).getAuthorName(), updateList.get(0)
                    .getName());
        } else if (updateList.size() > 1) {
            System.out.println("we have few books with such name please choose one by typing a number of book:");
            updateList.forEach(r -> System.out.printf("\t%s\n", r));
            System.out.print("> ");
            String updateNumber = scanner.nextLine();
            newBookName = updatingText();
            for (Book updateBook : updateList) {
                if (Integer.valueOf(updateNumber) == updateBook.getId()) {
                    System.out.printf("book %s \"%s\" was edited\n", updateBook.getAuthorName(), updateBook.getName());
                }
            }
            bookDao.updateById(Integer.valueOf(updateNumber), newBookName);
        }
    }

    private String updatingText() {
        System.out.print("type new name: ");
        String newBookName = scanner.nextLine();
        return newBookName;
    }

    @Override
    public List<Book> allBooks() throws SQLException {
        return bookDao.allBooks();
    }

    @Override
    public void closeConnection() throws SQLException {
        bookDao.closeConnection();
    }
}
