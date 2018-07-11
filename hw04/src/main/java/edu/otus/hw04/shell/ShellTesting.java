package edu.otus.hw04.shell;

import edu.otus.hw04.service.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by Nik Bespalov on 11/07/2018.
 *
 * @author Nik Bespalov.
 */
@ShellComponent
public class ShellTesting {
    private TestingService service;

    @Autowired
    public ShellTesting(@Qualifier("service") TestingService service) {
        this.service = service;
    }

    @ShellMethod("start test")
    public void test(@ShellOption String name) {
        System.out.println(name + ", you are " + service.start());
    }
}
