package es.deusto.bspq.cinema.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CMWindow extends JFrame {
	
	final static Logger logger = Logger.getLogger(CMWindow.class);

	private static final long serialVersionUID = 1L;
	
	// App controller
	private CMController controller;
	
	// Login email
	private String loginEmail;

	private JButton buttonBuy;
	private JButton buttonSearch;
	private JButton buttonSearchAll;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField film;
	private JList<String> sessionsList1;
	private JList<String> seatList1;
	private JTextField hour;
	private JTextField date;
	private JPanel panelButton;
	private JPanel panelButtonsP1;
	private JPanel panelSessions;
	private JPanel panelControlP;
	private JPanel panelControlM;
	private JPanel panelListSessions;
	private JPanel panelSeats;
	private JPanel panelTickets;
	private JPanel panelDate;
	private JPanel panelFilm;
	private JPanel panelHour;
	private JPanel panelUserTicketFields;
	private JScrollPane scrollSessions;
	private JScrollPane scrollSeats;
	private JTabbedPane tabsTable;
	private JButton buttonAddSeat;
	
	private ArrayList<String> seatNumberList = new ArrayList<String>();
	private DefaultListModel<String> seatList;
	
	private List<SessionDTO> sessions;
	private DefaultListModel<String> sessionsList;
	
	private JPanel panelOptions;
	private JButton btnManageMembership;
	private JLabel lblOptions;

	public CMWindow(CMController controller, String loginEmail) {
		this.controller = controller;
		this.loginEmail = loginEmail;
		initComponents();
		sessionsList = new DefaultListModel<String>();
		seatList = new DefaultListModel<String>();
		sessionsList1.setModel(sessionsList);
		seatList1.setModel(seatList);
	}

	private void initComponents() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		    	  try {
		    		  seatNumberList.clear();
		    		  seatList.clear();
		    		  for (int i = 0; i < sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().size(); i++) {
		    			  seatList.addElement(sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().get(i));
		    		  }
		    		  seatList1.setSelectedIndex(0);
		    	  } catch (Exception e) {
		    		  logger.error("Error updating seat list.");
		    	  }
		      }
		};
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		panelTickets = new JPanel();
		panelSeats = new JPanel();
		scrollSeats = new JScrollPane();
		seatList1 = new JList<String>();
		panelControlM = new JPanel();
		panelUserTicketFields = new JPanel();
		panelButton = new JPanel();
		buttonBuy = new JButton();
		buttonAddSeat = new JButton();

		panelTickets.setLayout(new GridLayout(1, 2));
		panelTickets.setBorder(new TitledBorder(new EtchedBorder(), "Ticket Section")); 
		panelSeats.setLayout(new BorderLayout());
		panelSeats.setBorder(new TitledBorder("Seats"));
		scrollSeats.setPreferredSize(new Dimension(100, 110));
		scrollSeats.setViewportView(seatList1);
		panelSeats.add(scrollSeats, BorderLayout.CENTER);
		panelTickets.add(panelSeats);
				
		panelControlM.setBorder(new TitledBorder(new TitledBorder(""), "Buy a Ticket"));
		panelControlM.setLayout(new GridLayout(1, 2));

		panelUserTicketFields.setPreferredSize(new Dimension(250, 60));
								
		panelControlM.add(panelUserTicketFields);
										
		panelButton.setPreferredSize(new Dimension(250, 36));
		buttonBuy.setText("Buy");
		buttonBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonBuyActionPerformed(evt);
			}
		});
		
		buttonAddSeat.setText("Add Seat");
		buttonAddSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonAddSeatActionPerformed(evt);
			}
		});
		panelSessions = new JPanel();
		panelListSessions = new JPanel();
		scrollSessions = new JScrollPane();
		sessionsList1 = new JList<String>();
		panelControlP = new JPanel();
		tabsTable = new JTabbedPane();
		panelFilm = new JPanel();
		jLabel3 = new JLabel();
		film = new JTextField();
		panelHour = new JPanel();
		jLabel2 = new JLabel();
		hour = new JTextField();
		panelDate = new JPanel();
		jLabel1 = new JLabel();
		date = new JTextField();
		panelButtonsP1 = new JPanel();
		buttonSearch = new JButton();
		buttonSearchAll = new JButton();
												
		panelSessions.setLayout(new GridLayout(1, 2));
		panelSessions.setBorder(new TitledBorder(new EtchedBorder(), "Session Section"));

		sessionsList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sessionsList1.addListSelectionListener(listSelectionListener);
		
		scrollSessions.setPreferredSize(new Dimension(100, 110));	
		scrollSessions.setViewportView(sessionsList1);
		
		panelListSessions.setLayout(new BorderLayout());
		panelListSessions.setBorder(new TitledBorder("Session List"));
		panelListSessions.add(scrollSessions, BorderLayout.CENTER);
		
		panelSessions.add(panelListSessions);
		panelControlP.setLayout(new BorderLayout());
		panelControlP.setBorder(new TitledBorder(new TitledBorder(""), "Search Settings"));
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
													
		panelControlP.add(tabsTable, BorderLayout.CENTER);
															
		buttonSearch.setText("Search");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchActionPerformed(evt);
			}
		});
		
		buttonSearchAll.setText("Search All Sessions");
		buttonSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonSearchAllActionPerformed(evt);
			}
		});
																			
		panelOptions = new JPanel();
		
		btnManageMembership = new JButton("Manage Membership");
		btnManageMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MIWindow miWindow = new MIWindow(controller, loginEmail);
				miWindow.centreWindow();
				miWindow.setVisible(true);
				dispose();
			}
		});
		
		lblOptions = new JLabel("OPTIONS:");
		panelOptions.add(lblOptions);
		panelOptions.add(btnManageMembership);
	
		// Add options panel
		getContentPane().add(panelOptions, BorderLayout.NORTH);

		panelButtonsP1.add(buttonSearch);
		panelButtonsP1.add(buttonSearchAll);
		panelControlP.add(panelButtonsP1, BorderLayout.SOUTH);
		panelSessions.add(panelControlP);
		
		// Add sessions panel
		getContentPane().add(panelSessions, BorderLayout.CENTER);
		
		panelButton.add(buttonAddSeat);
		panelButton.add(buttonBuy);
		panelControlM.add(panelButton);
		panelTickets.add(panelControlM);
		
		// Add tickets panel
		getContentPane().add(panelTickets, BorderLayout.SOUTH);

		pack();
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
	private void buttonAddSeatActionPerformed(ActionEvent evt) {
		seatNumberList.add(seatList.get(seatList1.getSelectedIndex()));
		sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().remove(seatList.get(seatList1.getSelectedIndex()));
		seatList.remove(seatList1.getSelectedIndex());
		logger.info("Seat added.");
	}
	
	private void buttonBuyActionPerformed(ActionEvent evt) {
		SessionDTO session = sessions.get(sessionsList1.getSelectedIndex());
		TicketDTO ticket = new TicketDTO(loginEmail, session.getTitleFilm(),
				session.getDate(), session.getHour(), seatNumberList);
		controller.buyTicket(ticket);
		logger.info("Ticket bought with " + ticket.getListSeats().size() + " seats for film: " + ticket.getTitleFilm());
		seatNumberList.clear();
		seatList.clear();
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
		for (int i = 0; i < sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().size(); i++) {
			seatList.addElement(sessions.get(sessionsList1.getSelectedIndex()).getRemainingSeatsCode().get(i));
		}
		seatList1.setSelectedIndex(0);
	}

	private void cleanSearchDetails() {
		film.setText("");
		hour.setText("");
		date.setText("");
	}
	
}
