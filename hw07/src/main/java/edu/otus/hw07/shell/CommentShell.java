package edu.otus.hw07.shell;

import edu.otus.hw07.entities.Book;
import edu.otus.hw07.entities.Comment;
import edu.otus.hw07.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
@ShellComponent
public class CommentShell {

    private final CommentService commentService;

    @Autowired
    public CommentShell(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod("add comment")
    public void add_comment(@ShellOption String text,
                            @ShellOption String user,
                            @ShellOption String book_title) {
        commentService.add(Comment.builder()
                .commentText(text)
                .userName(user)
                .createdAt(LocalDateTime.now())
                .book(Book.builder().title(book_title).build())
                .build()
        );
    }

    @ShellMethod("remove comment")
    public void remove_comment(@ShellOption int comment_id) {
        commentService.remove(Comment.builder()
                .id(comment_id)
                .build()
        );
    }

    @ShellMethod("get all book comments")
    public void get_by_book(@ShellOption String book_title) {
        System.out.println(commentService.getByBookTitle(book_title));
    }
}
