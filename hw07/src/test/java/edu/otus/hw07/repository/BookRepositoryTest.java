package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Author;
import edu.otus.hw07.entities.Book;
import edu.otus.hw07.entities.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getByTitleTest() {
        Book book = bookRepository.findByTitle("title1").get();
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("title1");
    }

    @Test
    public void getAllTest() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void insertTest() {
        Book newBook = Book.builder()
                .title("test title")
                .decription("test descr")
                .authors(Collections.singletonList(Author.builder().authorName("masha").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("fiction").build()))
                .build();

        bookRepository.save(newBook);
        assertThat(bookRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void deleteTest() {
        Book newBook = Book.builder()
                .id(1)
                .title("title1")
                .decription("test descr")
                .authors(Collections.singletonList(Author.builder().authorName("masha").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("fiction").build()))
                .comments(Collections.EMPTY_LIST)
                .build();

        bookRepository.delete(newBook);
        assertThat(bookRepository.findAll().size()).isEqualTo(1);
    }
}
