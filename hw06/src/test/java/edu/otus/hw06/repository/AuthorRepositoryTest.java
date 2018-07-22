package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void getByNameTest() {
        Author author = authorRepository.getByName("vasya");
        assertThat(author).isNotNull();
        assertThat(author.getAuthorName()).isEqualTo("vasya");
    }

    @Test
    public void getAllTest() {
        List<Author> authors = authorRepository.getAll();
        assertThat(authors.size()).isEqualTo(4);
        authors.forEach(author -> assertThat(author.getId()).isNotNull());
    }

    @Test
    public void insertTest() {
        List<Author> authors = authorRepository.getAll();
        assertThat(authors.size()).isEqualTo(4);

        Author newAuthor = Author.builder()
                .authorName("Test Name")
                .build();
        authorRepository.insert(newAuthor);

        authors = authorRepository.getAll();
        assertThat(authors.size()).isEqualTo(5);

        newAuthor = authorRepository.getByName("Test Name");
        assertThat(newAuthor.getId()).isEqualTo(5);
    }
}
