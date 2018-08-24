package edu.otus.hw10.controller;

import edu.otus.hw10.entities.Book;
import edu.otus.hw10.entities.Comment;
import edu.otus.hw10.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Book> list() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}/show")
    public Book view(@PathVariable String id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/books/{id}/delete")
    public void delete(@PathVariable String id) {
        bookService.remove(new Book() {{setId(id);}});
    }

    @PostMapping("/books/add")
    public Book save(@RequestBody Book book) {
        return bookService.add(book);
    }

    @PutMapping("/books/update")
    public Book update(@RequestBody Book book) {
        System.out.println("BOOK: " + book);
        return bookService.update(book);
    }

    @PostMapping("/books/{id}/comments")
    public Book saveComment(@RequestBody Comment comment, @PathVariable String id) {
        return bookService.addComment(id, comment);
    }
}
