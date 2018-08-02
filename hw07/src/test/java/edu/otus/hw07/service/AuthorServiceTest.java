package edu.otus.hw07.service;

import edu.otus.hw07.entities.Author;
import edu.otus.hw07.repository.AuthorRepository;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    public void addTest() {
        Author newAuthor = Author.builder()
                .authorName("test author")
                .build();
        when(authorRepository.findByAuthorName(newAuthor.getAuthorName())).thenReturn(Optional.of(newAuthor));
        authorService.add(newAuthor);
        Author newAuthorAfterAdd = authorService.getByName(newAuthor.getAuthorName());
        assertThat(newAuthorAfterAdd).isNotNull();
        assertThat(newAuthor.getAuthorName()).isEqualTo(newAuthorAfterAdd.getAuthorName());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeTest() {
        Author existAuthor = Author.builder()
                .authorName("New test author")
                .build();
        when(authorRepository.findByAuthorName(existAuthor.getAuthorName())).thenReturn(Optional.empty());
        authorService.add(existAuthor);
        authorService.remove(existAuthor);
        Author existAuthorAfterRemove = authorService.getByName(existAuthor.getAuthorName());
        assertThat(existAuthorAfterRemove).isNull();
    }

    @Test
    public void getExistAuthorTest() {
        when(authorRepository.findByAuthorName("Exist")).thenReturn(Optional.of(Author.builder().authorName("Exist").build()));
        assertThat(authorService.getByName("Exist").getAuthorName()).isEqualTo("Exist");
    }

    @Test(expected = NoSuchElementException.class)
    public void getNotExistAuthorTest() {
        when(authorRepository.findByAuthorName("Not Exist")).thenReturn(Optional.empty());
        assertThat(authorService.getByName("Not Exist")).isNull();
    }

    @Test
    public void getAllTest() {
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(Author.builder().authorName("Author").build()));
        assertThat(authorService.getAll()).isNotNull();
        assertThat(authorService.getAll().size()).isEqualTo(1);
        assertThat(authorService.getAll().get(0).getAuthorName()).isEqualTo("Author");
    }
}
