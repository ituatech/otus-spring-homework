package edu.otus.hw04.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Nik Bespalov on 09/07/2018.
 *
 * @author Nik Bespalov.
 */
@Getter
@Setter
@Component
@ConfigurationProperties("csv")
public class Properties {
    private String path;
}
