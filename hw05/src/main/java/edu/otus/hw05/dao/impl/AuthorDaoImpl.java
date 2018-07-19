package edu.otus.hw05.dao.impl;

import edu.otus.hw05.dao.AuthorDao;
import edu.otus.hw05.entities.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(Author obj) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", obj.getAuthorName());
        int result = 0;
        try {
            result = jdbc.update("insert into AUTHORS (AUTHOR_NAME) values (:name)", params);
            logger.info("Author " + obj.getAuthorName() + " added");
        } catch (DataAccessException e) {
            logger.warn("Add new author failed: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int remove(Author obj) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", obj.getAuthorName());
        int result = 0;
        try {
            result = jdbc.update("delete from AUTHORS where AUTHOR_NAME = :name", params);
            logger.info("Author " + obj.getAuthorName() + " removed");
        } catch (DataAccessException e) {
            logger.warn("Add new author failed: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return jdbc.queryForObject("select * from authors where AUTHOR_NAME = :name", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new HashMap<>(), new AuthorMapper());
    }

    @Override
    public List<Author> getByBookId(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        return jdbc.query("select * from book_to_author bta left join authors a on bta.author_id = a.id where bta.book_id = :book_id", params, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String authorName = resultSet.getString("AUTHOR_NAME");
            return new Author(id, authorName);
        }
    }
}
