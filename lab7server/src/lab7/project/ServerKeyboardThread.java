package lab7.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ServerKeyboardThread implements Runnable {

	private BufferedReader reader;

	public void run() {
		while (true) {
			try {
				reader = new BufferedReader(new InputStreamReader(System.in));
				if (reader.readLine().equals("exit")) {
					System.out.println("Programm is not active anymore");
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
