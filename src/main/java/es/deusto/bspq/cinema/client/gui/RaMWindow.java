package es.deusto.bspq.cinema.client.gui;

import javax.swing.*;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.remote.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;



public class RaMWindow extends JFrame implements ActionListener {

	private JLabel l1, l2, l3, l4, l5, l6, l7;
	private JTextField tf1, tf2, tf3, tf4;
	private JButton btn1, btn2;
	private JPasswordField p1, p2;
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private CMController controller;


	public RaMWindow() {

		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(430, 350);
		setTitle("RaMWindow");
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		l2 = new JLabel("Name:");
		l3 = new JLabel("Surname:");
		l4 = new JLabel("Email:");
		l5 = new JLabel("Create Passowrd:");
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

		panel.add(l1);
		l1.setBounds(8, 1, 400, 30);
		panel.add(l2);
		l2.setBounds(8, 40, 200, 30);
		panel.add(l3);
		l3.setBounds(8, 75, 200, 30);
		panel.add(l4);
		l4.setBounds(8, 110, 200, 30);
		panel.add(l5);
		l5.setBounds(8, 145, 200, 30);
		panel.add(l6);
		l6.setBounds(8, 180, 200, 30);
		panel.add(l7);
		l7.setBounds(8, 215, 200, 30);

		panel.add(tf1);
		tf1.setBounds(200, 43, 200, 25);
		panel.add(tf2);
		tf2.setBounds(200, 78, 200, 25);
		panel.add(tf3);
		tf3.setBounds(200, 113, 200, 25);
		panel.add(tf4);
		tf4.setBounds(200, 218, 200, 25);

		panel.add(p1);
		p1.setBounds(200, 183, 200, 25);
		panel.add(p2);
		p2.setBounds(200, 148, 200, 25);

		panel.add(btn1);
		btn1.setBounds(200, 260, 100, 30);
		panel.add(btn2);
		btn2.setBounds(300, 260, 100, 30);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn1) {
			int x = 0;
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

			} else {

				if ((s4.equals(s5)) && (!(s4.isEmpty()) || !(s5.isEmpty()))) {
					try {
						
						MemberDTO member = new MemberDTO();
						member.setName(s1);
						member.setSurname(s2);
						member.setEmail(s3);
						member.setPassword(s4);
						member.setBirthday(s6);

						if (controller.getRegisterMember(member)) {
							JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else{
					JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
				}
			}
		} else {
			tf1.setText("");
			tf2.setText("");
			p1.setText("");
			p2.setText("");
			tf3.setText("");
			tf4.setText("");
		}
	}




	public static void main(String args[]) {
		new RaMWindow();
	}
}