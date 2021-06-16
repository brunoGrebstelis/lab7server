package lab7.project;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import obj.Person;

public class IDoperations {

	private LinkedHashMap<Integer, Person> showPersons2;

	public IDoperations(LinkedHashMap<Integer, Person> showPersons2) {
		this.showPersons2 = showPersons2;
	}

	public boolean testIfIDExist(long id) {
		boolean beenThere = false;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (id == a.getID()) {
				beenThere = true;
			}
		}
		return beenThere;
	}

	public boolean testIfKeyExist(long id) {
		boolean beenThere = false;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			if (id == m.getKey()) {
				beenThere = true;
			}
		}
		return beenThere;
	}

	public long setID() {
		long maxID = 0;
		int i = 0;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if (a.getID() > maxID) {
				maxID = (a.getID());
			}
		}
		maxID = maxID + 1;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			i++;
			Person a = m.getValue();
			if (a.getID() != i) {
				maxID = i;
				i++;
			}
		}
		return maxID;
	}

	public boolean testIfCorUser(long id,int userID) {
		boolean result = false;
		for (Entry<Integer, Person> m : showPersons2.entrySet()) {
			Person a = m.getValue();
			if(a.getID()==id) {
				if(a.getUserID()==userID) {
					result = true;
				}
			}
		}
		return result;
	}
}
