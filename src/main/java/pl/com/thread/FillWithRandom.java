package pl.com.thread;

import java.util.concurrent.CountDownLatch;

import pl.com.main.Person;

public class FillWithRandom implements Runnable {
	public Person person;
	public CountDownLatch latch;
	
	public FillWithRandom(Person person, CountDownLatch latch) {
		this.person = person;
		this.latch = latch;
	}

	@Override
	public void run() {
		Person.fill(person);
		Person.fix(person);
		Person.rating(person);

        if(latch != null)
        	latch.countDown();
	}

}
