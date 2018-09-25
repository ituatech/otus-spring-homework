package edu.otus.hw13.service.impl;

import edu.otus.hw13.entities.User;
import edu.otus.hw13.repository.UserRepository;
import edu.otus.hw13.security.CustomAuthException;
import edu.otus.hw13.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkCredentials(String login, String password) {
        return Optional.ofNullable(userRepository.findByLoginAndPassword(login, DigestUtils.md5Hex(password)))
                .orElseThrow(() -> new CustomAuthException("Bad auth"));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public User add(String login, String password) {
        if (userRepository.findByLoginAndPassword(login, DigestUtils.md5Hex(password)) == null) {
            return userRepository.save(User.builder().login(login).password(DigestUtils.md5Hex(password)).build());
        }
        throw new RuntimeException("User already exist");
    }
}
