package es.deusto.bspq.cinema.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;

public class MMWindow extends JFrame {
	
	final static Logger logger = Logger.getLogger(MMWindow.class);

	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	// Login user
	private String loginUser;

	private JButton buttonSearch;
	private JButton buttonSearchAll;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel lblPoints;
	private JLabel lblPointsNumber;
	private JTextField email;
	private JList<String> membersList1;
	private JTextField name;
	private JTextField surname;
	private JPanel panelManageMembers;
	private JPanel panelButtons;
	private JPanel panelMembers;
	private JPanel panelControlM;
	private JPanel panelListMembers;
	private JPanel panelEmail;
	private JPanel panelName;
	private JPanel panelSurname;
	private JPanel panelUpdate;
	private JPanel panelFields;
	private JPanel panelPoints;
	private JTextField updateName;
	private JTextField updateSurname;
	private JTextField updateBirthday;
	private JTextField updatePoints;
	private JScrollPane scrollMembers;
	private JTabbedPane tabsTable;
	
	private List<MemberDTO> members;
	private DefaultListModel<String> membersList;
	
	private JPanel panelOptions = new JPanel();
	private JButton buttonManageCinema;
	
	ResourceBundle messages;

	/**
	 * Class Constructor.
	 * @param controller Controller of the application.
 	 * @param messages Strings of certain language. 
	 * @param loginUser Email of the user logged in.
	 */
	public MMWindow(CMController controller, ResourceBundle messages, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		this.messages = messages;
		initComponents();
		membersList = new DefaultListModel<String>();
		membersList1.setModel(membersList);
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
		
		panelManageMembers = new JPanel();
		panelMembers = new JPanel();
		panelListMembers = new JPanel();
		scrollMembers = new JScrollPane();
		membersList1 = new JList<String>();
		panelControlM = new JPanel();
		tabsTable = new JTabbedPane();
		panelEmail = new JPanel();
		jLabel3 = new JLabel();
		email = new JTextField();
		panelName = new JPanel();
		jLabel2 = new JLabel();
		name = new JTextField();
		panelSurname = new JPanel();
		jLabel1 = new JLabel();
		surname = new JTextField();
		panelUpdate = new JPanel();
		updateName = new JTextField();
		updateSurname = new JTextField();
		updateBirthday = new JTextField();
		updatePoints = new JTextField();
		panelButtons = new JPanel();
		buttonSearch = new JButton();
		buttonSearchAll = new JButton();
		buttonUpdate = new JButton();
		buttonDelete = new JButton();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		panelFields = new JPanel();
		panelPoints = new JPanel();
		buttonManageCinema = new JButton();
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelManageMembers.setLayout(new GridLayout(2, 1));
		
		buttonManageCinema = new JButton(messages.getString("goToManageCinemaWindow"));
		buttonManageCinema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonManageCinemaActionPerformed(evt);
			}
		});
		panelOptions.add(new JLabel(messages.getString("options") + ": "));
		panelOptions.add(buttonManageCinema);
		
		getContentPane().add(panelOptions, BorderLayout.NORTH);

		panelMembers.setLayout(new GridLayout(1, 2));
		panelMembers.setBorder(new TitledBorder(new EtchedBorder(), messages.getString("memberSection")));

		membersList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  try {
		    		  lblPointsNumber.setText(String.valueOf(controller.getMemberPoints(members.get(membersList1.getSelectedIndex()).getEmail())));
		    	  } catch (Exception e) {
		    		  
		    	  }
		      }
		};
		membersList1.addListSelectionListener(listSelectionListener);
		
		scrollMembers.setPreferredSize(new Dimension(100, 110));	
		scrollMembers.setViewportView(membersList1);
		
		panelListMembers.setLayout(new BorderLayout());
		panelListMembers.setBorder(new TitledBorder(messages.getString("memberSection")));
		panelListMembers.add(scrollMembers, BorderLayout.CENTER);

		panelMembers.add(panelListMembers);
		panelControlM.setLayout(new BorderLayout());
		panelControlM.setBorder(new TitledBorder(new TitledBorder(""), messages.getString("searchSettings")));
		jLabel3.setText(messages.getString("email") + ":");
		panelEmail.add(jLabel3);

		email.setColumns(15);
		panelEmail.add(email);

		tabsTable.addTab(messages.getString("email"), panelEmail);

		jLabel2.setText(messages.getString("name") + ": ");
		panelName.add(jLabel2);

		name.setColumns(7);
		panelName.add(name);

		tabsTable.addTab(messages.getString("name"), panelName);

		jLabel1.setText(messages.getString("surname") + ": ");
		panelSurname.add(jLabel1);

		surname.setColumns(7);
		panelSurname.add(surname);

		tabsTable.addTab(messages.getString("surname"), panelSurname);

		panelControlM.add(tabsTable, BorderLayout.CENTER);

		buttonSearch.setText(messages.getString("search"));
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchActionPerformed(evt);
			}
		});
		
		buttonSearchAll.setText(messages.getString("searchAll") + " " + messages.getString("members"));
		buttonSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchAllActionPerformed(evt);
			}
		});
		
		buttonUpdate.setText(messages.getString("update"));
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonUpdateActionPerformed(evt);
			}
		});
		
		buttonDelete.setText(messages.getString("delete"));
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonDeleteActionPerformed(evt);
			}
		});

		panelButtons.add(buttonSearch);
		panelButtons.add(buttonSearchAll);
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		panelControlM.add(panelButtons, BorderLayout.SOUTH);
		panelMembers.add(panelControlM);
		
		// Add members panel
		panelManageMembers.add(panelMembers);
		
		updateName.setColumns(7);
		updateSurname.setColumns(7);
		updateBirthday.setColumns(10);
		jLabel4.setText(messages.getString("name") + ": ");
		panelFields.add(jLabel4);
		panelFields.add(updateName);
		jLabel5.setText(messages.getString("surname") + ": ");
		panelFields.add(jLabel5);
		panelFields.add(updateSurname);
		jLabel6.setText(messages.getString("birthday") + ": ");
		panelFields.add(jLabel6);
		panelFields.add(updateBirthday);
		panelFields.setBorder(new TitledBorder(messages.getString("updateMemberFields")));
		
		panelPoints.setBorder(new TitledBorder(messages.getString("updateMemberPoints")));
		lblPoints = new JLabel(messages.getString("currentPoints"));
		panelPoints.add(lblPoints);
		lblPointsNumber = new JLabel("[Member not selected]");
		panelPoints.add(lblPointsNumber);
		updatePoints.setColumns(10);
		panelPoints.add(updatePoints);
		
		panelUpdate.setBorder(new TitledBorder(messages.getString("update") + " " + messages.getString("member")));
		panelUpdate.setLayout(new GridLayout(1, 2));
		panelUpdate.add(panelFields);
		panelUpdate.add(panelPoints);
		
		// Add update panel
		panelManageMembers.add(panelUpdate);
		
		getContentPane().add(panelManageMembers, BorderLayout.SOUTH);

		membersList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		membersList1.setFocusable(false);
		membersList1.setEnabled(true);
		
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
	 * Opens CMAWindow.
	 * @param evt Action performed over Button 
	 */
	private void buttonManageCinemaActionPerformed(ActionEvent evt) {
		CMAWindow cmaWindow = new CMAWindow(controller, messages, loginUser);
		cmaWindow.centreWindow();
		cmaWindow.setVisible(true);
		dispose();
	}
	
	private void buttonSearchActionPerformed(ActionEvent evt) {
		
	}
	
	/**
	 * Searches for Members.
	 * @param evt Action performed over Button 
	 */
	private void buttonSearchAllActionPerformed(ActionEvent evt) {
		members = controller.getAllMembers();
		updateLists(members);
		cleanSearchDetails();
		logger.info(messages.getString("allMembersRetrieved"));
	}
	
	/**
	 * Updates attributes of Members.
	 * @param evt Action performed over Button 
	 */
	private void buttonUpdateActionPerformed(ActionEvent evt) {
		MemberDTO memberDTO = members.get(membersList1.getSelectedIndex());
		if (!updateName.getText().trim().isEmpty()) {
			memberDTO.setName(updateName.getText().trim());
		}
		if (!updateSurname.getText().trim().isEmpty()) {
			memberDTO.setSurname(updateSurname.getText().trim());
		}
		if (!updateBirthday.getText().trim().isEmpty()) {
			memberDTO.setBirthday(updateBirthday.getText().trim());
		}
		if (!updatePoints.getText().trim().isEmpty()) {
			memberDTO.setPoints(Integer.parseInt(updatePoints.getText().trim()));
		}
		controller.updateMember(memberDTO);
		members.set(membersList1.getSelectedIndex(), memberDTO);
		updateLists(members);
		cleanUpdateDetails();
		cleanSearchDetails();
		logger.info(messages.getString("memberUpdated"));
	}
	
	/**
	 * Deletes Members from DB.
	 * @param evt Action performed over Button 
	 */
	private void buttonDeleteActionPerformed(ActionEvent evt) {
		controller.deleteMember(members.get(membersList1.getSelectedIndex()));
		members.remove(membersList1.getSelectedIndex());
		updateLists(members);
		logger.info(messages.getString("memberDeleted"));
	}

	/**
	 * Updates Lists of Members.
	 * @param members Data Container.
	 */
	private void updateLists(List<MemberDTO> members) {
		membersList.clear();
		for (int i = 0; i < members.size(); i++) {
			MemberDTO member = (MemberDTO) members.get(i);
			membersList.addElement(member.getName() + " " + member.getSurname() + " " + messages.getString("birthday") + " : " + member.getBirthday());
		}
		membersList1.setSelectedIndex(0);
	}

	/**
	 * Cleans search text fields
	 */
	private void cleanSearchDetails() {
		email.setText("");
		name.setText("");
		surname.setText("");
	}
	
	/**
	 * Cleans update text fields
	 */
	private void cleanUpdateDetails() {
		updateName.setText("");
		updateSurname.setText("");
		updateBirthday.setText("");
		updatePoints.setText("");
	}
	
	/** 
	 * Exit the Application 
	 * @param evt Window event performed over Button
	 */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}
	
}
