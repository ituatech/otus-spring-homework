package edu.otus.hw13.service;

import edu.otus.hw13.entities.User;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
public interface UserService {
    User checkCredentials(String login, String password);
    User add(String login, String password);
}
