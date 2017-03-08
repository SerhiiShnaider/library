package com.botscrew.task.dto;

public class BookDto {

    private String name;
    private String authorName;

    public BookDto() {
    }

    public BookDto(String name, String authorName) {
        this.name = name;
        this.authorName = authorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return String.format("%s \"%s\"", authorName, name);
    }
}


