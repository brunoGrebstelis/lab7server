package lab7.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

import obj.Color;
import obj.Coordinates;
import obj.Location;
import obj.Person;
import obj.UserInfo;

public class DataBaseOp {
	final String user = "postgres";
	final String password = "mocis";
	final String url = "jdbc:postgresql://localhost:5432/lab7_db";
	LinkedHashMap<Integer, UserInfo> userinfo = new LinkedHashMap<Integer, UserInfo>();
	LinkedHashMap<Integer, Person> showPersons2 = new LinkedHashMap<Integer, Person>();
	
	public DataBaseOp() {
		fillUser();
		fillPers();
	}
	

	public void fillUser() {
		Integer i = 0;
		try {
			final Connection connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM userinfo");
			while (rs.next()) {
				Integer user_id = rs.getInt("user_id");
				String login = rs.getString("login");
				String passwordUser = rs.getString("password");
				UserInfo u = new UserInfo(user_id, login, passwordUser);
				userinfo.put(i, u);
				i++;
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void fillPers() {
		Integer i = 0;
		try {
			final Connection connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM persondata");
			while (rs.next()) {
				Integer userId = rs.getInt("user_id");
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				Double xcor = rs.getDouble("xcord");
				Float ycor = rs.getFloat("ycord");
				Double height = rs.getDouble("height");
				String passportid = rs.getString("passportid");
				Integer xloc = rs.getInt("xloc");
				Long yloc = rs.getLong("yloc");	
				Long zloc = rs.getLong("zloc");
				Coordinates cord = new Coordinates(xcor, ycor);
				Location loc = new Location(xloc, yloc, zloc);
				Person pers = new Person(id, name, cord, height, passportid,
						Color.valueOf(rs.getString("color")), loc, userId);
				pers.setCreationDate(ZonedDateTime.parse(rs.getString("creationdata")));
				pers.FsetDateTimeBirthString(LocalDate.parse(rs.getString("birthsday")));
				showPersons2.put(i, pers);
				i++;
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//showPersons2.forEach((k,v) -> System.out.println(v.getUserID()));
	}
	
	public LinkedHashMap<Integer, Person> getShowPersons2() {
		return this.showPersons2;
	}
	
	public LinkedHashMap<Integer, UserInfo> getUserinfo() {
		return this.userinfo;
	}
	

}
