package edu.otus.hw16.bootstrap;


import edu.otus.hw16.repository.BookRepository;
import edu.otus.hw16.entities.Author;
import edu.otus.hw16.entities.Book;
import edu.otus.hw16.entities.Comment;
import edu.otus.hw16.entities.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nik Bespalov on 15/08/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@Component
public class BooksBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;

    @Autowired
    public BooksBootStrap(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookRepository.saveAll(initBooks());
    }

    private List<Book> initBooks() {
        List<Book> books = new ArrayList<>();

        books.add(Book.builder()
                .id("1")
                .title("Comedy book")
                .description("funny book")
                .authors(Arrays.asList(Author.builder().authorName("Vasya").build()))
                .genres(Arrays.asList(Genre.builder().genreName("comedy").build(),
                        Genre.builder().genreName("thriller").build()))
                .comments(Arrays.asList(Comment.builder().commentText("comment 1").userName("Nik").createdAt(LocalDateTime.now()).build(),
                        Comment.builder().commentText("comment 2").userName("Nik").createdAt(LocalDateTime.now()).build()))
                .build()
        );

        books.add(Book.builder()
                .id("2")
                .title("Fiction book")
                .description("great book")
                .authors(Arrays.asList(Author.builder().authorName("Petya").build(),
                        Author.builder().authorName("Alex Sergeevich").build()))
                .genres(Arrays.asList(Genre.builder().genreName("comedy").build()))
                .comments(Arrays.asList(Comment.builder().commentText("comment 1").userName("Nik").createdAt(LocalDateTime.now()).build(),
                        Comment.builder().commentText("comment 2").userName("Nik").createdAt(LocalDateTime.now()).build()))
                .build()
        );

        return books;
    }
}
