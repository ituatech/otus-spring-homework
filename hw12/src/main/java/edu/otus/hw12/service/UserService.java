package edu.otus.hw12.service;

import edu.otus.hw12.entities.User;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
public interface UserService {
    boolean checkCredentials(String login, String password);
    User add(String login, String password);
}
