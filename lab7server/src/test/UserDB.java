package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import obj.UserInfo;

public class UserDB {
	public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "mocis";
        final String url = "jdbc:postgresql://localhost:5432/lab7_db";
        LinkedHashMap<Integer, UserInfo> map = new LinkedHashMap<Integer, UserInfo>();
        Integer i = 0;
        UserInfo u;
        
        final Connection connection = DriverManager.getConnection(url, user, password);
        
        try {
        	Statement st = connection.createStatement();
        	ResultSet rs = st.executeQuery("SELECT * FROM userinfo");
        	while(rs.next()) {
        		Integer user_id = rs.getInt("user_id");
        		String login = rs.getString("login");
        		String passwordUser = rs.getString("password");
        		u = new UserInfo(user_id, login, passwordUser);
        		map.put(i, u);
        		i++;
        	}
        	
        	
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
        
        connection.close();
        map.forEach((k,v) -> System.out.println(k+" "+v.getUser_id()+" "+v.getLogin()+
        		" "+v.getPassword()+"\n"));
        
	}

}
