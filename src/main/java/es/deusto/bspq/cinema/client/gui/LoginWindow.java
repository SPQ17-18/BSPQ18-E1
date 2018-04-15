package es.deusto.bspq.cinema.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;

public class LoginWindow extends JDialog {
	
	final static Logger logger = Logger.getLogger(LoginWindow.class);
	
	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
    private boolean employee = false;
 
    public LoginWindow(String args[]) {
    	
    	try {
			controller = new CMController(args);
		} catch (RemoteException e) {
			logger.error("Remote exception: " + e.getMessage());
			e.printStackTrace();
		}
    	
        setResizable(false);
    	
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Sign in");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//              if (controller.identify(getUsername(), getPassword())) {
//  	        	if (userIsEmployee) {
//		                JOptionPane.showMessageDialog(LoginWindow.this,
//		                        "Hi " + getUsername() + "! You have successfully logged in Cinema Manager as a employee.",
//		                        "Login",
//		                        JOptionPane.INFORMATION_MESSAGE);
//		                succeeded = true;
//		                employee = true;
//		                logger.info("Successfully logged as a user.");
//		                CMWindow cmWindow = new CMWindow(controller);
//		                cmWindow.centreWindow();
//		                cmWindow.setVisible(true);
//		                dispose();
//	        		}
//  	            else {
		                JOptionPane.showMessageDialog(LoginWindow.this,
		                        "Hi " + getUsername() + "! You have successfully logged in Cinema Manager as a member.",
		                        "Login",
		                        JOptionPane.INFORMATION_MESSAGE);
		                succeeded = true;
		                logger.info("Successfully logged as a member.");
		                CMWindow cmWindow = new CMWindow(controller);
		                cmWindow.centreWindow();
		                cmWindow.setVisible(true);
		                dispose();
//  	        	}
//              else {
//	                JOptionPane.showMessageDialog(LoginWindow.this,
//	                        "Invalid username or password",
//	                        "Login",
//	                        JOptionPane.ERROR_MESSAGE);
//	                logger.info("Invalid username and password.");
//	                // reset username and password
//	                tfUsername.setText("");
//	                pfPassword.setText("");
//	                succeeded = false;
//              }
	        }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
    }
    
    public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    
}
