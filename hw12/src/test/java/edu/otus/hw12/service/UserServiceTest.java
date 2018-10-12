package edu.otus.hw12.service;

import edu.otus.hw12.entities.User;
import edu.otus.hw12.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {
        user = User.builder().login("admin").password(DigestUtils.md5Hex("admin")).build();
        userRepository.save(user);
    }

    @Test
    public void addSuccess() {
        when(userRepository.findByLoginAndPassword(anyString(), anyString())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(user);
        User u = userService.add("admin", "admin");
        assertThat(u).isNotNull();
    }

    @Test(expected = RuntimeException.class)
    public void addWithException() {
        when(userRepository.findByLoginAndPassword(anyString(), anyString())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        User u = userService.add("admin", "admin");
    }

    @Test
    public void authSuccess() {
        when(userRepository.findByLoginAndPassword(anyString(), anyString())).thenReturn(user);
        boolean result = userService.checkCredentials("admin", "admin");
        assertThat(result).isTrue();
    }

    @Test
    public void badAuth() {
        when(userRepository.findByLoginAndPassword(anyString(), anyString())).thenReturn(null);
        boolean result = userService.checkCredentials("admin", "admin");
        assertThat(result).isFalse();
    }
}
