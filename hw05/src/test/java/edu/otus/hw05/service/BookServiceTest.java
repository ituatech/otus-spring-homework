package edu.otus.hw05.service;

import edu.otus.hw05.dao.BookDao;
import edu.otus.hw05.entities.Author;
import edu.otus.hw05.entities.Book;
import edu.otus.hw05.entities.Genre;
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
    private BookDao bookDao;

    private Book testBook;
    @Before
    public void init() {
        testBook = new Book("test book", "",
                Collections.singletonList(new Author("test author")),
                Collections.singletonList(new Genre("test genre")));
    }

    @Test
    public void addTest() {
        when(bookDao.insert(testBook)).thenReturn(1);
        assertThat(bookService.add(testBook)).isTrue();
    }

    @Test
    public void removeTest() {
        when(bookDao.remove(testBook)).thenReturn(1);
        assertThat(bookService.remove(testBook)).isTrue();
    }

    @Test
    public void getByTitleTest() {
        when(bookDao.getByTitle("test book")).thenReturn(testBook);
        Book book = bookService.getByTitle("test book");
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("test book");
    }

    @Test
    public void getAllTest() {
        when(bookDao.getAll()).thenReturn(Collections.singletonList(testBook));
        assertThat(bookDao.getAll()).isNotNull();
        assertThat(bookDao.getAll().size()).isEqualTo(1);
    }
}
