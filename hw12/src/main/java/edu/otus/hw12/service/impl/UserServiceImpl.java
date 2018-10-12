package edu.otus.hw12.service.impl;

import edu.otus.hw12.entities.User;
import edu.otus.hw12.repository.UserRepository;
import edu.otus.hw12.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean checkCredentials(String login, String password) {
        return userRepository.findByLoginAndPassword(login, DigestUtils.md5Hex(password)) != null;
    }

    @Override
    public User add(String login, String password) {
        if (userRepository.findByLoginAndPassword(login, DigestUtils.md5Hex(password)) == null) {
            return userRepository.save(User.builder().login(login).password(DigestUtils.md5Hex(password)).build());
        }
        throw new RuntimeException("User already exist");
    }
}
