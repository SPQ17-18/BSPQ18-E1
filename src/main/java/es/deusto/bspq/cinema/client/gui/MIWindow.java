package es.deusto.bspq.cinema.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MIWindow {

	private JFrame frmMemberInfo;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtName_edit;
	private JTextField txtSurname;
	private JTextField txtSurname_edit;
	private JTextField txtPassword;
	private JPasswordField pass;
	private JPasswordField passRepeat;
	private JTextField txtBirthday;
	private JTextField txtBirthay_edit;
	private JTextField txtPoints;
	private JTextField txtPoints_show;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MIWindow window = new MIWindow();
					window.frmMemberInfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMemberInfo = new JFrame();
		frmMemberInfo.setTitle("Member Info");
		frmMemberInfo.setBounds(100, 100, 450, 320);
		frmMemberInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMemberInfo.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelMember = new JPanel();
		panelMember.setBorder(new TitledBorder(null, "Member", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmMemberInfo.getContentPane().add(panelMember);
		panelMember.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMember.add(panelInfo);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[]{0, 0, 0};
		gbl_panelInfo.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfo.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfo.setLayout(gbl_panelInfo);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 2;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.anchor = GridBagConstraints.NORTH;
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 0;
		gbc_txtEmail.gridy = 0;
		panelInfo.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setText("Name");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 1;
		panelInfo.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		txtName_edit = new JTextField();
		GridBagConstraints gbc_txtName_edit = new GridBagConstraints();
		gbc_txtName_edit.insets = new Insets(0, 0, 5, 0);
		gbc_txtName_edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName_edit.gridx = 1;
		gbc_txtName_edit.gridy = 1;
		panelInfo.add(txtName_edit, gbc_txtName_edit);
		txtName_edit.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setEditable(false);
		txtSurname.setText("Surname");
		GridBagConstraints gbc_txtSurname = new GridBagConstraints();
		gbc_txtSurname.insets = new Insets(0, 0, 5, 5);
		gbc_txtSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSurname.gridx = 0;
		gbc_txtSurname.gridy = 2;
		panelInfo.add(txtSurname, gbc_txtSurname);
		txtSurname.setColumns(10);
		
		txtSurname_edit = new JTextField();
		GridBagConstraints gbc_txtSurname_edit = new GridBagConstraints();
		gbc_txtSurname_edit.insets = new Insets(0, 0, 5, 0);
		gbc_txtSurname_edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSurname_edit.gridx = 1;
		gbc_txtSurname_edit.gridy = 2;
		panelInfo.add(txtSurname_edit, gbc_txtSurname_edit);
		txtSurname_edit.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setEditable(false);
		txtPassword.setText("Password");
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 0;
		gbc_txtPassword.gridy = 3;
		panelInfo.add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		pass = new JPasswordField();
		GridBagConstraints gbc_pass = new GridBagConstraints();
		gbc_pass.insets = new Insets(0, 0, 5, 0);
		gbc_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass.gridx = 1;
		gbc_pass.gridy = 3;
		panelInfo.add(pass, gbc_pass);
		
		passRepeat = new JPasswordField();
		GridBagConstraints gbc_passRepeat = new GridBagConstraints();
		gbc_passRepeat.insets = new Insets(0, 0, 5, 0);
		gbc_passRepeat.fill = GridBagConstraints.HORIZONTAL;
		gbc_passRepeat.gridx = 1;
		gbc_passRepeat.gridy = 4;
		panelInfo.add(passRepeat, gbc_passRepeat);
		
		txtBirthday = new JTextField();
		txtBirthday.setEditable(false);
		txtBirthday.setText("Birthday");
		GridBagConstraints gbc_txtBirthday = new GridBagConstraints();
		gbc_txtBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_txtBirthday.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthday.gridx = 0;
		gbc_txtBirthday.gridy = 5;
		panelInfo.add(txtBirthday, gbc_txtBirthday);
		txtBirthday.setColumns(10);
		
		txtBirthay_edit = new JTextField();
		GridBagConstraints gbc_txtBirthay_edit = new GridBagConstraints();
		gbc_txtBirthay_edit.insets = new Insets(0, 0, 5, 0);
		gbc_txtBirthay_edit.anchor = GridBagConstraints.NORTH;
		gbc_txtBirthay_edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthay_edit.gridx = 1;
		gbc_txtBirthay_edit.gridy = 5;
		panelInfo.add(txtBirthay_edit, gbc_txtBirthay_edit);
		txtBirthay_edit.setColumns(10);
		
		txtPoints = new JTextField();
		txtPoints.setEditable(false);
		txtPoints.setText("Points");
		GridBagConstraints gbc_txtPoints = new GridBagConstraints();
		gbc_txtPoints.insets = new Insets(0, 0, 0, 5);
		gbc_txtPoints.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPoints.gridx = 0;
		gbc_txtPoints.gridy = 6;
		panelInfo.add(txtPoints, gbc_txtPoints);
		txtPoints.setColumns(10);
		
		txtPoints_show = new JTextField();
		txtPoints_show.setEditable(false);
		GridBagConstraints gbc_txtPoints_show = new GridBagConstraints();
		gbc_txtPoints_show.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPoints_show.gridx = 1;
		gbc_txtPoints_show.gridy = 6;
		panelInfo.add(txtPoints_show, gbc_txtPoints_show);
		txtPoints_show.setColumns(10);
		
		JPanel panelActions = new JPanel();
		panelActions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMember.add(panelActions, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		panelActions.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		panelActions.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelActions.add(btnCancel);
	}

}
