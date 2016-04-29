package chatApplication;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

class MyThread implements Runnable {
	Socket s;
	static ArrayList al;
	DataInputStream din;
	DataOutputStream dout;

	MyThread(Socket s, ArrayList al) {
		this.s = s;
		MyThread.al = al;
	}

	@Override
	public void run() {
		String str;
		int i = 1;
		try {
			din = new DataInputStream(s.getInputStream());
		} catch (Exception e) {
		}

		while (i == 1) {
			try {

				str = din.readUTF(); // read the message
				distribute(str);
			} catch (IOException e) {
			}
		}
	}

	// send it to all clients
	public void distribute(String str) throws IOException {
		Iterator i = al.iterator();
		Socket st;
		while (i.hasNext()) {
			st = (Socket) i.next();
			dout = new DataOutputStream(st.getOutputStream());
			dout.writeUTF(str);
			dout.flush();
		}
	}
}
