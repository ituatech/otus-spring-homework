package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Book;
import edu.otus.hw06.entities.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
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
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
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
        commentRepository.insert(newComment);
        List<Comment> comments = commentRepository.getByBook(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
        assertThat(comments.get(0).getCommentText()).isEqualTo(newComment.getCommentText());
        assertThat(comments.get(0).getId()).isNotZero();
        assertThat(comments.get(0).getBook().getTitle()).isEqualTo(newComment.getBook().getTitle());
    }

    @Test
    public void removeTest() {
        commentRepository.insert(newComment);
        List<Comment> comments = commentRepository.getByBook(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
        commentRepository.remove(comments.get(0));
        comments = commentRepository.getByBook(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(0);
    }

    @Test
    public void getByBookTest() {
        commentRepository.insert(newComment);
        List<Comment> comments = commentRepository.getByBook(newComment.getBook().getTitle());
        assertThat(comments.size()).isEqualTo(1);
    }
}
