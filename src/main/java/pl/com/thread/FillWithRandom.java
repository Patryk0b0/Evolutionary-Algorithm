package pl.com.thread;

import pl.com.main.Person;

public class FillWithRandom implements Runnable {
	public Person person;
	
	public FillWithRandom(Person person) {
		this.person = person;
	}

	@Override
	public void run() {
		Person.fill(person);
		Person.fix(person);
		Person.rating(person);
	}

}
