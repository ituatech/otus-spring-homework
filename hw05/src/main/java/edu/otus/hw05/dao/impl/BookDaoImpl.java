package edu.otus.hw05.dao.impl;

import edu.otus.hw05.dao.AuthorDao;
import edu.otus.hw05.dao.BookDao;
import edu.otus.hw05.dao.GenreDao;
import edu.otus.hw05.entities.Author;
import edu.otus.hw05.entities.Book;
import edu.otus.hw05.entities.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
@Repository
public class BookDaoImpl implements BookDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoImpl(NamedParameterJdbcOperations jdbc, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = jdbc;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        return setAuthorsAndGenres(jdbc.queryForObject("select * from books where title = :title", params, new BookMapper()));
    }

    @Override
    public int insert(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("descr", book.getDecription());
        int result = jdbc.update("insert into BOOKS (title, description) values (:title, :descr)", params);
        int bookId = getByTitle(book.getTitle()).getId();

        book.getAuthors().forEach(author -> {
            Author authorWithId = checkAuthor(book, author);
            if (authorWithId == null) return;
            Map<String, Object> authorParams = new HashMap<>();
            authorParams.put("author_id", authorWithId.getId());
            authorParams.put("book_id", bookId);
            jdbc.update("insert into BOOK_TO_AUTHOR (BOOK_ID, AUTHOR_ID) values (:book_id, :author_id)", authorParams);
        });

        book.getGenres().forEach(genre -> {
            Genre genreWithId = checkGenre(book, genre);
            if (genreWithId == null) return;
            Map<String, Object> genreParams = new HashMap<>();
            genreParams.put("genre_id", genreWithId.getId());
            genreParams.put("book_id", bookId);
            jdbc.update("insert into BOOK_TO_GENRE (BOOK_ID, GENRE_ID) values (:book_id, :genre_id)", genreParams);
        });
        return result;
    }

    private Genre checkGenre(Book book, Genre genre) {
        Genre genreWithId;
        try {
            genreWithId = genreDao.getByName(genre.getGenreName());
            return genreWithId;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Genre with name " + genre.getGenreName() + " does not exist");
            rollback(book);
            return null;
        }
    }

    private Author checkAuthor(Book book, Author author) {
        Author authorWithId;
        try {
            authorWithId = authorDao.getByName(author.getAuthorName());
            return authorWithId;
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Author with name " + author.getAuthorName() + " does not exist");
            rollback(book);
            return null;
        }
    }

    private void rollback(Book book) {
        Map<String, Object> removeParams = new HashMap<>();
        removeParams.put("title", book.getTitle());
        jdbc.update("delete from BOOKS where title = :title", removeParams);
    }

    @Override
    public int remove(Book obj) {
        int bookId = getByTitle(obj.getTitle()).getId();

        Map<String, Object> authorParams = new HashMap<>();
        authorParams.put("book_id", bookId);
        jdbc.update("delete from BOOK_TO_AUTHOR where BOOK_ID = :book_id", authorParams);

        Map<String, Object> genreParams = new HashMap<>();
        genreParams.put("book_id", bookId);
        jdbc.update("delete from BOOK_TO_GENRE where BOOK_ID = :book_id", genreParams);

        Map<String, Object> params = new HashMap<>();
        params.put("title", obj.getTitle());
        return jdbc.update("delete from BOOKS where title = :title", params);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = jdbc.query("select * from books", new HashMap<>(), new BookMapper());
        books = books.stream().map(this::setAuthorsAndGenres).collect(Collectors.toList());
        return books;
    }

    private Book setAuthorsAndGenres(Book book) {
        List<Genre> genres = genreDao.getByBookId(book.getId());
        List<Author> authors = authorDao.getByBookId(book.getId());
        return new Book(book, authors, genres);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            return new Book(id, title, description);
        }
    }
}
