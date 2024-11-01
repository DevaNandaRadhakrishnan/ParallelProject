package com.example.author_service.dto;

public class BookDto {
    private String id;
    private String title;

    public BookDto() {
    }

    public BookDto(String id, String title, String authorId) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
