package pl.com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
	
	private Person person;
	
    @BeforeEach
    public void setUp() throws Exception {
    	person = new Person();
    }
    
    @Test
    public void testFill() {
    	int hetmans = 0;
    	
    	hetmans = count(person);
    	assertFalse(hetmans > 0);
    	
		Person.fill(person);
		hetmans = count(person);
		assertTrue(hetmans > 0 , "Filling with random numbers should work");
    }
    
    @Test  
	public void testFix() {
		int hetmans = 0;
		
		for (int i = 0; i < Main.WIDTH; i++) {
			person.chromosome[i] = 1;
		}
		
		hetmans = count(person);
		assertEquals(8, hetmans);
		
		Person.fix(person);
		hetmans = count(person);
		assertEquals(1, hetmans , "Removing repeated numbers in a row should work");
	}
 
    @Test
	public void testFix2() {
		int hetmans = 0;
		
		for (int i = 0; i < Main.WIDTH; i++) {
			person.chromosome[i * Main.WIDTH] = 1;
		}

		hetmans = count(person);
		assertEquals(8, hetmans);
		
		Person.fix(person);
		hetmans = count(person);
		assertEquals(1, hetmans, "Removing repeated numbers in a column should work");
	}
    
    @Test
	public void testFix3() {
		int hetmans = 0;
		
		for (int i = 0; i < Main.WIDTH; i++) {
			person.chromosome[(i * Main.WIDTH) + i] = 1;
		}

		hetmans = count(person);
		assertEquals(8, hetmans);
		
		Person.fix(person);
		hetmans = count(person);
		assertEquals(1, hetmans, "Removing repeated numbers at a slant");
	}
    
    @Test
	public void testRating() {
		Person.fill(person);
		
		assertEquals(0, person.rating);
		
		Person.rating(person);
		assertTrue(person.rating > 0);
	}
	
	private int count(Person person) {
		int hetmans = 0;
		
		for (int chromosome : person.chromosome) {
			if (chromosome == 1)
				hetmans++;
		}
		return hetmans;
	}
}
