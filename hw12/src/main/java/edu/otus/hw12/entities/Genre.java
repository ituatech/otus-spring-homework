package edu.otus.hw12.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private String genreName;

    @Override
    public String toString() {
        return "Genre{" +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
