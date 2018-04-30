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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MIWindow extends JFrame {

	final static Logger logger = Logger.getLogger(MIWindow.class);

	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	// Login user
	private String loginUser;
	
	private JFrame frmMemberInfo;
	private JButton btnAction;
	private JButton btnCancel;
	private JPanel panelActions;
	private JPanel panelUpdate;
	private JLabel lblEmail;
	private JLabel lblName;
	private JTextField textField;
	private JLabel lblSurname;
	private JTextField textField_1;
	private JLabel lblBirthDay;
	private JTextField textField_2;
	private JLabel lblPoints;
	private JLabel lblPointsShow;
	private JLabel label_7;
	private JPasswordField passwordField_1;
	private JCheckBox chckbxDelete;
	

	/**
	 * Create the application.
	 */
	public MIWindow(CMController controller, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		initComponents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {
		frmMemberInfo = new JFrame();
		frmMemberInfo.setTitle("Member Info");
		frmMemberInfo.setBounds(100, 100, 360, 280);
		frmMemberInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMemberInfo.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelActions = new JPanel();
		frmMemberInfo.getContentPane().add(panelActions, BorderLayout.SOUTH);
		panelActions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelActions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_7 = new JLabel("Password");
		panelActions.add(label_7);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		passwordField_1.setColumns(12);
		panelActions.add(passwordField_1);
		
		btnAction = new JButton("Update");
		btnAction.setEnabled(false);
		panelActions.add(btnAction);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelActions.add(btnCancel);
		
		panelUpdate = new JPanel();
		frmMemberInfo.getContentPane().add(panelUpdate, BorderLayout.CENTER);
		panelUpdate.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_panelUpdate = new GridBagLayout();
		gbl_panelUpdate.columnWidths = new int[]{0, 0, 0};
		gbl_panelUpdate.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panelUpdate.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelUpdate.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelUpdate.setLayout(gbl_panelUpdate);
		
		lblEmail = new JLabel("<dynamic>");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 0;
		panelUpdate.add(lblEmail, gbc_lblEmail);
		
		lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		panelUpdate.add(lblName, gbc_lblName);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panelUpdate.add(textField, gbc_textField);
		
		lblSurname = new JLabel("Surname");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 0;
		gbc_lblSurname.gridy = 2;
		panelUpdate.add(lblSurname, gbc_lblSurname);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panelUpdate.add(textField_1, gbc_textField_1);
		
		lblBirthDay = new JLabel("Birthday");
		GridBagConstraints gbc_lblBirthDay = new GridBagConstraints();
		gbc_lblBirthDay.anchor = GridBagConstraints.WEST;
		gbc_lblBirthDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthDay.gridx = 0;
		gbc_lblBirthDay.gridy = 3;
		panelUpdate.add(lblBirthDay, gbc_lblBirthDay);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panelUpdate.add(textField_2, gbc_textField_2);
		
		lblPoints = new JLabel("Points");
		GridBagConstraints gbc_lblPoints = new GridBagConstraints();
		gbc_lblPoints.anchor = GridBagConstraints.WEST;
		gbc_lblPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblPoints.gridx = 0;
		gbc_lblPoints.gridy = 4;
		panelUpdate.add(lblPoints, gbc_lblPoints);
		
		lblPointsShow = new JLabel("");
		GridBagConstraints gbc_lblPointsShow = new GridBagConstraints();
		gbc_lblPointsShow.insets = new Insets(0, 0, 5, 0);
		gbc_lblPointsShow.gridx = 1;
		gbc_lblPointsShow.gridy = 4;
		panelUpdate.add(lblPointsShow, gbc_lblPointsShow);
		
		chckbxDelete = new JCheckBox("I wish to delete my account");
		chckbxDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxDelete.isSelected()) {
					btnAction.setText("Delete");
				} else {
					btnAction.setText("Update");
				}
			}
		});
		GridBagConstraints gbc_chckbxDelete = new GridBagConstraints();
		gbc_chckbxDelete.gridx = 1;
		gbc_chckbxDelete.gridy = 5;
		panelUpdate.add(chckbxDelete, gbc_chckbxDelete);
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
}
