package edu.otus.hw05.shell;

import edu.otus.hw05.entities.Author;
import edu.otus.hw05.entities.Book;
import edu.otus.hw05.entities.Genre;
import edu.otus.hw05.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@ShellComponent
public class BookShell {

    private final BookService bookService;

    @Autowired
    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Добавить книгу
     * @param title - название
     * @param description - примечание
     * @param authors - имена авторов, разделенные запятой, без пробелов до и послезапятой
     * @param genres - названия жанров, разделенные запятой, без пробелов до и послезапятой
     */

    @ShellMethod("add book")
    public void add_book(@ShellOption String title,
                         @ShellOption(defaultValue = "") String description,
                         @ShellOption String authors,
                         @ShellOption String genres) {
        List<Genre> genreList = Arrays.stream(genres.split(",")).map(Genre::new).collect(Collectors.toList());
        List<Author> authorList = Arrays.stream(authors.split(",")).map(Author::new).collect(Collectors.toList());
        bookService.add(new Book(title, description, authorList, genreList));
    }

    //remove_book --title "new book"
    @ShellMethod("remove book")
    public void remove_book(@ShellOption String title) {
        bookService.remove(new Book(title, null));
    }

    @ShellMethod("get all book")
    public void get_all_books() {
        bookService.getAll().forEach(System.out::println);
    }

    @ShellMethod("get by title")
    public void get_book(@ShellOption String title) {
        System.out.println(bookService.getByTitle(title));
    }
}
