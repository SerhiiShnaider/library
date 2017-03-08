package com.botscrew.task.dto;

import com.botscrew.task.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class DtoMaker {
    public static List<BookDto> booksToBooksDto(List<Book> books) {
        List<BookDto> studentsDto = new ArrayList<>();
        for (Book book : books) {
            studentsDto.add(new BookDto(book.getName(), book.getAuthorName()));
        }
        return studentsDto;
    }
}
