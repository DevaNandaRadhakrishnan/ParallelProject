package com.example.author_service.controller;

import com.example.author_service.client.BookClient;
import com.example.author_service.dto.BookDto;
import com.example.author_service.entity.Author;
import com.example.author_service.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookClient bookClient;

    @GetMapping
    public List<Author> getAllAuthors() {

        return authorRepository.findAll();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {

        return authorRepository.save(author);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> findBooksByAuthorId(@PathVariable String id){
        return ResponseEntity.ok(bookClient.getBooksByAuthorId(id));
    }
}

