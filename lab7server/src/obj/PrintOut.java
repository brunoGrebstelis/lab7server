package obj;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;


public class PrintOut implements  Serializable{
	
	private LinkedHashMap<Integer, Person> showPersons2;
	private LinkedHashMap<Integer, String> helpElements = new LinkedHashMap<Integer, String>();
	public PrintOut(LinkedHashMap<Integer, Person> showPersons2){
		this.showPersons2 =showPersons2;
	}
	
	public void show() {
		showPersons2.forEach((k,v) -> System.out.println(v.getShow()));
	}
	
	public void help() {
		helpElements.put(1, "help : display help for available commands");
		helpElements.put(2,"info : print information about the collection (type, date of initialization, number of elements, etc.) to standard output");
		helpElements.put(3, "show : print to standard output all elements of the collection in string representation");
		helpElements.put(4, "insert_null {element} : add a new item with the given key");
		helpElements.put(5,"update_id {element} : update the value of the collection item whose id is equal to the given");
		helpElements.put(6, "remove_key null : remove an item from the collection by its key");
		helpElements.put(7, "clear : clear collection");
		helpElements.put(8, "save : save collection to file");
		helpElements.put(9,"execute_script file_name : read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.");
		helpElements.put(10, "exit : end the program (without saving to file)");
		helpElements.put(11,"remove_greater {element} : remove all items from the collection that are greater than the specified one");
		helpElements.put(12, "history : print the last 6 commands (without their arguments)");
		helpElements.put(13,"replace_if_greater null {element} : replace value by key if new value is greater than old");
		helpElements.put(14,"remove_all_by_birthday birthday : remove from the collection all elements whose birthday field value is equivalent to the given one");
		helpElements.put(15, "print_ascending : display the elements of the collection in ascending order");
		helpElements.put(16,"print_field_descending_birthday : print the birthday field values ​​of all elements in descending order");

		for (Entry<Integer, String> m : helpElements.entrySet()) {
			System.out.println(m.getValue());
		}
	}
	
	public void info() {

		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
		String formattedString = zonedDateTimeNow.format(formatter);

		System.out.println("Type: " + showPersons2.getClass());
		System.out.println("Data: " + formattedString);
		System.out.println("Number of elements: " + showPersons2.size());
	}
	
	public void print_ascending() {
		showPersons2.forEach((k,a)->System.out.println(
				"Element nr." + k + ") " + a.getName() + "; " + a.FgetBirthday() + "; " 
				+ a.getEyeColor()+"; ID-"+a.getID()+ "; UserID: "+a.getUserID()));
	}
	
	public void print_field_descending_birthday() {
		 List<Entry<Integer, Person>> list = new ArrayList<>(showPersons2.entrySet());
		 Collections.sort(list, new Comparator<Entry<Integer, Person>>(){

			@Override
			public int compare(Entry<Integer, Person> p1, Entry<Integer, Person> p2) {
				return p2.getValue().FgetBirthday().compareTo(p1.getValue().FgetBirthday());
				}	
			}); 
			list.forEach(l ->System.out.println(l.getValue().getName()+" - "+l.getValue().FgetBirthday()));
	}
}
