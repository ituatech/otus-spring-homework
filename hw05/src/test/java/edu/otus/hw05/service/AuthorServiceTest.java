package edu.otus.hw05.service;

import edu.otus.hw05.dao.AuthorDao;
import edu.otus.hw05.entities.Author;
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
public class AuthorServiceTest {

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorService authorService;

    @Test
    public void addTest() {
        Author newAuthor = new Author("New test author");
        Author newAuthorWithExistName = new Author("New test author");
        when(authorDao.insert(newAuthor)).thenReturn(1);
        when(authorDao.insert(newAuthorWithExistName)).thenReturn(0);
        assertThat(authorService.add(newAuthor)).isTrue();
        assertThat(authorService.add(newAuthorWithExistName)).isFalse();
    }

    @Test
    public void removeTest() {
        Author existAuthor = new Author("New test author");
        Author notExistAuthor = new Author("New test author");
        when(authorDao.remove(existAuthor)).thenReturn(1);
        when(authorDao.remove(notExistAuthor)).thenReturn(0);
        assertThat(authorService.remove(existAuthor)).isTrue();
        assertThat(authorService.remove(notExistAuthor)).isFalse();
    }

    @Test
    public void getExistAuthorTest() {
        when(authorDao.getByName("Exist")).thenReturn(new Author("Exist"));
        assertThat(authorService.getByName("Exist").getAuthorName()).isEqualTo("Exist");
    }

    @Test
    public void getNotExistAuthorTest() {
        when(authorDao.getByName("Not Exist")).thenReturn(null);
        assertThat(authorService.getByName("Not Exist")).isNull();
    }

    @Test
    public void getAllTest() {
        when(authorDao.getAll()).thenReturn(Collections.singletonList(new Author("Author")));
        assertThat(authorService.getAll()).isNotNull();
        assertThat(authorService.getAll().size()).isEqualTo(1);
        assertThat(authorService.getAll().get(0).getAuthorName()).isEqualTo("Author");
    }
}
