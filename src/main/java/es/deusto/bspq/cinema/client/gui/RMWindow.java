package es.deusto.bspq.cinema.client.gui;

import javax.swing.*;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

public class RMWindow extends JFrame implements ActionListener {
	
	final static Logger logger = Logger.getLogger(RMWindow.class);
	
	private static final long serialVersionUID = 1L;

	private JLabel l1, l2, l3, l4, l5, l6, l7;
	private JTextField tf1, tf2, tf3, tf4;
	private JButton btn1, btn2, btnLoginWindow;
	private JPasswordField p1, p2;
	private JPanel buttons;
	private JPanel fields;
	private JPanel password;
	
	// App controller
	private CMController controller;
	
	ResourceBundle messages;

	/**
	 * Class Constructor.
	 * @param controller Controller of the application.
 	 * @param messages Strings of certain language. 
	 */
	public RMWindow(CMController controller, ResourceBundle messages) {
		this.controller = controller;
		this.messages = messages;
		initComponents();
	}
	
	/**
	 * Initialize Components.
	 */
	private void initComponents() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		getContentPane().setLayout(new GridLayout(3, 1));
		
		buttons = new JPanel();
		fields = new JPanel();
		password = new JPanel();
		l2 = new JLabel(messages.getString("name") + ":");
		l3 = new JLabel(messages.getString("surname") + ":");
		l4 = new JLabel(messages.getString("email") + ":");
		l5 = new JLabel(messages.getString("createPassword") + ":");
		l6 = new JLabel(messages.getString("confirmPassword") + ":");
		l7 = new JLabel(messages.getString("birthday") + ":");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		p1 = new JPasswordField();
		p2 = new JPasswordField();
		btn1 = new JButton(messages.getString("submit"));
		btn2 = new JButton(messages.getString("clear"));
		btnLoginWindow = new JButton(messages.getString("goToLoginWindow"));

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btnLoginWindow.addActionListener(this);

		l1 = new JLabel(messages.getString("registerAsAMember"));
		l1.setForeground(Color.black);
		
		buttons.add(btn1);
		buttons.add(btn2);
		buttons.add(btnLoginWindow);
		
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
		
		pack();
	}
	
	/**
	 * Center the CMWindow.
	 */
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}

	/**
	 * Creates a Window for register as a Member.
	 * @param e Action performed over Button 
	 */
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
				JOptionPane.showMessageDialog(btn1, messages.getString("emptyFields"));
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
					JOptionPane.showMessageDialog(btn1, messages.getString("passwordsMatch"));
				}
			}
		} 
		else if (e.getSource() == btnLoginWindow) {
			LoginWindow loginWindow = new LoginWindow(controller, messages);
			loginWindow.centreWindow();
			loginWindow.setVisible(true);
			dispose();
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
	
	/** 
	 * Exit the Application 
	 * @param evt Window event performed over Button 
	 */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}
	
}
