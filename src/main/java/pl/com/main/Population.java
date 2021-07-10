package pl.com.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import pl.com.thread.FillWithRandom;
import pl.com.thread.GeneCrossingThread;

public class Population extends ArrayList<Person> implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public float min = Main.HETMANS;
	public float max = 0;
	public float avg = 0;

	public void removalOfDefective(int amount) {
		removeRange(amount, size());
	}
	
	public void geneCrossing() {
		try {
			Thread thread = new Thread(new GeneCrossingThread(this));
			thread.start();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void completing(int amount) {
		int size = size();
		
		for (int i = 0; i < amount; i++) {
			add(get(Main.random.nextInt(size)));
		}
	}
	
	public void sort() {
		Collections.sort(this, new SortByRating());
	}
	
	public static Population create(int amount) {
		Population population = new Population();
		
		CountDownLatch latch = new CountDownLatch(amount);
		
		for(int i = 0; i < amount; i++) {
			population.add(new Person());

			new Thread(new FillWithRandom(population.get(i), latch)).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return population;
	}
	
	private void removeBad() {
		for (int i = 0, len = size(); i < len; i++) {
			if (get(i).rating < avg - 1) {
				remove(i);
				i--;
				len--;
			}
		}
	}

	@Override
	public void run() {
		
		for (int i = 0; i < Main.ITERATIONS; i++) {
			completing(Main.MAX_POPULATION - size());
			
			mutations();

			geneCrossing();
			sort();			
			removalOfDefective(Main.MAX_POPULATION / 2);
//			removeBad();
			
			results();
			update(i);
		}
	}

	private void update(int i) {
		Main.min(i, min, (x) -> (double)x);
		Main.max(i, max, (x) -> (double)x);
		Main.avg(i, avg, (x) -> (double)x);
	}

	private void mutations() {
		for (int j = 0, max = (Main.MAX_POPULATION / 100) * Main.MUTATIONS; j < max; j++) {
			
			Person person = get(Main.random.nextInt(size()));
			
			person.chromosome[Main.random.nextInt(Main.SIZE)] = 1;
			
			Person.fix(person);
		}
	}

	private void results() {
		min = Main.HETMANS;
		max = 0;
		avg = 0;
		
		
		float suma = 0;
		
		for (int i = 0, len = size(); i < len; i++) {
			suma += get(i).rating;
			
			if (min > get(i).rating)
				min = get(i).rating;
			else if (max < get(i).rating)
				max = get(i).rating;
		}
		avg = suma / size();
	}
	
}
