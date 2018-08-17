package edu.otus.hw08.repository;

import edu.otus.hw08.entities.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void getByNameTest() {
        Author newAuthor = Author.builder()
                .authorName("vasya")
                .build();
        authorRepository.save(newAuthor);
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
        assertThat(authors.size()).isEqualTo(0);
    }

    @Test
    public void insertTest() {
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(0);

        Author newAuthor = Author.builder()
                .authorName("Test Name")
                .build();
        authorRepository.save(newAuthor);

        authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(1);

        newAuthor = authorRepository.findByAuthorName("Test Name").get();
        assertThat(newAuthor.getAuthorName()).isEqualTo("Test Name");
    }
}
