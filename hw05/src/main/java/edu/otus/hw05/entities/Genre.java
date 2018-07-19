package edu.otus.hw05.entities;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
public class Genre {
    private final int id;
    private final String genreName;

    public Genre(String genreName) {
        this.id = -1;
        this.genreName = genreName;
    }

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
