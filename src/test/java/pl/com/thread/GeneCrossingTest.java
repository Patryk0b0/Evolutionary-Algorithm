package pl.com.thread;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import pl.com.main.Main;
import pl.com.main.Person;

public class GeneCrossingTest {

    @RepeatedTest(5)
    public void testGeneCrossing() {
    	Person person0 = new Person();
    	Person.fill(person0);
    	Person.fix(person0);
    	
    	Person person1 = new Person();
    	Person.fill(person1);
    	Person.fix(person1);
    	
    	Person[] persons = new Person[2];
    	
    	GeneCrossing geneCrossing = new GeneCrossing(persons, person0, 0, person1, 1);
    	geneCrossing.run();
    	
    	assertTrue(persons[0].rating <= Main.HETMANS);
    	assertTrue(persons[1].rating <= Main.HETMANS);
    	
    	assertTrue(persons[0].rating > 0);
    	assertTrue(persons[1].rating > 0);   	
    	
    	assertTrue(persons[0].chromosome.length == Main.SIZE);
    	assertTrue(persons[1].chromosome.length == Main.SIZE);
    }
}
