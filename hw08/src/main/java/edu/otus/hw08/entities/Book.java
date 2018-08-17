package edu.otus.hw08.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {
    @Id
    private String id;
    private String title;
    private String decription;
    @DBRef
    private List<Author> authors;
    @DBRef
    private List<Genre> genres;
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", decription='" + decription + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", comments=" + comments +
                '}';
    }
}
