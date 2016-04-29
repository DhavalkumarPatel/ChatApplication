package chatApplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;

// class is used to maintaning the list of user name
class MaintainClientList implements Runnable {
	DataOutputStream dout1;
	DefaultListModel model1;
	DataInputStream din1;
	String name, lname;

	ArrayList alname = new ArrayList(); // stores the list of user names
	ObjectInputStream obj; // read the list of user names
	int i = 0;

	MaintainClientList(DataOutputStream dout1, DefaultListModel model1, String name,
			DataInputStream din1) {
		this.dout1 = dout1;
		this.model1 = model1;
		this.name = name;
		this.din1 = din1;
	}

	@Override
	public void run() {
		try {
			dout1.writeUTF(name); // write the user name in output stream
			while (true) {
				obj = new ObjectInputStream(din1);
				// read the list of user names
				alname = (ArrayList) obj.readObject();
				if (i > 0)
					model1.clear();
				Iterator i1 = alname.iterator();
				System.out.println(alname);
				while (i1.hasNext()) {
					lname = (String) i1.next();
					i++;
					// add the user names in list box
					model1.addElement(lname);
				}
			}
		} catch (Exception oe) {
		}
	}
}
