package edu.otus.hw04;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
		QuestionsDAOTest.class,
		TestingServiceTest.class
})
public class Hw04ApplicationTests {
}
