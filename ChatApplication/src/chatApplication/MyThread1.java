package chatApplication;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread1 implements Runnable {
	Socket s1, s, s2;
	static ArrayList al1;
	DataInputStream ddin;
	String sname;

	MyThread1(Socket s1, ArrayList al1, Socket s, Socket s2) {
		this.s1 = s1;
		MyThread1.al1 = al1;
		this.s = s;
		this.s2 = s2;
	}

	@Override
	public void run() {
		try {
			ddin = new DataInputStream(s1.getInputStream());
			while (true) {
				sname = ddin.readUTF();
				System.out.println("Exit  :" + sname);
				MyThread2.alname.remove(sname);// remove the logout user name
												// from arraylist
				MyThread2.every();
				al1.remove(s1);

				MyThread.al.remove(s);
				MyThread2.al2.remove(s2);
				if (al1.isEmpty())
					System.exit(0); // all client has been logout
			}
		} catch (Exception ie) {
		}
	}
}
