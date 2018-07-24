package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Author;
import edu.otus.hw06.entities.Book;
import edu.otus.hw06.entities.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
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
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getByTitleTest() {
        Book book = bookRepository.getByTitle("title1");
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("title1");
    }

    @Test
    public void getAllTest() {
        List<Book> books = bookRepository.getAll();

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

        bookRepository.insert(newBook);
        assertThat(bookRepository.getAll().size()).isEqualTo(3);
    }

    @Test
    public void deleteTest() {
        Book newBook = Book.builder()
                .id(1)
                .title("title1")
                .decription("test descr")
                .authors(Collections.singletonList(Author.builder().authorName("masha").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("fiction").build()))
                .build();

        bookRepository.remove(newBook);
        assertThat(bookRepository.getAll().size()).isEqualTo(1);
    }
}
