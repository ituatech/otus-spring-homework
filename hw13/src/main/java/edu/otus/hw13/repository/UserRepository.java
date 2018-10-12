package edu.otus.hw13.repository;

import edu.otus.hw13.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByLoginAndPassword(String login, String password);
}
