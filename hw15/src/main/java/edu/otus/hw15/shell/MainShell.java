package edu.otus.hw15.shell;

import edu.otus.hw15.entity.Address;
import edu.otus.hw15.entity.Person;
import edu.otus.hw15.integration.PersonIntegration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */

@Log4j2
@ShellComponent
public class MainShell {

    private PersonIntegration personIntegration;

    @Autowired
    public MainShell(PersonIntegration personIntegration) {
        this.personIntegration = personIntegration;
    }

    @ShellMethod("get all")
    public void get_all() {
        Message<String> message = MessageBuilder.withPayload("").build();
        personIntegration.getAll(message).forEach(System.out::println);
    }

    @ShellMethod("add")
    public void add(@ShellOption String name,
                    @ShellOption Integer age,
                    @ShellOption String city,
                    @ShellOption String street,
                    @ShellOption Integer building_num) { // add --name n --age 10 --city c --street s --building_num 1
        Person newPerson = Person.builder().name(name)
                                            .age(age)
                                            .address(Address.builder()
                                                    .city(city)
                                                    .street(street)
                                                    .buildingNumber(building_num)
                                                    .build())
                                            .build();
        boolean result = personIntegration.add(newPerson);

        if (result) {
            System.out.println("USER SAVED");
        } else {
            System.out.println("ERROR");
        }
    }
}
