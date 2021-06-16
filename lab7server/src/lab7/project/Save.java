package lab7.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import obj.Person;
import obj.UserInfo;

public class Save {
	
	final String user = "postgres";
	final String password = "mocis";
	final String url = "jdbc:postgresql://localhost:5432/lab7_db";
	LinkedHashMap<Integer, UserInfo> userinfo = new LinkedHashMap<Integer, UserInfo>();
	LinkedHashMap<Integer, Person> showPersons2 = new LinkedHashMap<Integer, Person>();
	
	public Save(LinkedHashMap<Integer, Person> showPersons2, LinkedHashMap<Integer, UserInfo> userinfo) {
		this.userinfo=userinfo;
		this.showPersons2=showPersons2;
	}
	
	public void saveUser(){
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
		
		
		public void savePerson(){
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
	

}
