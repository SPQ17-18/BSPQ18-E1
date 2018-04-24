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
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField email;
	private JList<String> membersList1;
	private JTextField name;
	private JTextField surname;
	private JPanel panelButtons;
	private JPanel panelMembers;
	private JPanel panelControlM;
	private JPanel panelListMembers;
	private JPanel panelEmail;
	private JPanel panelName;
	private JPanel panelSurname;
	private JScrollPane scrollMembers;
	private JTabbedPane tabsTable;
	
	private List<MemberDTO> members;
	private DefaultListModel<String> membersList;

	public MMWindow(CMController controller, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		initComponents();
		membersList = new DefaultListModel<String>();
		membersList1.setModel(membersList);
	}

	private void initComponents() {
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
		panelButtons = new JPanel();
		buttonSearch = new JButton();
		buttonSearchAll = new JButton();

		getContentPane().setLayout(new GridLayout(1, 1));

		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});

		panelMembers.setLayout(new GridLayout(1, 2));
		panelMembers.setBorder(new TitledBorder(new EtchedBorder(), "Member Section"));

		membersList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	 
		      }
		};
		membersList1.addListSelectionListener(listSelectionListener);
		
		scrollMembers.setPreferredSize(new Dimension(100, 110));	
		scrollMembers.setViewportView(membersList1);
		
		panelListMembers.setLayout(new BorderLayout());
		panelListMembers.setBorder(new TitledBorder("Member List"));
		panelListMembers.add(scrollMembers, BorderLayout.CENTER);

		panelMembers.add(panelListMembers);
		panelControlM.setLayout(new BorderLayout());
		panelControlM.setBorder(new TitledBorder(new TitledBorder(""), "Search Settings"));
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

		panelControlM.add(tabsTable, BorderLayout.CENTER);

		buttonSearch.setText("Search");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchActionPerformed(evt);
			}
		});
		
		buttonSearchAll.setText("Search All Members");
		buttonSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchAllActionPerformed(evt);
			}
		});

		panelButtons.add(buttonSearch);
		panelButtons.add(buttonSearchAll);
		panelControlM.add(panelButtons, BorderLayout.SOUTH);
		panelMembers.add(panelControlM);
		// Add members panel
		getContentPane().add(panelMembers);

		membersList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		members = controller.getAllMembers();
		updateLists(members);
		cleanSearchDetails();
		logger.info("All members retrieved.");
	}

	/** Exit the Application */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}

	private void updateLists(List<MemberDTO> members) {
		membersList.clear();
		for (int i = 0; i < members.size(); i++) {
			MemberDTO member = (MemberDTO) members.get(i);
			membersList.addElement(member.getName() + " " + member.getSurname() + " Birthday: " + member.getBirthday());
		}
		membersList1.setSelectedIndex(0);
	}

	private void cleanSearchDetails() {
		email.setText("");
		name.setText("");
		surname.setText("");
	}
	
}
