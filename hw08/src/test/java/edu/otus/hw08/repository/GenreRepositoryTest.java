package edu.otus.hw08.repository;

import edu.otus.hw08.entities.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    private Genre newGenre;

    @Before
    public void setUp() throws Exception {
        newGenre = Genre.builder()
                .id("ge")
                .genreName("Test Genre")
                .build();
        genreRepository.save(newGenre);
    }

    @Test
    public void getByIdTest() {
        Genre genre = genreRepository.findByGenreName("Test Genre").get();
        assertThat(genre).isNotNull();
        assertThat(genre.getGenreName()).isEqualTo("Test Genre");
    }

    @Test
    public void getAllTest() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(1);
        genres.forEach(author -> assertThat(author.getId()).isNotBlank());
    }

    @Test
    public void insertTest() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(1);

        Genre newGenre1 = Genre.builder()
                .id("test")
                .genreName("Test Genre1")
                .build();
        genreRepository.save(newGenre1);

        genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(2);

        newGenre1 = genreRepository.findByGenreName("Test Genre1").get();
        assertThat(newGenre1.getId()).isNotBlank().isEqualTo("test");
    }
}
