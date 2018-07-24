package edu.otus.hw06.service;

import edu.otus.hw06.entities.Genre;
import edu.otus.hw06.repository.GenreRepository;
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
    private GenreRepository genreRepository;

    @Test
    public void addTest() {
        Genre newGenre = Genre.builder().genreName("New test genre").build();
        when(genreRepository.getByName(newGenre.getGenreName())).thenReturn(newGenre);
        genreService.add(newGenre);
        Genre genreAfterInsert = genreService.getByGenreName(newGenre.getGenreName());
        assertThat(genreAfterInsert).isNotNull();
        assertThat(genreAfterInsert.getGenreName()).isEqualTo(newGenre.getGenreName());
    }

    @Test
    public void removeTest() {
        Genre genre = Genre.builder().genreName("New test genre").build();
        when(genreRepository.getByName(genre.getGenreName())).thenReturn(null);
        genreService.add(genre);
        genreService.remove(genre);
        Genre genreAfterRemove = genreService.getByGenreName(genre.getGenreName());
        assertThat(genreAfterRemove).isNull();
    }

    @Test
    public void getExistAuthorTest() {
        when(genreRepository.getByName("Exist")).thenReturn(Genre.builder().genreName("Exist").build());
        assertThat(genreService.getByGenreName("Exist").getGenreName()).isEqualTo("Exist");
    }

    @Test
    public void getNotExistAuthorTest() {
        when(genreRepository.getByName("Not Exist")).thenReturn(null);
        assertThat(genreService.getByGenreName("Not Exist")).isNull();
    }

    @Test
    public void getAllTest() {
        when(genreRepository.getAll()).thenReturn(Collections.singletonList(Genre.builder().genreName("Genre").build()));
        assertThat(genreService.getAll()).isNotNull();
        assertThat(genreService.getAll().size()).isEqualTo(1);
        assertThat(genreService.getAll().get(0).getGenreName()).isEqualTo("Genre");
    }

}
