package com.example.author_service.client;

import com.example.author_service.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", url = "http://book-service:7071")
public interface BookClient {
    @GetMapping("/books/author/{id}")
    List<BookDto> getBooksByAuthorId(@PathVariable("id") String id);
}