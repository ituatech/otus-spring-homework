package edu.otus.hw13.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
public class CustomAuthException extends AuthenticationException {
    public CustomAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomAuthException(String msg) {
        super(msg);
    }
}
