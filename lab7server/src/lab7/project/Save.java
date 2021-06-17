package lab7.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import obj.UserInfo;

public class Save {
	
	final String user = "postgres";
	final String password = "mocis";
	final String url = "jdbc:postgresql://localhost:5432/lab7_db";
	
	public void saveUser(LinkedHashMap<Integer, UserInfo> userinfo){
		try {
			final Connection connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			String SQL = "TRUNCATE userinfo";
			st.addBatch(SQL);
			st.executeBatch();
			for (Entry<Integer, UserInfo> m : userinfo.entrySet()) {
				UserInfo a = m.getValue();
				SQL = "INSERT INTO userinfo (user_id, login, password) VALUES ('"+a.getUser_id()+
						"', '"+a.getLogin()+"', '"+a.getPassword()+"');";
				st.addBatch(SQL);
				st.executeBatch();		
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
		
		public void savePerson(LinkedHashMap<Integer, Person> showPersons2){
			try {
				final Connection connection = DriverManager.getConnection(url, user, password);
				Statement st = connection.createStatement();
				String SQL = "TRUNCATE persondata";
				st.addBatch(SQL);
				st.executeBatch();
				for (Entry<Integer, Person> m : showPersons2.entrySet()) {
					Person a = m.getValue();
					SQL = "INSERT INTO persondata (id, name, xcord, ycord, height, passportid, "
							+ "color, xloc, yloc, zloc, creationdata, birthsday, user_id)"
							+ " VALUES("+a.getID()+", '"+a.getName()+"',"
							+ " '"+a.getXcoordinate()+"', '"+a.getYcoordinate()+"',"
							+ " '"+a.getHeight()+"', '"+a.getPassportID()+"', '"+a.getEyeColor()+"',"
							+ " '"+a.getXlocation()+"', '"+a.getYlocation()+"', '"+a.getZlocation()+"',"
							+ " '"+a.getCreationDate()+"', '"+a.FgetBirthday()+"', "+a.getUserID()+");";
					st.addBatch(SQL);
					st.executeBatch();		
				}
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void insertPerson(Person p,LinkedHashMap<Integer, Person> showPersons2){			
			
			try {
				final Connection connection = DriverManager.getConnection(url, user, password);
				Statement st = connection.createStatement();
				String SQL = "INSERT INTO persondata (id, name, xcord, ycord, height, passportid, "
						+ "color, xloc, yloc, zloc, creationdata, birthsday, user_id)"
						+ " VALUES(DEFAULT, '"+p.getName()+"',"
						+ " '"+p.getXcoordinate()+"', '"+p.getYcoordinate()+"',"
						+ " '"+p.getHeight()+"', '"+p.getPassportID()+"', '"+p.getEyeColor()+"',"
						+ " '"+p.getXlocation()+"', '"+p.getYlocation()+"', '"+p.getZlocation()+"',"
						+ " '"+p.getCreationDate()+"', '"+p.FgetBirthday()+"', "+p.getUserID()+");";
				st.addBatch(SQL);
				st.executeBatch();		
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			try {
				final Connection connection = DriverManager.getConnection(url, user, password);
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM persondata WHERE name = '"+p.getName()+"'");
				while (rs.next()) {
					Long setID = rs.getLong("id");
					p.setID(setID);
				}
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			int key = 0, i = 0;

			for (Entry<Integer, Person> m : showPersons2.entrySet()) {
				if (m.getKey() != i) {key = i; i++;}i++;
			}
			if(showPersons2.size()==i) {key=i;}
			
			//System.out.println("key: "+key);
			showPersons2.put(key, p);
			sort(showPersons2);
	}
		
	public void sort(LinkedHashMap<Integer, Person> showPersons2) {
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
