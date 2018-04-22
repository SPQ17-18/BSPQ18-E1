package es.deusto.bspq.cinema.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
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

	private javax.swing.JButton buttonSearch;
	private javax.swing.JButton buttonSearchAll;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField email;
	private javax.swing.JList<String> membersList1;
	private javax.swing.JTextField name;
	private javax.swing.JTextField surname;
	private javax.swing.JPanel panelButtons;
	private javax.swing.JPanel panelMembers;
	private javax.swing.JPanel panelControlM;
	private javax.swing.JPanel panelListMembers;
	private javax.swing.JPanel panelEmail;
	private javax.swing.JPanel panelName;
	private javax.swing.JPanel panelSurname;
	private javax.swing.JScrollPane scrollMembers;
	private javax.swing.JTabbedPane tabsTable;
	
	private List<MemberDTO> members;
	private javax.swing.DefaultListModel<String> membersList;

	public MMWindow(CMController controller, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		initComponents();
		membersList = new DefaultListModel<String>();
		membersList1.setModel(membersList);
	}

	private void initComponents() {
		panelMembers = new javax.swing.JPanel();
		panelListMembers = new javax.swing.JPanel();
		scrollMembers = new javax.swing.JScrollPane();
		membersList1 = new javax.swing.JList<String>();
		panelControlM = new javax.swing.JPanel();
		tabsTable = new javax.swing.JTabbedPane();
		panelEmail = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		email = new javax.swing.JTextField();
		panelName = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		name = new javax.swing.JTextField();
		panelSurname = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		surname = new javax.swing.JTextField();
		panelButtons = new javax.swing.JPanel();
		buttonSearch = new javax.swing.JButton();
		buttonSearchAll = new javax.swing.JButton();

		getContentPane().setLayout(new java.awt.GridLayout(1, 1));

		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		panelMembers.setLayout(new java.awt.GridLayout(1, 2));
		panelMembers.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Member Section"));

		membersList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	 
		      }
		};
		membersList1.addListSelectionListener(listSelectionListener);
		
		scrollMembers.setPreferredSize(new java.awt.Dimension(100, 110));	
		scrollMembers.setViewportView(membersList1);
		
		panelListMembers.setLayout(new java.awt.BorderLayout());
		panelListMembers.setBorder(new javax.swing.border.TitledBorder("Member List"));
		panelListMembers.add(scrollMembers, java.awt.BorderLayout.CENTER);

		panelMembers.add(panelListMembers);
		panelControlM.setLayout(new java.awt.BorderLayout());
		panelControlM.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.TitledBorder(""), "Search Settings"));
		jLabel3.setText("Email:");
		panelEmail.add(jLabel3);

		email.setColumns(5);
		panelEmail.add(email);

		tabsTable.addTab("Email", panelEmail);

		jLabel2.setText("Name: ");
		panelName.add(jLabel2);

		name.setColumns(5);
		panelName.add(name);

		tabsTable.addTab("Name", panelName);

		jLabel1.setText("Surname: ");
		panelSurname.add(jLabel1);

		surname.setColumns(10);
		panelSurname.add(surname);

		tabsTable.addTab("Surname", panelSurname);

		panelControlM.add(tabsTable, java.awt.BorderLayout.CENTER);

		buttonSearch.setText("Search");
		buttonSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSearchActionPerformed(evt);
			}
		});
		
		buttonSearchAll.setText("Search All Members");
		buttonSearchAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSearchAllActionPerformed(evt);
			}
		});

		panelButtons.add(buttonSearch);
		panelButtons.add(buttonSearchAll);
		panelControlM.add(panelButtons, java.awt.BorderLayout.SOUTH);
		panelMembers.add(panelControlM);
		// Add sessions panel
		getContentPane().add(panelMembers);

		membersList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		membersList1.setFocusable(false);
		membersList1.setEnabled(true);
		
		pack();
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
	private void buttonSearchActionPerformed(ActionEvent evt) {
		
	}
	
	private void buttonSearchAllActionPerformed(ActionEvent evt) {
//		members = controller.getAllMembers();
//		updateLists(members);
//		cleanSearchDetails();
//		logger.info("All members retrieved.");
	}

	/** Exit the Application */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}

	private void updateLists(List<MemberDTO> members) {
		membersList.clear();
	}

	private void cleanSearchDetails() {
		email.setText("");
		name.setText("");
		surname.setText("");
	}
	
}
