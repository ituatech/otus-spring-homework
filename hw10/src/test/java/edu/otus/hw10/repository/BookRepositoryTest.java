package edu.otus.hw10.repository;

import edu.otus.hw10.entities.Author;
import edu.otus.hw10.entities.Book;
import edu.otus.hw10.entities.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book newBook;

    @Before
    public void setUp() {
        newBook = Book.builder()
                .id("1")
                .title("test title")
                .description("test descr")
                .authors(Collections.singletonList(Author.builder().authorName("masha").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("fiction").build()))
                .build();
    }

    @Test
    public void getByTitleTest() {
        bookRepository.save(newBook);
        Book book = bookRepository.findByTitle("test title").get();
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("test title");
    }

    @Test
    public void getAllTest() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    public void insertTest() {
        bookRepository.save(newBook);
        assertThat(bookRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void deleteTest() {
        bookRepository.save(newBook);
        assertThat(bookRepository.findAll().size()).isEqualTo(1);
        bookRepository.delete(newBook);
        assertThat(bookRepository.findAll().size()).isEqualTo(0);
    }
}
