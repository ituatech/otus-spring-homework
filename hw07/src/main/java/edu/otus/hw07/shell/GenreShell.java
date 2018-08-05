package edu.otus.hw07.shell;

import edu.otus.hw07.entities.Genre;
import edu.otus.hw07.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    @Autowired
    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod("add genre")
    public void add_genre(@ShellOption String name) {
        genreService.add(Genre.builder().genreName(name).build());
    }

    @ShellMethod("remove author")
    public void remove_genre(@ShellOption String name) {
        genreService.remove(Genre.builder().genreName(name).build());
    }

    @ShellMethod("get all authors")
    public void get_all_genres() {
        genreService.getAll().forEach(System.out::println);
    }

    @ShellMethod("get by name")
    public void get_genre(@ShellOption String name) {
        System.out.println(genreService.getByGenreName(name));
    }
}
