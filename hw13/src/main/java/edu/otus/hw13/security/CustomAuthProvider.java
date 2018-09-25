package edu.otus.hw13.security;

import edu.otus.hw13.entities.User;
import edu.otus.hw13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private UserService userService;

    @Autowired
    public CustomAuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.checkCredentials(login, password);
        return new UsernamePasswordAuthenticationToken(login, password, Collections.singletonList(() -> user.getRole().name()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
