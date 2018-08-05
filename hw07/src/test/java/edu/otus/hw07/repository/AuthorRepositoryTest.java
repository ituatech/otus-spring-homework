package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void getByNameTest() {
        Author author = authorRepository.findByAuthorName("vasya").get();
        assertThat(author).isNotNull();
        assertThat(author.getAuthorName()).isEqualTo("vasya");
    }

    @Test(expected = NoSuchElementException.class)
    public void getByNameIfNameNotExistsTest() {
        authorRepository.findByAuthorName("NotExists").get();
    }

    @Test
    public void getAllTest() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(4);
        authors.forEach(author -> assertThat(author.getId()).isNotNull());
    }

    @Test
    public void insertTest() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(4);

        Author newAuthor = Author.builder()
                .authorName("Test Name")
                .build();
        authorRepository.save(newAuthor);

        authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(5);

        newAuthor = authorRepository.findByAuthorName("Test Name").get();
        assertThat(newAuthor.getId()).isEqualTo(5);
    }
}
