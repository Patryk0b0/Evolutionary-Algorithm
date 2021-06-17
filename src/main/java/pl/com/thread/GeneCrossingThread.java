package pl.com.thread;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import pl.com.main.Person;
import pl.com.main.Population;

public class GeneCrossingThread implements Runnable {
	public Population population;

	public GeneCrossingThread(Population population) {
		this.population = population;
	}

	@Override
	public void run() {
		int count = population.size();
		
		CountDownLatch latch = new CountDownLatch(count / 2);		
		Person[] persons = new Person[count];
		
		for (int i = 0, len = count; i < len; i += 2) {
			int index1 = len - i - 1;
			int index2 = len - i - 2;
			
			Person person1 = this.population.get(index1);
			Person person2 = this.population.get(index2);

			this.population.remove(person1);
			this.population.remove(person2);
			GeneCrossing geneCrossing = new GeneCrossing(persons, person1, index1, person2, index2, latch);
			geneCrossing.id = i;
			
			new Thread(geneCrossing).start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.population.addAll(Arrays.asList(persons));
	}

}
