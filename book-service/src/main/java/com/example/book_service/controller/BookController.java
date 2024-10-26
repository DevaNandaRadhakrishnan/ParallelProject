package com.example.book_service.controller;

import com.example.book_service.entity.Book;
import com.example.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable String authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}

