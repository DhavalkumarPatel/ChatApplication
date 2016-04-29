package chatApplication;
import java.io.DataInputStream;

import javax.swing.DefaultListModel;

//class is used to received the messages
class MaintainMessage implements Runnable {
	DataInputStream din;
	DefaultListModel model;

	MaintainMessage(DataInputStream din, DefaultListModel model) {
		this.din = din;
		this.model = model;
	}

	@Override
	public void run() {
		String str1 = "";
		while (true) {
			try {
				str1 = din.readUTF(); // receive the message
				// add the message in list box
				model.addElement(str1);
			} catch (Exception e) {
			}
		}
	}
}
