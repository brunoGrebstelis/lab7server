package lab7.project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import obj.Coordinates;
import obj.Location;
import obj.Objects;
import obj.Person;
import obj.PrintOut;

public class ServerConnection implements Runnable {
	private ObjectOutputStream outStream = null;
	private ObjectInputStream inStream = null;
	private Objects msgRes, msg;
	private String input;
	private String send;
	private ServerKeyboardThread save;

	private int port;

	public ServerConnection(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		try {
			System.out.println("Server is activated!");
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Server: Connected");
				ServerAction newclient = new ServerAction(socket);
				ExecutorService pool = Executors.newFixedThreadPool(4);
				pool.execute(newclient);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
