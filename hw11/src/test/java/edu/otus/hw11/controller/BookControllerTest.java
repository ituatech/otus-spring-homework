package edu.otus.hw11.controller;

import edu.otus.hw11.entities.Author;
import edu.otus.hw11.entities.Book;
import edu.otus.hw11.entities.Comment;
import edu.otus.hw11.entities.Genre;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Nik Bespalov on 24/08/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private Book book1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        book1 = new Book() {{
            setId("10");
            setTitle("Title1");
            setDescription("Description1");
            setAuthors(Arrays.asList(new Author("Author1"), new Author("Author2")));
            setGenres(Collections.singletonList(new Genre("Genre1")));
            setComments(Collections.singletonList(new Comment("text", "userName", LocalDateTime.now())));
        }};
    }

    @Test
    public void list() {
        webTestClient.get().uri("/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .hasSize(2);
    }

    @Test
    public void view() {
        WebTestClient.ResponseSpec responseSpec = webTestClient.get().uri("/books/1/show")
                .exchange()
                .expectStatus()
                .isOk();
        Book book = responseSpec.expectBody(Book.class).returnResult().getResponseBody();
        assertThat(book.getTitle()).isNotBlank().isEqualTo("Comedy book");
    }

    @Test
    public void deleteTest() {
       webTestClient.delete().uri("/books/1/delete")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void save() {
        WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/books/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(book1))
                .exchange()
                .expectStatus()
                .isOk();
        Book book = responseSpec.expectBody(Book.class).returnResult().getResponseBody();
        assertThat(book.getTitle()).isNotBlank().isEqualTo("Title1");
    }

    @Test
    public void update() {
        book1.setId("1");
        WebTestClient.ResponseSpec responseSpec = webTestClient.put().uri("/books/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(book1))
                .exchange()
                .expectStatus()
                .isOk();
        Book book = responseSpec.expectBody(Book.class).returnResult().getResponseBody();
        assertThat(book.getTitle()).isNotBlank().isEqualTo("Title1");
    }

    @Test
    public void saveComment() {
        Comment comment = Comment.builder().commentText("test comment").userName("user").build();
        WebTestClient.ResponseSpec responseSpec = webTestClient.post().uri("/books/1/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(comment))
                .exchange()
                .expectStatus()
                .isOk();
        Book book = responseSpec.expectBody(Book.class).returnResult().getResponseBody();
        assertThat(book.getComments()).contains(comment);
    }
}