package es.deusto.bspq.cinema.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public class CMWindow extends JFrame {
	
	final static Logger logger = Logger.getLogger(CMWindow.class);

	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;

	private javax.swing.JButton buttonBuy;
	private javax.swing.JButton buttonSearch;
	private javax.swing.JButton buttonSearchAll;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JTextField film;
	private javax.swing.JList<String> sessionsList1;
	private javax.swing.JList<String> seatList1;
	private javax.swing.JTextField hour;
	private javax.swing.JTextField date;
	private javax.swing.JPanel panelButton;
	private javax.swing.JPanel panelButtonsP1;
	private javax.swing.JPanel panelSessions;
	private javax.swing.JPanel panelControlP;
	private javax.swing.JPanel panelControlM;
	private javax.swing.JPanel panelListSessions;
	private javax.swing.JPanel panelSeats;
	private javax.swing.JPanel panelTickets;
	private javax.swing.JPanel panelDate;
	private javax.swing.JPanel panelFilm;
	private javax.swing.JPanel panelHour;
	private javax.swing.JPanel panelUserTicketFields;
	private javax.swing.JScrollPane scrollSessions;
	private javax.swing.JScrollPane scrollSeats;
	private javax.swing.JTabbedPane tabsTable;
	private javax.swing.JButton buttonAddSeat;
	
	private ArrayList<String> seatNumberList = new ArrayList<String>();
	private javax.swing.DefaultListModel<String> seatList;
	
	private List<SessionDTO> sessions;
	private javax.swing.DefaultListModel<String> sessionsList;

	public CMWindow(CMController controller) {
		this.controller = controller;
		initComponents();
		sessionsList = new DefaultListModel<String>();
		seatList = new DefaultListModel<String>();
		sessionsList1.setModel(sessionsList);
		seatList1.setModel(seatList);
	}

	private void initComponents() {
		panelSessions = new javax.swing.JPanel();
		panelListSessions = new javax.swing.JPanel();
		scrollSessions = new javax.swing.JScrollPane();
		sessionsList1 = new javax.swing.JList<String>();
		panelControlP = new javax.swing.JPanel();
		tabsTable = new javax.swing.JTabbedPane();
		panelFilm = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		film = new javax.swing.JTextField();
		panelHour = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		hour = new javax.swing.JTextField();
		panelDate = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		date = new javax.swing.JTextField();
		panelButtonsP1 = new javax.swing.JPanel();
		buttonSearch = new javax.swing.JButton();
		buttonSearchAll = new javax.swing.JButton();
		panelTickets = new javax.swing.JPanel();
		panelSeats = new javax.swing.JPanel();
		scrollSeats = new javax.swing.JScrollPane();
		seatList1 = new javax.swing.JList<String>();
		panelControlM = new javax.swing.JPanel();
		panelUserTicketFields = new javax.swing.JPanel();
		panelButton = new javax.swing.JPanel();
		buttonBuy = new javax.swing.JButton();
		buttonAddSeat = new javax.swing.JButton();

		getContentPane().setLayout(new java.awt.GridLayout(2, 1));

		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		panelSessions.setLayout(new java.awt.GridLayout(1, 2));
		panelSessions.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Session Section"));

		sessionsList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  try {
		    		  seatNumberList.clear();
		    		  seatList.clear();
		    		  for (int i = 0; i < sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().size(); i++) {
		    			  seatList.addElement(sessions.get(seatList1.getSelectedIndex()).getRemainingSeatsCode().get(i));
		    		  }
		    		  seatList1.setSelectedIndex(0);
		    	  } catch (Exception e) {
		    		  logger.error("Error updating seat list.");
		    	  }
		      }
		};
		sessionsList1.addListSelectionListener(listSelectionListener);
		
		scrollSessions.setPreferredSize(new java.awt.Dimension(100, 110));	
		scrollSessions.setViewportView(sessionsList1);
		
		panelListSessions.setLayout(new java.awt.BorderLayout());
		panelListSessions.setBorder(new javax.swing.border.TitledBorder("Session List"));
		panelListSessions.add(scrollSessions, java.awt.BorderLayout.CENTER);

		panelSessions.add(panelListSessions);
		panelControlP.setLayout(new java.awt.BorderLayout());
		panelControlP.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.TitledBorder(""), "Search Settings"));
		jLabel3.setText("Film:");
		panelFilm.add(jLabel3);

		film.setColumns(5);
		panelFilm.add(film);

		tabsTable.addTab("Name", panelFilm);

		jLabel2.setText("Hour (hh:mm): ");
		panelHour.add(jLabel2);

		hour.setColumns(5);
		panelHour.add(hour);

		tabsTable.addTab("Hour", panelHour);

		jLabel1.setText("Date (dd-mm-aaaa): ");
		panelDate.add(jLabel1);

		date.setColumns(10);
		panelDate.add(date);

		tabsTable.addTab("Date", panelDate);

		panelControlP.add(tabsTable, java.awt.BorderLayout.CENTER);

		buttonSearch.setText("Search");
		buttonSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSearchActionPerformed(evt);
			}
		});
		
		buttonSearchAll.setText("Search All Sessions");
		buttonSearchAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSearchAllActionPerformed(evt);
			}
		});

		panelButtonsP1.add(buttonSearch);
		panelButtonsP1.add(buttonSearchAll);
		panelControlP.add(panelButtonsP1, java.awt.BorderLayout.SOUTH);
		panelSessions.add(panelControlP);
		// Add sessions panel
		getContentPane().add(panelSessions);

		panelTickets.setLayout(new java.awt.GridLayout(1, 2));
		panelTickets.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EtchedBorder(), "Ticket Section")); 
		panelSeats.setLayout(new java.awt.BorderLayout());
		panelSeats.setBorder(new javax.swing.border.TitledBorder("Seats"));
		scrollSeats.setPreferredSize(new java.awt.Dimension(100, 110));
		sessionsList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		sessionsList1.setFocusable(false);
		sessionsList1.setEnabled(true);
		scrollSeats.setViewportView(seatList1);
		panelSeats.add(scrollSeats, java.awt.BorderLayout.CENTER);
		panelTickets.add(panelSeats);

		panelControlM.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.TitledBorder(""), "Buy a Ticket"));
		panelControlM.setLayout(new java.awt.GridLayout(1, 2));

		panelUserTicketFields.setPreferredSize(new java.awt.Dimension(250, 60));

		panelControlM.add(panelUserTicketFields);

		panelButton.setPreferredSize(new java.awt.Dimension(250, 36));
		buttonBuy.setText("Buy");
		buttonBuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonBuyActionPerformed(evt);
			}
		});
		
		buttonAddSeat.setText("Add Seat");
		buttonAddSeat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAddSeatActionPerformed(evt);
			}
		});
		
		panelButton.add(buttonAddSeat);
		panelButton.add(buttonBuy);
		panelControlM.add(panelButton);
		panelTickets.add(panelControlM);
		// Add tickets panel
		getContentPane().add(panelTickets);

		pack();
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
	private void buttonAddSeatActionPerformed(ActionEvent evt) {
		seatNumberList.add(sessionsList.get(sessionsList1.getSelectedIndex()));
		sessionsList.remove(sessionsList1.getSelectedIndex());
		logger.info("Seat added.");
	}
	
	private void buttonBuyActionPerformed(ActionEvent evt) {
		SessionDTO session = sessions.get(sessionsList1.getSelectedIndex());
		//TODO Email will be inserted in login features
		TicketDTO ticket = new TicketDTO("unai.bermejo@opendeusto.es", session.getTitleFilm(),
				session.getDate(), session.getHour(), seatNumberList);
		controller.buyTicket(ticket);
		seatNumberList.clear();
		seatList.clear();
		sessionsList.clear();
		logger.info("Ticket bought.");
	}

	private void buttonSearchActionPerformed(ActionEvent evt) {
		
	}
	
	private void buttonSearchAllActionPerformed(ActionEvent evt) {
		sessions = controller.getAllSessions();
		updateLists(sessions);
		cleanSearchDetails();
		logger.info("All sessions retrieved.");
	}

	/** Exit the Application */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}

	private void updateLists(List<SessionDTO> sessions) {
		sessionsList.clear();
		for (int i = 0; i < sessions.size(); i++) {
			SessionDTO session = (SessionDTO) sessions.get(i);
			sessionsList.addElement(session.getTitleFilm() + " HOUR: " + session.getHour() + " DATE: " + session.getDate());
		}
		sessionsList1.setSelectedIndex(0);
		seatList.clear();
		for (int i = 0; i < sessions.get(seatList1.getSelectedIndex()).getRemainingSeatsCode().size(); i++) {
			sessionsList.addElement(sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().get(i));
		}
		seatList1.setSelectedIndex(0);
	}

	private void cleanSearchDetails() {
		film.setText("");
		hour.setText("");
		date.setText("");
	}
	
}
