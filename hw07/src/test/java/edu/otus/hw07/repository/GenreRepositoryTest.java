package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getByIdTest() {
        Genre genre = genreRepository.findByGenreName("fiction").get();
        assertThat(genre).isNotNull();
        assertThat(genre.getGenreName()).isEqualTo("fiction");
    }

    @Test
    public void getAllTest() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(3);
        genres.forEach(author -> assertThat(author.getId()).isNotNull());
    }

    @Test
    public void insertTest() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(3);

        Genre newGenre = Genre.builder()
                .genreName("Test Genre")
                .build();
        genreRepository.save(newGenre);

        genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(4);

        newGenre = genreRepository.findByGenreName("Test Genre").get();
        assertThat(newGenre.getId()).isEqualTo(4);
    }
}
