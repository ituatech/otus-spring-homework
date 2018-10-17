package edu.otus.hw15.integration;

import edu.otus.hw15.entity.Person;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */
@MessagingGateway
public interface PersonIntegration {

    @Gateway(requestChannel = "getAllInputChannel", replyChannel = "getAllOutputChannel")
    List<Person> getAll(Message o);

    @Gateway(requestChannel = "addInputChannel", replyChannel = "addOutputChannel")
    boolean add(Person person);
}
