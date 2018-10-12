package edu.otus.hw12.repository;

import edu.otus.hw12.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.save(User.builder().login("admin").password("admin").build());
    }

    @Test
    public void checkSuccess() {
        User user = userRepository.findByLoginAndPassword("admin", "admin");

        assertThat(user).isNotNull();
        assertThat(user.getLogin()).isEqualTo("admin");
    }

    @Test
    public void badCredentials() {
        User user = userRepository.findByLoginAndPassword("test", "");
        assertThat(user).isNull();
    }
}
