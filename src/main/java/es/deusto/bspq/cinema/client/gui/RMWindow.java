package es.deusto.bspq.cinema.client.gui;

import javax.swing.*;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import java.awt.*;
import java.awt.event.*;

public class RMWindow extends JFrame implements ActionListener {
	
	final static Logger logger = Logger.getLogger(RMWindow.class);
	
	private static final long serialVersionUID = 1L;

	private JLabel l1, l2, l3, l4, l5, l6, l7;
	private JTextField tf1, tf2, tf3, tf4;
	private JButton btn1, btn2;
	private JPasswordField p1, p2;
	private JPanel buttons;
	private JPanel fields;
	private JPanel password;
	
	// App controller
	private CMController controller;

	public RMWindow(CMController controller) {
		this.controller = controller;

		getContentPane().setLayout(new GridLayout(3, 1));
		
		buttons = new JPanel();
		fields = new JPanel();
		password = new JPanel();
		l2 = new JLabel("Name:");
		l3 = new JLabel("Surname:");
		l4 = new JLabel("Email:");
		l5 = new JLabel("Create Password:");
		l6 = new JLabel("Confirm Password:");
		l7 = new JLabel("Birthday:");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		p1 = new JPasswordField();
		p2 = new JPasswordField();
		btn1 = new JButton("Submit");
		btn2 = new JButton("Clear");

		btn1.addActionListener(this);
		btn2.addActionListener(this);

		l1 = new JLabel("Register as a Member");
		l1.setForeground(Color.black);
		
		buttons.add(btn1);
		buttons.add(btn2);
		
		fields.add(l2);
		tf1.setColumns(10);
		fields.add(tf1);
		fields.add(l3);
		tf2.setColumns(10);
		fields.add(tf2);
		fields.add(l4);
		tf3.setColumns(10);
		fields.add(tf3);
		fields.add(l7);
		tf4.setColumns(10);
		fields.add(tf4);
		password.add(l5);
		p1.setColumns(10);
		password.add(p1);
		password.add(l6);
		p2.setColumns(10);
		password.add(p2);
		
		getContentPane().add(fields);
		getContentPane().add(password);
		getContentPane().add(buttons);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			String s1 = tf1.getText();
			String s2 = tf2.getText();
			String s3 = tf3.getText();

			char[] c1 = p1.getPassword();
			char[] c2 = p2.getPassword();
			String s4 = new String(c1);
			String s5 = new String(c2);

			String s6 = tf4.getText();

			if ((tf1.getText().equals("")) || (tf2.getText().equals("")) || (tf3.getText().equals(""))
					|| (tf4.getText().equals("") || (s4.isEmpty()) || s5.isEmpty())) {
				JOptionPane.showMessageDialog(btn1, "Fields are empty");
			} 
			else {
				if ((s4.equals(s5)) && (!(s4.isEmpty()) || !(s5.isEmpty()))) {
					try {
						MemberDTO member = new MemberDTO();
						member.setName(s1);
						member.setSurname(s2);
						member.setEmail(s3);
						member.setPassword(s4);
						member.setBirthday(s6);

						if (controller.registerMember(member)) {
							logger.info("Member registration successful");
						}
					} catch (Exception ex) {
						
					}
				} 
				else {
					JOptionPane.showMessageDialog(btn1, "Passwords do not match");
				}
			}
		} 
		else {
			tf1.setText("");
			tf2.setText("");
			p1.setText("");
			p2.setText("");
			tf3.setText("");
			tf4.setText("");
		}
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
	/** Exit the Application */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}
	
}
