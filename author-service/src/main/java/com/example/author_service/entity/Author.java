package com.example.author_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Set;

@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String name;

    private Set<String> bookIds;

    public Author() {
    }

    public Author(String id, String name, Set<String> bookIds) {
        this.id = id;
        this.name = name;
        this.bookIds = bookIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<String> bookIds) {
        this.bookIds = bookIds;
    }
}

