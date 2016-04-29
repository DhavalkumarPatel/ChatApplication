package chatApplication;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ServerSocket ss;
	Socket s;
	ArrayList al = new ArrayList();
	ArrayList al1 = new ArrayList();
	ArrayList al2 = new ArrayList();
	ArrayList alname = new ArrayList();
	Socket s1, s2;

	Server() throws IOException 
	{
		ss = new ServerSocket(1004); // create server socket
		while (true) 
		{
			s = ss.accept(); // accept the client socket
			s1 = ss.accept();
			s2 = ss.accept();
			
			al.add(s); // add the client socket in arraylist
			al1.add(s1);
			al2.add(s2);
			
			System.out.println("Client is Connected");
			
			MyThread2 m = new MyThread2(s2, al2, alname); // new thread for maintaining the list of user name
			Thread t2 = new Thread(m);
			t2.start();

			MyThread r = new MyThread(s, al);// new thread for receive and sending the messages
			Thread t = new Thread(r);
			t.start();

			MyThread1 my = new MyThread1(s1, al1, s, s2); // new thread for update the list of user name
			Thread t1 = new Thread(my);
			t1.start();
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
		}
	}
}
