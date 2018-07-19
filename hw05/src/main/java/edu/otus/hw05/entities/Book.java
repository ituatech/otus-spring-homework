package edu.otus.hw05.entities;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
public class Book {
    private final int id;
    private final String title;
    private final String decription;
    private final List<Author> authors;
    private final List<Genre> genres;

    public Book(String title, String decription) {
        this.id = -1;
        this.title = title;
        this.decription = decription;
        this.authors = null;
        this.genres = null;
    }

    public Book(int id, String title, String decription) {
        this.id = id;
        this.title = title;
        this.decription = decription;
        this.authors = null;
        this.genres = null;
    }

    public Book(Book book, List<Author> authors, List<Genre> genres) {
        this.id = book.id;
        this.title = book.title;
        this.decription = book.decription;
        this.authors = authors;
        this.genres = genres;
    }

    public Book(String title, String decription, List<Author> authors, List<Genre> genres) {
        this.id = -1;
        this.title = title;
        this.decription = decription;
        this.authors = authors;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getDecription() {
        return decription;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", decription='" + decription + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
