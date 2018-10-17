package edu.otus.hw15.integration;

import edu.otus.hw15.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */

@IntegrationComponentScan
@EnableIntegration
@Configuration
public class Config {
    @Bean
    public MessageChannel getAllInputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public PublishSubscribeChannel getAllOutputChannel() {
        return MessageChannels.publishSubscribe().datatype(List.class).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow personIntegrationFlow() {
        return IntegrationFlows.from("getAllInputChannel")
                .handle("personServiceImpl", "getAll")
                .channel("getAllOutputChannel")
                .get();
    }

    @Bean
    public MessageChannel addInputChannel() {
        return MessageChannels.direct().datatype(Person.class).get();
    }

    @Bean
    public PublishSubscribeChannel addOutputChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow addPersonIntegrationFlow() {
        return IntegrationFlows.from("addInputChannel")
                .handle("personServiceImpl", "add")
                .channel("addOutputChannel")
                .get();
    }
}