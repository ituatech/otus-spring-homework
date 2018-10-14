package edu.otus.hw14.repository.mongo;

import edu.otus.hw14.entity.mongo.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */
public interface PersonMongoRepository extends MongoRepository<PersonMongo, String> {
}
