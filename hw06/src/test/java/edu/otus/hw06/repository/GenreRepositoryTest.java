package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Genre;
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
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void getByIdTest() {
        Genre genre = genreRepository.getByName("fiction");
        assertThat(genre).isNotNull();
        assertThat(genre.getGenreName()).isEqualTo("fiction");
    }

    @Test
    public void getAllTest() {
        List<Genre> genres = genreRepository.getAll();
        assertThat(genres.size()).isEqualTo(3);
        genres.forEach(author -> assertThat(author.getId()).isNotNull());
    }

    @Test
    public void insertTest() {
        List<Genre> genres = genreRepository.getAll();
        assertThat(genres.size()).isEqualTo(3);

        Genre newGenre = Genre.builder()
                .genreName("Test Genre")
                .build();
        genreRepository.insert(newGenre);

        genres = genreRepository.getAll();
        assertThat(genres.size()).isEqualTo(4);

        newGenre = genreRepository.getByName("Test Genre");
        assertThat(newGenre.getId()).isEqualTo(4);
    }
}
