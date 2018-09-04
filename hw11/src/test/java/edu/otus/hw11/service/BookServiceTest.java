package edu.otus.hw11.service;

import edu.otus.hw11.bootstrap.BooksBootStrap;
import edu.otus.hw11.entities.Author;
import edu.otus.hw11.entities.Book;
import edu.otus.hw11.entities.Genre;
import edu.otus.hw11.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BooksBootStrap booksBootStrap;

    private Book testBook;
    @Before
    public void init() {
        testBook = Book.builder()
                .title("test book")
                .description("")
                .authors(Collections.singletonList(Author.builder().authorName("test author").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("test genre").build()))
                .build();
    }

    @Test
    public void addTest() {
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Mono.just(testBook));
        when(bookRepository.save(testBook)).thenReturn(Mono.just(testBook));
        bookService.add(testBook).block();
        Book bookAfterInsert = bookService.getByTitle(testBook.getTitle()).block();
        assertThat(bookAfterInsert).isNotNull();
        assertThat(bookAfterInsert.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test
    public void removeTest() {
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Mono.empty());
        when(bookRepository.save(testBook)).thenReturn(Mono.just(testBook));
        when(bookRepository.delete(testBook)).thenReturn(Mono.empty());
        bookService.add(testBook).block();
        bookService.remove(testBook).block();
        Book bookAfterRemove = bookRepository.findByTitle(testBook.getTitle()).block();
        assertThat(bookAfterRemove).isNull();
    }

    @Test
    public void getByTitleTest() {
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Mono.just(testBook));
        Book book = bookService.getByTitle(testBook.getTitle()).block();
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test
    public void getAllTest() {
        when(bookRepository.findAll()).thenReturn(Flux.just(testBook));
        assertThat(bookService.getAll().collectList().block()).isNotNull();
        assertThat(bookService.getAll().collectList().block().size()).isEqualTo(1);
    }
}
