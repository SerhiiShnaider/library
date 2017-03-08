package com.botscrew.task.view;

import com.botscrew.task.controller.BookController;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Starter {
    Scanner scanner = new Scanner(System.in);
    BookController bookController = new BookController();

    public Starter() throws SQLException {
        start();
    }

    private void start() throws SQLException {
        System.out.print("> ");
        String command = scanner.nextLine();
        reader(command);
        start();
    }

    private void reader(String command) throws SQLException {
        if (command.equalsIgnoreCase("exit")) {
            System.exit(0);
            bookController.close();
        } else {
            validatorCommand(command);
        }
    }

    private void validatorCommand(String command) throws SQLException {
        Matcher m = Pattern.compile("( *[a-zA-Z]*)(.*)", Pattern.CASE_INSENSITIVE).matcher(command);
        String suffix = null;
        String prefix = null;
        if (m.find()) {
            suffix = m.group(1).trim();  // команда
            prefix = m.group(2).trim();  // інфо книжки
        }
        commandReader(suffix, prefix);
    }

    private void commandReader(String suffix, String prefix) throws SQLException {
        String[] book;
        if (suffix.equalsIgnoreCase("add")) {
            book = validatorBookInfo(prefix);
            bookController.addBook(book[0], book[1].substring(1, book[1].length() - 1));
        } else if (suffix.equalsIgnoreCase("remove")) {
            bookController.removeBook(prefix);
        } else if (suffix.equalsIgnoreCase("edit")) {
            bookController.editBook(prefix);
        } else if (suffix.equalsIgnoreCase("allbooks")) {
            List books = bookController.allBooks();
            System.out.println("our books: ");
            books.forEach(b -> System.out.printf("\t%s\n", b));
        }

    }

    private String[] validatorBookInfo(String bookInfo) {
        Matcher m = Pattern.compile("([a-zA-Z. ]*)(.*)", Pattern.CASE_INSENSITIVE).matcher(bookInfo);
        String[] book = new String[2];
        String suffix = null;
        String prefix = null;
        if (m.find()) {
            suffix = m.group(1).trim();  // автор
            prefix = m.group(2).trim();  // назва книжки
        }
        book[0] = suffix; // дані автора
        book[1] = prefix; // назва книжки
        return book;
    }
}
