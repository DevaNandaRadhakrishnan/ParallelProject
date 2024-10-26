package com.example.author_service.controller;

import com.example.author_service.dto.BookDto;
import com.example.author_service.entity.Author;
import com.example.author_service.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping
    public List<Author> getAllAuthors() {

        return authorRepository.findAll();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {

        return authorRepository.save(author);
    }

    @GetMapping("/{id}/books")
    public List<BookDto> getBooksByAuthorId(@PathVariable String id) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:7078/books/author/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BookDto>>() {})
                .block();
    }
}

