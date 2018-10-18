package edu.otus.hw16.service;

import edu.otus.hw16.entities.Author;
import edu.otus.hw16.entities.Book;
import edu.otus.hw16.entities.Genre;
import edu.otus.hw16.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

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
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Optional.of(testBook));
        bookService.add(testBook);
        Book bookAfterInsert = bookService.getByTitle(testBook.getTitle());
        assertThat(bookAfterInsert).isNotNull();
        assertThat(bookAfterInsert.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeTest() {
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Optional.empty());
        bookService.add(testBook);
        bookService.remove(testBook);
        Book bookAfterRemove = bookRepository.findByTitle(testBook.getTitle()).get();
        assertThat(bookAfterRemove).isNull();
    }

    @Test
    public void getByTitleTest() {
        when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(Optional.of(testBook));
        Book book = bookService.getByTitle(testBook.getTitle());
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test
    public void getAllTest() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(testBook));
        assertThat(bookService.getAll()).isNotNull();
        assertThat(bookService.getAll().size()).isEqualTo(1);
    }
}
