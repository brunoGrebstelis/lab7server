package test;

import java.security.NoSuchAlgorithmException;

public class MD2Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MD2 m = new MD2();
		System.out.println("HashCode Generated by MD2 for: ");

		String s1 = "user1";
		System.out.println("\n" + s1 + " : " + m.encryptThisString(s1));

		String s2 = "user2";
		System.out.println("\n" + s2 + " : " + m.encryptThisString(s2));
	}
}
