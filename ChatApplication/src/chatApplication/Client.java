package chatApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//create the GUI of the client side
public class Client extends WindowAdapter implements ActionListener {
	JFrame frame;
	JList list;
	JList list1;
	JTextField tf;
	DefaultListModel model;
	DefaultListModel model1;
	JButton button;

	JButton lout;
	JScrollPane scrollpane;
	JScrollPane scrollpane1;
	JLabel label;
	Socket s, s1, s2;
	DataInputStream din;
	DataOutputStream dout;
	DataOutputStream dlout;
	DataOutputStream dout1;
	DataInputStream din1;
	String name;

	Client(String name) throws IOException {
		frame = new JFrame("Client Side");
		tf = new JTextField();
		model = new DefaultListModel();
		model1 = new DefaultListModel();
		label = new JLabel("Message");
		list = new JList(model);
		list1 = new JList(model1);
		button = new JButton("Send");
		lout = new JButton("Logout");
		scrollpane = new JScrollPane(list);
		scrollpane1 = new JScrollPane(list1);
		JPanel panel = new JPanel();
		button.addActionListener(this);
		lout.addActionListener(this);
		panel.add(tf);
		panel.add(button);
		panel.add(scrollpane);
		panel.add(label);
		panel.add(lout);
		panel.add(scrollpane1);
		scrollpane.setBounds(10, 20, 180, 150);
		scrollpane1.setBounds(250, 20, 100, 150);
		label.setBounds(20, 180, 80, 30);
		tf.setBounds(100, 180, 140, 30);
		button.setBounds(260, 180, 90, 30);
		lout.setBounds(260, 230, 90, 30);
		frame.add(panel);

		panel.setLayout(null);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.name = name;
		frame.addWindowListener(this);
		s = new Socket("localhost", 1004); // creates a socket object
		s1 = new Socket("localhost", 1004);
		s2 = new Socket("localhost", 1004);
		// create inputstream for a particular socket
		din = new DataInputStream(s.getInputStream());
		// create outputstream
		dout = new DataOutputStream(s.getOutputStream());
		// sending a message for login
		dout.writeUTF(name + " has Logged in");
		dlout = new DataOutputStream(s1.getOutputStream());
		dout1 = new DataOutputStream(s2.getOutputStream());
		din1 = new DataInputStream(s2.getInputStream());

		// creating a thread for maintaning the list of user name
		MaintainClientList m1 = new MaintainClientList(dout1, model1, name, din1);
		Thread t1 = new Thread(m1);
		t1.start();
		// creating a thread for receiving a messages
		MaintainMessage m = new MaintainMessage(din, model);
		Thread t = new Thread(m);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// sending the messages
		if (e.getSource() == button) {
			String str = "";
			str = tf.getText();
			tf.setText("");
			str = name + ": > " + str;
			try {

				dout.writeUTF(str);
				System.out.println(str);
				dout.flush();
			} catch (IOException ae) {
				System.out.println(ae);
			}
		}
		// client logout
		if (e.getSource() == lout) {
			frame.dispose();
			try {
				// sending the message for logout
				dout.writeUTF(name + " has Logged out");
				dlout.writeUTF(name);
				dlout.flush();
				Thread.currentThread();
				Thread.sleep(1000);
				System.exit(1);
			} catch (Exception oe) {
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent w) {
		try {
			dlout.writeUTF(name);
			dlout.flush();
			Thread.currentThread();
			Thread.sleep(1000);
			System.exit(1);
		} catch (Exception oe) {
		}
	}
}
