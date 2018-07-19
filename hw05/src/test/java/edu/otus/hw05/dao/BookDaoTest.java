package edu.otus.hw05.dao;

import edu.otus.hw05.entities.Author;
import edu.otus.hw05.entities.Book;
import edu.otus.hw05.entities.Genre;
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
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void getByTitleTest() {
        Book book = bookDao.getByTitle("title1");
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("title1");
    }

    @Test
    public void getAllTest() {
        List<Book> books = bookDao.getAll();
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void insertTest() {
        Book newBook = new Book("test title",
                "test descr",
                Collections.singletonList(new Author("masha")),
                Collections.singletonList(new Genre("fiction"))
        );
        bookDao.insert(newBook);
        assertThat(bookDao.getAll().size()).isEqualTo(3);
    }

    @Test
    public void deleteTest() {
        Book newBook = new Book("title1",
                "test descr",
                Collections.singletonList(new Author(1, "")),
                Collections.singletonList(new Genre(1, ""))
        );
        bookDao.remove(newBook);
        assertThat(bookDao.getAll().size()).isEqualTo(1);
    }
}
