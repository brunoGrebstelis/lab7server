package lab7.project;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import obj.Person;
import obj.UserInfo;

public class MD2 {
	LinkedHashMap<Integer, UserInfo> userinfo = new LinkedHashMap<Integer, UserInfo>();
	
	public MD2(LinkedHashMap<Integer, UserInfo> userinfo) {
		this.userinfo=userinfo;
	}
	
	public static String encryptThisString(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
	public boolean testLogin(String login){
		boolean result = false;
		for (Entry<Integer, UserInfo> m : userinfo.entrySet()) {
			UserInfo a = m.getValue();
			if (a.getLogin().equals(login)) {result = true;}
		}
		return result;
	}
	
	public int testPassword(String password,String login) {
		int result = -1;
		String hashpassword = encryptThisString(password);
		for (Entry<Integer, UserInfo> m : userinfo.entrySet()) {
			UserInfo a = m.getValue();
			if (a.getLogin().equals(login)&&a.getPassword().equals(hashpassword)) {result = a.getUser_id();}
		}	
		return result;
	}
}
