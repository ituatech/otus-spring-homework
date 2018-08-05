package edu.otus.hw07.service;

import edu.otus.hw07.entities.Genre;
import edu.otus.hw07.repository.GenreRepository;
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
        when(genreRepository.findByGenreName(newGenre.getGenreName())).thenReturn(Optional.of(newGenre));
        genreService.add(newGenre);
        Genre genreAfterInsert = genreService.getByGenreName(newGenre.getGenreName());
        assertThat(genreAfterInsert).isNotNull();
        assertThat(genreAfterInsert.getGenreName()).isEqualTo(newGenre.getGenreName());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeTest() {
        Genre genre = Genre.builder().genreName("New test genre").build();
        when(genreRepository.findByGenreName(genre.getGenreName())).thenReturn(Optional.empty());
        genreService.add(genre);
        genreService.remove(genre);
        Genre genreAfterRemove = genreService.getByGenreName(genre.getGenreName());
        assertThat(genreAfterRemove).isNull();
    }

    @Test
    public void getExistAuthorTest() {
        when(genreRepository.findByGenreName("Exist")).thenReturn(Optional.of(Genre.builder().genreName("Exist").build()));
        assertThat(genreService.getByGenreName("Exist").getGenreName()).isEqualTo("Exist");
    }

    @Test(expected = NoSuchElementException.class)
    public void getNotExistAuthorTest() {
        when(genreRepository.findByGenreName("Not Exist")).thenReturn(Optional.empty());
        assertThat(genreService.getByGenreName("Not Exist")).isNull();
    }

    @Test
    public void getAllTest() {
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(Genre.builder().genreName("Genre").build()));
        assertThat(genreService.getAll()).isNotNull();
        assertThat(genreService.getAll().size()).isEqualTo(1);
        assertThat(genreService.getAll().get(0).getGenreName()).isEqualTo("Genre");
    }

}
