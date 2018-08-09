package edu.otus.hw08.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String commentText;
    private String userName;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", userName='" + userName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
