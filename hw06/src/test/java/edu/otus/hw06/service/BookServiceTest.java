package edu.otus.hw06.service;

import edu.otus.hw06.entities.Author;
import edu.otus.hw06.entities.Book;
import edu.otus.hw06.entities.Genre;
import edu.otus.hw06.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Nik Bespalov on 19/07/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
                .decription("")
                .authors(Collections.singletonList(Author.builder().authorName("test author").build()))
                .genres(Collections.singletonList(Genre.builder().genreName("test genre").build()))
                .build();
    }

    @Test
    public void addTest() {
        when(bookRepository.getByTitle(testBook.getTitle())).thenReturn(testBook);
        bookService.add(testBook);
        Book bookAfterInsert = bookService.getByTitle(testBook.getTitle());
        assertThat(bookAfterInsert).isNotNull();
        assertThat(bookAfterInsert.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test
    public void removeTest() {
        when(bookRepository.getByTitle(testBook.getTitle())).thenReturn(null);
        bookService.add(testBook);
        bookService.remove(testBook);
        Book bookAfterRemove = bookRepository.getByTitle(testBook.getTitle());
        assertThat(bookAfterRemove).isNull();
    }

    @Test
    public void getByTitleTest() {
        when(bookRepository.getByTitle(testBook.getTitle())).thenReturn(testBook);
        Book book = bookService.getByTitle(testBook.getTitle());
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(testBook.getTitle());
    }

    @Test
    public void getAllTest() {
        when(bookRepository.getAll()).thenReturn(Collections.singletonList(testBook));
        assertThat(bookService.getAll()).isNotNull();
        assertThat(bookService.getAll().size()).isEqualTo(1);
    }
}
