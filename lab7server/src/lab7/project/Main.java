package lab7.project;


public class Main {

	public static void main(String[] args) throws Exception  {
		
		//DataBaseOp o = new DataBaseOp();

		
		ServerConnection obj1 = new ServerConnection(4671);
		ServerKeyboardThread obj2 = new ServerKeyboardThread();
		Thread thread1 = new Thread(obj1);
		Thread thread2 = new Thread(obj2);
		
		thread1.start();
        thread2.start();
		
	}

}
