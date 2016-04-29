package chatApplication;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

class MyThread2 implements Runnable {
	Socket s2;
	static ArrayList al2;
	static ArrayList alname;
	static DataInputStream din1;
	static DataOutputStream dout1;

	MyThread2(Socket s2, ArrayList al2, ArrayList alname) {
		this.s2 = s2;
		MyThread2.al2 = al2;
		MyThread2.alname = alname;
	}

	@Override
	public void run() {
		try {
			din1 = new DataInputStream(s2.getInputStream());
			alname.add(din1.readUTF()); // store the user name in arraylist
			every();
		} catch (Exception oe) {
			System.out.println("Main expression" + oe);
		}
	}

	// send the list of user name to all client
	static void every() throws Exception {
		Iterator i1 = al2.iterator();
		Socket st1;

		while (i1.hasNext()) {
			st1 = (Socket) i1.next();
			dout1 = new DataOutputStream(st1.getOutputStream());

			ObjectOutputStream obj = new ObjectOutputStream(dout1);
			obj.writeObject(alname); // write the list of users in stream of all
										// clients
			dout1.flush();
			obj.flush();
		}
	}
}
