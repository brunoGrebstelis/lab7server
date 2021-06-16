package lab7.project;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import obj.Color;
import obj.Coordinates;
import obj.Location;
import obj.Person;

public class Insert {

	private LinkedHashMap<Integer, Person> showPersons2;

	public Insert(LinkedHashMap<Integer, Person> showPersons2) {
		this.showPersons2 = showPersons2;
	}

	public void insert(String name, Coordinates coordinates, Double height, long id, String passportID, Color eyeColor,
			Location location, LocalDate Fbirthday,int userID) {
		Person newPers = new Person(id, name, coordinates, height, passportID, eyeColor, location,userID);
		newPers.FsetDateTimeBirthString(Fbirthday);

		int key = 0, i = 0;

		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			if (m.getKey() != i) {
				key = i;
				i++;
			}
			i++;
		}
		if(showPersons2.size()==i) {
			key=i;
		}
		
		//System.out.println("key: "+key);
		showPersons2.put(key, newPers);
		sort();
	}

	public void sort() {

		List<Entry<Integer, Person>> list = new LinkedList<Entry<Integer, Person>>(showPersons2.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, Person>>() {

			@Override
			public int compare(Entry<Integer, Person> arg0, Entry<Integer, Person> arg1) {
				return arg0.getKey().compareTo(arg1.getKey());
			}

		});
		showPersons2.clear();
		list.forEach(k -> showPersons2.put(k.getKey(), k.getValue()));
	}

}
