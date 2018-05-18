package es.deusto.bspq.cinema.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;

public class LoginWindow extends JDialog {
	
	final static Logger logger = Logger.getLogger(LoginWindow.class);
	
	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	private JTextField tfEmailM;
	private JPasswordField pfPasswordM;
	private JLabel lbEmailM;
	private JLabel lbPasswordM;
	private JTextField tfUsernameE;
	private JPasswordField pfPasswordE;
	private JLabel lbUsernameE;
	private JLabel lbPasswordE;
	private JButton btnLogin;
	private JButton btnCancel;
	private JButton btnRegister;
	private JTabbedPane tabbedPane;
	
	ResourceBundle messages;
	
	/**
	 * Class Constructor.
	 * @param args[] Command line arguments.
	 */
	public LoginWindow(String args[]) {
		try {
			controller = new CMController(args);
		} catch (RemoteException e) {
			logger.error("Remote exception: " + e.getMessage());
			e.printStackTrace();
		}
		String language = new String(args[3]);
		String country = new String(args[4]);
		Locale currentLocale = new Locale(language, country);
		messages = ResourceBundle.getBundle("lang/messages", currentLocale);
		initComponents();
	}
	
	/**
	 * Class Constructor.
	 * @param controller Controller of the application.
 	 * @param messages Strings of certain language. 
	 */
	public LoginWindow(CMController controller, ResourceBundle messages) {
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
		
		JPanel panelEmployee = new JPanel(new GridBagLayout());
		JPanel panelMember = new JPanel(new GridBagLayout());

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab(messages.getString("employee"), null, panelEmployee, null);
		tabbedPane.addTab(messages.getString("member"), null, panelMember, null);
	 
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
 
		lbEmailM = new JLabel(messages.getString("email") + ": ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panelEmployee.add(lbEmailM, cs);
		panelMember.add(lbEmailM, cs);
 
		tfEmailM = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panelMember.add(tfEmailM, cs);
 
		lbPasswordM = new JLabel(messages.getString("password") + ": ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panelMember.add(lbPasswordM, cs);
 
		pfPasswordM = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panelMember.add(pfPasswordM, cs);
		panelMember.setBorder(new LineBorder(Color.GRAY));
        
		cs.fill = GridBagConstraints.HORIZONTAL;
 
		lbUsernameE = new JLabel(messages.getString("username") + ": ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panelEmployee.add(lbUsernameE, cs);
 
		tfUsernameE = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panelEmployee.add(tfUsernameE, cs);
 
		lbPasswordE = new JLabel(messages.getString("password") + ": ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panelEmployee.add(lbPasswordE, cs);
 
		pfPasswordE = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panelEmployee.add(pfPasswordE, cs);
		panelEmployee.setBorder(new LineBorder(Color.GRAY));
 
		btnLogin = new JButton(messages.getString("signIn"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					if (controller.identifyEmployee(tfUsernameE.getText().trim(), String.valueOf(pfPasswordE.getPassword()))) {
						JOptionPane.showMessageDialog(LoginWindow.this,
								messages.getString("greetings") + " " + tfUsernameE.getText().trim() + "! " + messages.getString("welcomeMessage") + " " + messages.getString("employee"),
								"Login",
								JOptionPane.INFORMATION_MESSAGE);
						logger.info(messages.getString("successfullyLoggedEmployee"));
						CMAWindow cmaWindow = new CMAWindow(controller, messages, tfUsernameE.getText().trim());
						cmaWindow.centreWindow();
						cmaWindow.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(LoginWindow.this,
								messages.getString("wrongSignIn"),
								messages.getString("badCredentials"),
								JOptionPane.INFORMATION_MESSAGE);
						logger.info(messages.getString("wrongUsernamePassword") + ": " + tfUsernameE.getText().trim() + " " +  String.valueOf(pfPasswordE.getPassword()));
					}
				}
				else {
					if (controller.identifyMember(tfEmailM.getText().trim(), String.valueOf(pfPasswordM.getPassword()))) {
						JOptionPane.showMessageDialog(LoginWindow.this,
								messages.getString("greetings") + " " + tfEmailM.getText().trim() + "! " + messages.getString("welcomeMessage") + " " + messages.getString("member"),
								"Login",
								JOptionPane.INFORMATION_MESSAGE);
						logger.info(messages.getString("successfullyLoggedMember"));
						CMWindow cmWindow = new CMWindow(controller, messages, tfEmailM.getText().trim());
						cmWindow.centreWindow();
						cmWindow.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(LoginWindow.this,
								messages.getString("wrongSignIn"),
								messages.getString("badCredentials"),
								JOptionPane.INFORMATION_MESSAGE);
						logger.info(messages.getString("wrongUsernamePassword") + ": " + tfEmailM.getText().trim() + " " +  String.valueOf(pfPasswordM.getPassword()));
					}
				}
		    }
		});
		
		btnCancel = new JButton(messages.getString("cancel"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnRegister = new JButton(messages.getString("register"));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RMWindow rmWindow = new RMWindow(controller, messages);
				rmWindow.centreWindow();
				rmWindow.setVisible(true);
				dispose();
			}
		});
        
		JPanel bp = new JPanel();
		bp.add(btnLogin);
		bp.add(btnCancel);
		bp.add(btnRegister);
 
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);
 
		pack();
	}
	    
	/**
	 * Center the CMAWindow.
	 */
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
	/** 
	 * Exit the Application 
 	 * @param evt Window event performed over Button
	 */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}
    
}
