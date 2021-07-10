package pl.com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopulationTest {
	
	private Population population;
	
    @BeforeEach
    public void setUp() throws Exception {
    	population = Population.create(10);
    }
    
    @Test
    public void testCreate() {
    	assertEquals(10, population.size());
	
    	for (int i = 0; i < 10; i++) {
    		assertTrue(population.get(i).rating > 0);
    	}
    }
    
    @Test
    public void testCompleting() {
    	population.completing(20);
    	
    	assertEquals(30, population.size());
    }
    
    @Test
    public void testSort() {
    	Person person1 = new Person();
    	person1.chromosome[0] = 1;
    	Person.rating(person1);

    	Person person2 = new Person();
    	person2.chromosome[0] = 1;
    	person2.chromosome[10] = 1;
    	Person.rating(person2);
    	
    	Population population = new Population();
    	population.add(person1);
    	population.add(person2);
    	
    	assertFalse(population.get(0).rating >= population.get(1).rating);
    	
    	population.sort();
    	
    	assertTrue(population.get(0).rating >= population.get(1).rating);
    }
    
    @Test
    public void test2Sort() {
    	population.sort();
    	
    	for (int i = 0; i < 10 - 1; i++) {
    		assertTrue(population.get(i).rating >= population.get(i + 1).rating);
    	}
    }

}
