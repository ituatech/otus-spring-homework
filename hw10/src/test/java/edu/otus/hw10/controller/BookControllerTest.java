package edu.otus.hw10.controller;

import edu.otus.hw10.entities.Author;
import edu.otus.hw10.entities.Book;
import edu.otus.hw10.entities.Comment;
import edu.otus.hw10.entities.Genre;
import edu.otus.hw10.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nik Bespalov on 24/08/2018.
 *
 * @author Nik Bespalov.
 */
public class BookControllerTest {

    BookController bookController;
    MockMvc mockMvc;

    @Mock
    BookService bookService;

    Book book1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .build();

        book1 = new Book() {{
            setId("1");
            setTitle("Title1");
            setDescription("Description1");
            setAuthors(Arrays.asList(new Author("Author1"), new Author("Author2")));
            setGenres(Collections.singletonList(new Genre("Genre1")));
            setComments(Collections.singletonList(new Comment("text", "userName", LocalDateTime.now())));
        }};
    }

    @Test
    public void list() throws Exception {
        when(bookService.getAll()).thenReturn(Collections.singletonList(book1));
        MvcResult mvcResult = mockMvc.perform(get("/books"))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString())
                .isNotNull()
                .isNotBlank()
                .contains("\"id\":\"1\",\"title\":\"Title1\",\"description\":\"Description1\"");
        verify(bookService, times(1)).getAll();
    }

    @Test
    public void view() throws Exception {
        when(bookService.getById("1")).thenReturn(book1);
        MvcResult mvcResult = mockMvc.perform(get("/books/1/show"))
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString())
                .isNotNull()
                .isNotBlank()
                .contains("\"id\":\"1\",\"title\":\"Title1\",\"description\":\"Description1\"");
        verify(bookService, times(1)).getById(anyString());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/books/1/delete")).andExpect(status().isOk());
        verify(bookService, times(1)).remove(any());
    }

    @Test
    public void save() throws Exception {
        when(bookService.add(any())).thenReturn(book1);
        MvcResult mvcResult = mockMvc.perform(post("/books/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andReturn();
        assertThat(mvcResult.getResponse().getStatus())
                .isNotNull()
                .isEqualTo(200);
        verify(bookService, times(1)).add(any());
    }

    @Test
    public void update() throws Exception {
        when(bookService.update(any())).thenReturn(book1);
        MvcResult mvcResult = mockMvc.perform(put("/books/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andReturn();
        assertThat(mvcResult.getResponse().getStatus())
                .isNotNull()
                .isEqualTo(200);
        verify(bookService, times(1)).update(any());
    }

    @Test
    public void saveComment() throws Exception {
        when(bookService.addComment(anyString(), any())).thenReturn(book1);
        MvcResult mvcResult = mockMvc.perform(post("/books/1/comments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{}"))
                .andReturn();
        assertThat(mvcResult.getResponse().getStatus())
                .isNotNull()
                .isEqualTo(200);
        verify(bookService, times(1)).addComment(anyString(), any());
    }
}