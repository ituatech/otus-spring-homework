package edu.otus.hw07.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */

@Getter
@Setter
@Entity
@Builder
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "description")
    private String decription;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "author_book",
            joinColumns = { @JoinColumn(name = "book_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "author_id", nullable = false, updatable = false) }
    )
    @Fetch(value = FetchMode.JOIN)
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "genre_book",
            joinColumns = { @JoinColumn(name = "book_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "genre_id", nullable = false, updatable = false) }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Genre> genres;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Comment> comments;

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
