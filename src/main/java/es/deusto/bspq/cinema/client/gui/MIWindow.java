package es.deusto.bspq.cinema.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class MIWindow extends JFrame {

	final static Logger logger = Logger.getLogger(MIWindow.class);

	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	// Login user
	private String loginUser;
	
	private JButton btnAction;
	private JButton btnCancel;
	private JPanel panelActions;
	private JPanel panelInfo;
	private JPanel panelPoints;
	private JLabel lblEmail;
	private JLabel lblName;
	private JTextField textField;
	private JLabel lblSurname;
	private JTextField textField_1;
	private JLabel lblBirthDay;
	private JTextField textField_2;
	private JLabel labelPass;
	private JPasswordField passwordField;
	private JCheckBox chckbxDelete;
	private JLabel lblInfo;
	private JLabel lblPoints;
	private JLabel lblPointsNumber;
	
	ResourceBundle messages;
	
	public MIWindow(CMController controller, ResourceBundle messages, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		this.messages = messages;
		initComponents();
	}

	private void initComponents() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		setTitle(messages.getString("memberInfo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelPoints = new JPanel();
		getContentPane().add(panelPoints, BorderLayout.CENTER);
		panelPoints.setBorder(new TitledBorder(null, messages.getString("points"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPoints.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblPoints = new JLabel(messages.getString("currentPoints"));
		panelPoints.add(lblPoints);
		lblPointsNumber = new JLabel("controller call here"); //TODO controller.getMemberPoints(loginUser);
		panelPoints.add(lblPointsNumber);
		
		panelActions = new JPanel();
		getContentPane().add(panelActions, BorderLayout.SOUTH);
		panelActions.setBorder(new TitledBorder(null, messages.getString("actions"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelActions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelPass = new JLabel(messages.getString("password"));
		panelActions.add(labelPass);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		passwordField.setColumns(12);
		panelActions.add(passwordField);
		
		btnAction = new JButton(messages.getString("delete"));
		btnAction.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (chckbxDelete.isSelected()) {
					if (controller.identifyMember(loginUser, String.valueOf(passwordField.getPassword()))) {
						controller.cancelMembership(loginUser, String.valueOf(passwordField.getPassword()));
						logger.info("Membership canceled for "+ loginUser);
					} else {
						lblInfo.setText(messages.getString("wrongPassword"));
						lblInfo.setVisible(true);
					}
				}
				else {
					lblInfo.setText(messages.getString("checkboxNotSelected"));
					lblInfo.setVisible(true);
				}
			}
		});
		panelActions.add(btnAction);
		
		btnCancel = new JButton(messages.getString("cancel"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CMWindow cmWindow = new CMWindow(controller, messages, loginUser);
				cmWindow.centreWindow();
				cmWindow.setVisible(true);
				dispose();
			}
		});
		panelActions.add(btnCancel);
		
		panelInfo = new JPanel();
		getContentPane().add(panelInfo, BorderLayout.NORTH);
		panelInfo.setBorder(new TitledBorder(null, messages.getString("info"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[]{0, 0, 0};
		gbl_panelInfo.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panelInfo.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelInfo.setLayout(gbl_panelInfo);
		
		lblEmail = new JLabel(loginUser);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 0;
		panelInfo.add(lblEmail, gbc_lblEmail);
		
		lblName = new JLabel(messages.getString("name"));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panelInfo.add(lblName, gbc_lblName);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panelInfo.add(textField, gbc_textField);
		
		lblSurname = new JLabel(messages.getString("surname"));
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 0;
		gbc_lblSurname.gridy = 2;
		panelInfo.add(lblSurname, gbc_lblSurname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panelInfo.add(textField_1, gbc_textField_1);
		
		lblBirthDay = new JLabel(messages.getString("birthday"));
		GridBagConstraints gbc_lblBirthDay = new GridBagConstraints();
		gbc_lblBirthDay.anchor = GridBagConstraints.WEST;
		gbc_lblBirthDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthDay.gridx = 0;
		gbc_lblBirthDay.gridy = 3;
		panelInfo.add(lblBirthDay, gbc_lblBirthDay);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panelInfo.add(textField_2, gbc_textField_2);
	
		chckbxDelete = new JCheckBox(messages.getString("wishToDeleteAccount"));
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 0, 5);
		gbc_lblInfo.gridx = 0;
		gbc_lblInfo.gridy = 5;
		panelInfo.add(lblInfo, gbc_lblInfo);
		GridBagConstraints gbc_chckbxDelete = new GridBagConstraints();
		gbc_chckbxDelete.gridx = 1;
		gbc_chckbxDelete.gridy = 5;
		panelInfo.add(chckbxDelete, gbc_chckbxDelete);
		
		pack();
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
