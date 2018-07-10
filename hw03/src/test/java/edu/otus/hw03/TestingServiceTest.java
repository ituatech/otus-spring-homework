package edu.otus.hw03;

import edu.otus.hw03.mock.TestingServiceMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingServiceTest {

    @Autowired
    private TestingServiceMock service;

    private Locale locale = Locale.getDefault();

    @Test
    public void testWithGoodResults() {
        service.setAnswers(new ArrayList<>(Arrays.asList("2", "1,3", "2", "3", "1")));
        String expected = locale.toString().equals("ru_RU") ? "хакер" : "hacker";
        Assert.assertEquals(expected, service.start());
    }

    @Test
    public void testWithBadResults() {
        service.setAnswers(new ArrayList<>(Arrays.asList("1", "1", "1", "1", "1")));
        String expected = locale.toString().equals("ru_RU") ? "ламер" : "lamer";
        Assert.assertEquals(expected, service.start());
    }
}