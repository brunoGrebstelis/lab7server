package lab7.project;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.plaf.synth.SynthPopupMenuUI;

import obj.Coordinates;
import obj.Location;
import obj.Objects;
import obj.Person;
import obj.PrintOut;
import obj.UserInfo;

public class ServerAction implements Runnable{
	
	private DataBaseOp doc = new DataBaseOp();
	private IDoperations check = new IDoperations(doc.getShowPersons2());
	private Remove rem = new Remove(doc.getShowPersons2());
	private Save save = new Save();
	private MD2 md2 = new MD2(doc.getUserinfo());
	private Socket socket;
	private ObjectOutputStream outStream = null;
	private ObjectInputStream inStream = null;
	private Objects msgRes, msg;
	private String input;
	private String send;
	private int userID=-1;
	private String thislogin;
	
	public ServerAction(Socket socket){
		this.socket=socket;		
	}

	@Override
	public void run() {
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());

			do {
				msgRes = (Objects) inStream.readObject();
				this.input = msgRes.message;
				doc = new DataBaseOp();
				//doc.getShowPersons2().forEach((k,v)->System.out.println("1 name: "+v.getName()+" id: "+v.getID()));
				System.out.println("From client: " + input);

				switch (input) {

				case "printOut":
					outStream.reset();
					outStream.writeObject(new PrintOut(doc.getShowPersons2()));
					break;

				case "insert":
					Person msgResPerson = (Person) inStream.readObject();
					save.insertPerson(msgResPerson,doc.getShowPersons2());
					outStream.writeObject(new Objects("Element added!"));
					break;

				case "remove":
					String t = null;
					String b = null;
					msgRes = (Objects) inStream.readObject();
					rem = new Remove(doc.getShowPersons2());
					check = new IDoperations(doc.getShowPersons2());
					System.out.println("msg: "+msgRes.message);
					if (!check.testIfCorUser(Long.valueOf(msgRes.message),getUserID())) {
						t="1";
						b="";
					}
					if(!check.testIfIDExist(Long.valueOf(msgRes.message))) {
						t="2";
						b="";
					}
					if(t==null) {
						t="3";
						b=rem.removeID(Long.valueOf(msgRes.message));
					}
					save.savePerson(doc.getShowPersons2());
					//doc.getShowPersons2().forEach((k,v)->System.out.println("2 name: "+v.getName()+" id: "+v.getID()));
					outStream.writeObject(new Objects(t));
					outStream.writeObject(new Objects(b));
					break;

				case "remove_greater":
					do {
						rem = new Remove(doc.getShowPersons2());
						msgRes = (Objects) inStream.readObject();
						check = new IDoperations(doc.getShowPersons2());
						if (!check.testIfKeyExist(Long.valueOf(msgRes.message))) {
							send = "1";
						} else {
							send = "2";
						}
						outStream.writeObject(new Objects(send));
					} while (!check.testIfKeyExist(Long.valueOf(msgRes.message)));
					rem.remove_greater(Long.valueOf(msgRes.message),getUserID());
					outStream.writeObject(
							new Objects("Elements key>= " + Long.valueOf(msgRes.message) + " where deleted"));
					save.savePerson(doc.getShowPersons2());
					break;

				case "clear":
					rem = new Remove(doc.getShowPersons2());
					outStream.reset();
					outStream.writeObject(new Objects(rem.clear(getUserID())));
					save.savePerson(doc.getShowPersons2());
					break;

				case "replace_if_greater":
					Long checkID = 0L;
					Long checkIDNew = 0L;
					rem = new Remove(doc.getShowPersons2());
					check = new IDoperations(doc.getShowPersons2());
					do {
						msgRes = (Objects) inStream.readObject();
						checkID = Long.valueOf(msgRes.message);
						msgRes = (Objects) inStream.readObject();
						checkIDNew = Long.valueOf(msgRes.message);
						if (check.testIfIDExist(checkID) && !check.testIfIDExist(checkIDNew)
								&&check.testIfCorUser(checkID, getUserID())) {
							send = "2";
						} else {
							send = "1";
						}
						outStream.writeObject(new Objects(send));
					} while (send.equals("1"));
					outStream.writeObject(new Objects(rem.removeID(checkID)));
					save.savePerson(doc.getShowPersons2());
					break;

				case "remove_all_by_birthday":
					rem = new Remove(doc.getShowPersons2());
					msgRes = (Objects) inStream.readObject();
					System.out.println(msgRes.message);
					outStream.writeObject(new Objects(rem.remove_all_by_birthday(msgRes.message,getUserID())));
					save.savePerson(doc.getShowPersons2());
					break;
					
				case "user":
					msgRes = (Objects) inStream.readObject();
					thislogin=msgRes.message;
					System.out.println(msgRes.message);
					if(!md2.testLogin(msgRes.message)) {
						System.out.println("Login does not exist");
						outStream.writeObject(new Objects("1"));
					}else {
						outStream.writeObject(new Objects("2"));
						msgRes = (Objects) inStream.readObject();	
						System.out.println(msgRes.message);
						int user = md2.testPassword(msgRes.message,thislogin);
						if(user==-1) {
							outStream.writeObject(new Objects("-1"));
							userID=user;
						}else {
							System.out.println("User "+thislogin+" is connected! user id: "+user);
							outStream.writeObject(new Objects(String.valueOf(user)));
							userID=user;
						}
					}
					break;
					
				case "newuser":
					String login;
					String password;
					msgRes = (Objects) inStream.readObject();
					login=msgRes.message;
					msgRes = (Objects) inStream.readObject();
					password=md2.encryptThisString(msgRes.message);			
					UserInfo newuser = new UserInfo(doc.userinfo.size()+1,login,password);
					doc.userinfo.put(doc.userinfo.size(), newuser);
					doc.userinfo.forEach((k,v)->System.out.println("k: "+k+"; userID: "+v.getUser_id()+
							"; login: "+v.getLogin()+"; password: "+v.getPassword()));
					save.saveUser(doc.getUserinfo());
					break;
					
				}

			} while (!msgRes.message.equals("exit"));
			System.out.println("Client "+getLogin()+" is disconnected");
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public String getLogin() {
		return this.thislogin;
	}
}
