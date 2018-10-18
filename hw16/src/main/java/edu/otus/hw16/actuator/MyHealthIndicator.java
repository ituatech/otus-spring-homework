package edu.otus.hw16.actuator;

import edu.otus.hw16.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created by Nik Bespalov on 17/10/2018.
 *
 * @author Nik Bespalov.
 */

@Component
public class MyHealthIndicator implements ReactiveHealthIndicator {

    private BookRepository bookRepository;

    @Autowired
    public MyHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Health> health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Mono.just(Health.down()
                    .withDetail("Error Code", errorCode).build());
        }
        return Mono.just(Health.up().build());
    }

    private int check() {
        if (bookRepository.findAll().isEmpty()) {
            return 1;
        }
        return 0;
    }
}
