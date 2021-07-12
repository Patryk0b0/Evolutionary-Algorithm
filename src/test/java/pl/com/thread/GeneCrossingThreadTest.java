package pl.com.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import pl.com.main.Main;
import pl.com.main.Person;
import pl.com.main.Population;

public class GeneCrossingThreadTest {

	@RepeatedTest(5)
	public void testGeneCrossing() {
		Population population = Population.create(10);
		
		GeneCrossingThread geneCrossingThread = new GeneCrossingThread(population);
		geneCrossingThread.run();
		
		assertEquals(10, population.size());
		
		for(Person person : population) {
			assertTrue(person.rating <= Main.HETMANS);
			assertTrue(person.rating > 0);
			assertTrue(person.chromosome.length == Main.SIZE);
		}
	}
}
