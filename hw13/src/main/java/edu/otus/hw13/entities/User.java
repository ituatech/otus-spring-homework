package edu.otus.hw13.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nik Bespalov on 19/09/2018.
 *
 * @author Nik Bespalov.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    @Id
    private String id;

    private String login;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
