package pl.com.main;

import java.util.Arrays;

public class Person {

	public int[] chromosome;
	public int rating;
	
	public Person() {
		chromosome = new int[Main.SIZE];
	}
	
	public void display() {
		for(int i=0; i<Main.SIZE; i++) {
			System.out.print(chromosome[i]+ " ");
			
			if((i+1) % Main.WIDTH == 0) {
				System.out.println("");
			}
		}
	}
	
	public static void fill(Person person) {
		Arrays.fill(person.chromosome, 0);
		
		int idx = 0;
	
		for(int i = 0; i < Main.HETMANS; i++)
		{
			idx = Main.random.nextInt(Main.SIZE);
			person.chromosome[idx] = 1;
		}
	}

	public static void fix(Person person) {
		int mozeByc = 1;
		int index = 0;
		boolean jest = false;
		
		for(int i=0; i<Main.SIZE; i++) {
			if(person.chromosome[i] == 1 && mozeByc == 1) {
				mozeByc = 0;

				int j = i+Main.WIDTH;
				while(j < Main.SIZE)
				{
					person.chromosome[j] = 0;
					j+=Main.WIDTH;
				}

			} else if(person.chromosome[i] == 1) {
				person.chromosome[i] = 0;
				mozeByc = 0;
			}
			if((i+1) % Main.WIDTH == 0) {
				mozeByc = 1;
			}
		}
		
		for (int p = -(Main.WIDTH - 1); p < Main.WIDTH; p++) {
			jest = false;
			
			for (int k = 0; k < Main.WIDTH; k++) {
				index = getIndex(k, p);
				
				if (index < 0) continue;
				if ((index >= (k + 1) * Main.WIDTH)) break;
				if ((index < k * Main.WIDTH)) continue;
				if (index >= Main.SIZE) break;
				
				if (person.chromosome[index] == 1 && !jest) {
					jest = true;
				} else {
					if (person.chromosome[index] == 1) {
						person.chromosome[index] = 0;
					}
				}
			}
		}

		for (int p = Main.WIDTH + Main.WIDTH - 1; p > 0; p--) {
			jest = false;
			
			for (int k = 0; k < Main.WIDTH; k++) {
				index = getIndex2(k, p);
				
				if (index < 0) break;
				if ((index >= (k + 1) * Main.WIDTH)) continue;
				if ((index < k * Main.WIDTH)) break;
				if (index >= Main.SIZE) continue;
				
				if (person.chromosome[index] == 1 && !jest) {
					jest = true;
				} else {
					if (person.chromosome[index] == 1) {
						person.chromosome[index] = 0;
					}
				}
			}
		}
	}
	
	public static int rating(Person person) {		
		person.rating = 0;

		for(int i=0; i<Main.WIDTH; i++) {
			for(int j=0; j<Main.WIDTH; j++) {
				person.rating += person.chromosome[j + i * Main.WIDTH];
			}
		}
		return person.rating;
	}
	
	private static int getIndex(int krok, int przesuniecie) {
		return krok * Main.WIDTH + krok + przesuniecie;
	}
	
	private static int getIndex2(int krok, int przesuniecie) {
		return krok * Main.WIDTH + krok + przesuniecie - (2 * krok);
	}

}
