package edu.otus.hw13.controller;

import edu.otus.hw13.entities.Book;
import edu.otus.hw13.entities.Comment;
import edu.otus.hw13.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Nik Bespalov on 18/08/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/", "books"})
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String list(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "/book/list";
    }

    @GetMapping("/books/{id}/show")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String view(@PathVariable String id,
                       Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "/book/view";
    }

    @GetMapping("/books/add")
    @Secured("ROLE_ADMIN")
    public String addForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "/book/add";
    }

    @GetMapping("/books/{id}/delete")
    @Secured("ROLE_ADMIN")
    public String delete(@PathVariable String id,
                         Model model) {
        bookService.remove(new Book() {{setId(id);}});
        return "redirect:/books/";
    }

    @GetMapping("/books/{id}/update")
    @Secured("ROLE_ADMIN")
    public String updateForm(@PathVariable String id,
                         Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "/book/update";
    }

    @PostMapping("books/add")
    @Secured("ROLE_ADMIN")
    public String savaOrUpdate(@Valid @ModelAttribute("book") Book book,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "/book/list";
        }
        Book savedBook = bookService.add(book);
        return "redirect:/books/" + savedBook.getId() + "/show";

    }

    @GetMapping("/books/{id}/comments")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String commentForm(@PathVariable String id,
                              Model model) {
        model.addAttribute("id", id);
        model.addAttribute("comment", new Comment() {{setUserName("user name");}});
        return "/comment/add";
    }

    @PostMapping("/books/{id}/comments")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public String savaOrUpdateComment(@ModelAttribute("comment") Comment comment,
                                      @ModelAttribute("id") String id,
                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "/book/list";
        }
        Book savedBook = bookService.addComment(id, comment);
        return "redirect:/books/" + savedBook.getId() + "/show";
    }
}
