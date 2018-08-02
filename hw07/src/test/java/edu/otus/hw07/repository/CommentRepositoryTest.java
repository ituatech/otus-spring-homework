package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Book;
import edu.otus.hw07.entities.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    private Comment newComment;

    @Before
    public void init() {
        newComment = Comment.builder()
                .commentText("test text comment")
                .userName("User")
                .createdAt(LocalDateTime.now())
                .book(Book.builder().title("title2").id(2).build())
                .build();
    }

    @Test
    public void insertTest() {
        commentRepository.save(newComment);
        List<Comment> comments = commentRepository.findByBookTitle(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
        assertThat(comments.get(0).getCommentText()).isEqualTo(newComment.getCommentText());
        assertThat(comments.get(0).getId()).isNotZero();
        assertThat(comments.get(0).getBook().getTitle()).isEqualTo(newComment.getBook().getTitle());
    }

    @Test
    public void removeTest() {
        commentRepository.save(newComment);
        List<Comment> comments = commentRepository.findByBookTitle(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
        commentRepository.delete(comments.get(0));
        comments = commentRepository.findByBookTitle(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(0);
    }

    @Test
    public void getByBookTest() {
        commentRepository.save(newComment);
        List<Comment> comments = commentRepository.findByBookTitle(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
    }
}
