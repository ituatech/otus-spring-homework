package edu.otus.hw05.dao.impl;

import edu.otus.hw05.dao.GenreDao;
import edu.otus.hw05.entities.Genre;
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
public class GenreDaoImpl implements GenreDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(Genre obj) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", obj.getGenreName());
        return jdbc.update("insert into GENRES (GENRE_NAME) values (:name)", params);
    }

    @Override
    public int remove(Genre obj) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", obj.getGenreName());
        int result = 0;
        try {
            result = jdbc.update("delete from GENRES where GENRE_NAME = :name", params);
            logger.info("Genre " + obj.getGenreName() + " removed");
        } catch (DataAccessException e) {
            logger.warn("Add new genre failed: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return jdbc.queryForObject("select * from GENRES where GENRE_NAME = :name", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new HashMap<>(), new GenreMapper());
    }

    @Override
    public List<Genre> getByBookId(int bookId) {
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        return jdbc.query("select * from book_to_genre btg left join genres g on btg.genre_id = g.id where btg.book_id = :book_id", params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String genreName = resultSet.getString("GENRE_NAME");
            return new Genre(id, genreName);
        }
    }
}
