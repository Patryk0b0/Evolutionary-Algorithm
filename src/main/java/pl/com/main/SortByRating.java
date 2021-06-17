package pl.com.main;

import java.util.Comparator;

public class SortByRating implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {

		return p2.rating - p1.rating;
	}

}
