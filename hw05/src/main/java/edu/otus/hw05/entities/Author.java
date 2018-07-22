package edu.otus.hw05.entities;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
public class Author {

    private final int id;
    private final String authorName;

    public Author(String name) {
        this.id = -1;
        this.authorName = name;
    }

    public Author(int id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
