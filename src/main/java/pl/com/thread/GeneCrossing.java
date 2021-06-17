package pl.com.thread;

import java.util.concurrent.CountDownLatch;

import pl.com.main.Main;
import pl.com.main.Person;

public class GeneCrossing implements Runnable {
	public CountDownLatch latch;
	public Person person1;
	public Person person2;
	public int id;
	public Person[] persons;
	public int index1;
	public int index2;
	
	public GeneCrossing(Person[] persons, Person person1, int index1, Person person2, int index2, CountDownLatch latch) {
		this.persons = persons;
		this.person1 = person1;
		this.person2 = person2;
		this.latch = latch;
		this.index1 = index1;
		this.index2 = index2;
	}

	public GeneCrossing(Person[] persons, Person person1, int index1, Person person2, int index2) {
		this.persons = persons;
		this.person1 = person1;
		this.person2 = person2;
		this.index1 = index1;
		this.index2 = index2;
	}

	@Override
	public void run() {
		Person person1 = new Person();
		person1.chromosome = new int[Main.SIZE];
		
		Person person2 = new Person();
		person2.chromosome = new int[Main.SIZE];
		
		Person maska = new Person();
		Person.fill(maska);
		
		for (int j = 0, length = maska.chromosome.length; j < length; j++) {
			if (maska.chromosome[j] == 0) {
				person1.chromosome[j] = this.person1.chromosome[j];
				person2.chromosome[j] = this.person2.chromosome[j];
			} else {
				person2.chromosome[j] = this.person1.chromosome[j];
				person1.chromosome[j] = this.person2.chromosome[j];
			}
		}
		
		Person.fix(person1);
		Person.rating(person1);
		Person.fix(person2);
		Person.rating(person2);
		
		persons[index1] = person1;
		persons[index2] = person2;
		
        if(latch != null)
        	latch.countDown();
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

}
