package edu.otus.hw11.controller;

import edu.otus.hw11.entities.Book;
import edu.otus.hw11.entities.Comment;
import edu.otus.hw11.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Nik Bespalov on 18/08/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/books"})
    public Flux<Book> list() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}/show")
    public Mono<Book> view(@PathVariable String id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/books/{id}/delete")
    public Mono<Void> delete(@PathVariable String id) {
        return bookService.remove(new Book() {{setId(id);}});
    }

    @PostMapping("/books/add")
    public Mono<Book> save(@RequestBody Book book) {
        return bookService.add(book);
    }

    @PutMapping("/books/update")
    public Mono<Book> update(@RequestBody Book book) {
        return bookService.update(book);
    }

    @PostMapping("/books/{id}/comments")
    public Mono<Book> saveComment(@RequestBody Comment comment, @PathVariable String id) {
        return bookService.addComment(id, comment);
    }
}
