package edu.otus.hw05.dao;

import edu.otus.hw05.entities.Genre;
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
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void getByIdTest() {
        Genre genre = genreDao.getByName("fiction");
        assertThat(genre).isNotNull();
        assertThat(genre.getGenreName()).isEqualTo("fiction");
    }

    @Test
    public void getAllTest() {
        List<Genre> genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(3);
        genres.forEach(author -> assertThat(author.getId()).isNotNull());
    }

    @Test
    public void insertTest() {
        List<Genre> genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(3);

        Genre newGenre = new Genre("Test Genre");
        genreDao.insert(newGenre);

        genres = genreDao.getAll();
        assertThat(genres.size()).isEqualTo(4);

        newGenre = genreDao.getByName("Test Genre");
        assertThat(newGenre.getId()).isEqualTo(4);
    }

    @Test
    public void getByBookIdTest() {
        List<Genre> genres = genreDao.getByBookId(2);
        assertThat(genres.size()).isEqualTo(2);
    }
}
