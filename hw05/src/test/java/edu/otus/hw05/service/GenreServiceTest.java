package edu.otus.hw05.service;

import edu.otus.hw05.dao.GenreDao;
import edu.otus.hw05.entities.Genre;
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
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreDao genreDao;

    @Test
    public void addTest() {
        Genre newGenre = new Genre("New test genre");
        Genre newGenreWithExistName = new Genre("New test genre");
        when(genreDao.insert(newGenre)).thenReturn(1);
        when(genreDao.insert(newGenreWithExistName)).thenReturn(0);
        assertThat(genreService.add(newGenre)).isTrue();
        assertThat(genreService.add(newGenreWithExistName)).isFalse();
    }

    @Test
    public void removeTest() {
        Genre existGenre = new Genre("New test genre");
        Genre notExistGenre = new Genre("New test genre");
        when(genreDao.remove(existGenre)).thenReturn(1);
        when(genreDao.remove(notExistGenre)).thenReturn(0);
        assertThat(genreService.remove(existGenre)).isTrue();
        assertThat(genreService.remove(notExistGenre)).isFalse();
    }

    @Test
    public void getExistAuthorTest() {
        when(genreDao.getByName("Exist")).thenReturn(new Genre("Exist"));
        assertThat(genreService.getByGenreName("Exist").getGenreName()).isEqualTo("Exist");
    }

    @Test
    public void getNotExistAuthorTest() {
        when(genreDao.getByName("Not Exist")).thenReturn(null);
        assertThat(genreService.getByGenreName("Not Exist")).isNull();
    }

    @Test
    public void getAllTest() {
        when(genreDao.getAll()).thenReturn(Collections.singletonList(new Genre("Genre")));
        assertThat(genreService.getAll()).isNotNull();
        assertThat(genreService.getAll().size()).isEqualTo(1);
        assertThat(genreService.getAll().get(0).getGenreName()).isEqualTo("Genre");
    }

}
