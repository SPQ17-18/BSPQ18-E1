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
	
	private JTextField tfUsernameM;
    private JPasswordField pfPasswordM;
    private JLabel lbUsernameM;
    private JLabel lbPasswordM;
	private JTextField tfUsernameE;
    private JPasswordField pfPasswordE;
    private JLabel lbUsernameE;
    private JLabel lbPasswordE;
    private JButton btnLogin;
    private JButton btnCancel;
    private JTabbedPane tabbedPane;
 
    public LoginWindow(String args[]) {
    	
    	try {
			controller = new CMController(args);
		} catch (RemoteException e) {
			logger.error("Remote exception: " + e.getMessage());
			e.printStackTrace();
		}
    	
        setResizable(false);
    	
        JPanel panelEmployee = new JPanel(new GridBagLayout());
        JPanel panelMember = new JPanel(new GridBagLayout());
        
    	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.addTab("Employee", null, panelEmployee, null);
    	tabbedPane.addTab("Member", null, panelMember, null);
 
    	GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsernameM = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panelEmployee.add(lbUsernameM, cs);
        panelMember.add(lbUsernameM, cs);
 
        tfUsernameM = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panelMember.add(tfUsernameM, cs);
 
        lbPasswordM = new JLabel("Password: ");
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
 
        lbUsernameE = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panelEmployee.add(lbUsernameE, cs);
 
        tfUsernameE = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panelEmployee.add(tfUsernameE, cs);
 
        lbPasswordE = new JLabel("Password: ");
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
 
        btnLogin = new JButton("Sign in");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (tabbedPane.getSelectedIndex() == 0) {
//  	        	if (controller.identifyEmployee(tfUsernameM.getText().trim(), String.valueOf(pfPasswordM.getPassword()))) {
        			JOptionPane.showMessageDialog(LoginWindow.this,
        					"Hi " + tfUsernameM.getText().trim() + "! You have successfully logged in Cinema Manager as a member.",
        					"Login",
        					JOptionPane.INFORMATION_MESSAGE);
        			logger.info("Successfully logged as a member.");
        			CMWindow cmWindow = new CMWindow(controller);
        			cmWindow.centreWindow();
        			cmWindow.setVisible(true);
        			dispose();
        		}
        		else {
        			if (controller.identifyMember(tfUsernameM.getText().trim(), String.valueOf(pfPasswordM.getPassword()))) {
	        			JOptionPane.showMessageDialog(LoginWindow.this,
	        					"Hi " + tfUsernameM.getText().trim() + "! You have successfully logged in Cinema Manager as a employee.",
	        					"Login",
	        					JOptionPane.INFORMATION_MESSAGE);
	        			logger.info("Successfully logged as an employee.");
	        			CMAWindow cmaWindow = new CMAWindow(controller);
	        			cmaWindow.centreWindow();
	        			cmaWindow.setVisible(true);
	        			dispose();
        			}
        			else {
        				JOptionPane.showMessageDialog(LoginWindow.this,
	        					"Wrong email or password.",
	        					"Bad credentials",
	        					JOptionPane.INFORMATION_MESSAGE);
	        			logger.info("Wrong email or password: " + tfUsernameM.getText().trim() + " " +  String.valueOf(pfPasswordM.getPassword()));
        			}
        		}
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
 
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
    }
    
    public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
  
    
    public static void main(String[] args) throws RemoteException {
		final LoginWindow window = new LoginWindow(args);
		window.centreWindow();
		window.setVisible(true);
	}
    
}
