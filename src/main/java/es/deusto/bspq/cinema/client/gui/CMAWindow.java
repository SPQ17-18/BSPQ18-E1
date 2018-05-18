package es.deusto.bspq.cinema.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import es.deusto.bspq.cinema.client.controller.CMController;
import es.deusto.bspq.cinema.client.controller.RemoteExeption;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class CMAWindow extends JFrame {
	
	final static Logger logger = Logger.getLogger(CMWindow.class);

	private final int seatsPerSession = 25;
	
	// App controller
	private CMController controller;
	
	// Login user
	private String loginUser;
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelCentral = new JPanel();
	private JPanel panelInsert = new JPanel();
	private JTabbedPane tabbedPaneInsert = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelUpdate = new JPanel();
	private JTabbedPane tabbedPaneUpdate = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelDelete = new JPanel();
	private JTabbedPane tabbedPaneDelete = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelDeleteSession = new JPanel();
	private JComboBox<String> comboBoxDeleteSession = new JComboBox<String>();
	private JButton btnDeleteSession;
	private JPanel panelDeleteFilm = new JPanel();
	private JComboBox<String> comboBoxDeleteFilm = new JComboBox<String>();
	private JButton btnDeleteFilm;
	private JButton btnUpdate;
	private JButton btnInsert;
	private final JPanel panelInsertFilm = new JPanel();
	private final JTextField textFieldInsertFilmTitle = new JTextField();
	private final JTextField textFieldInsertFilmTitle_Edit = new JTextField();
	private final JTextField textFieldInsertFilmDirector = new JTextField();
	private final JTextField textFieldInsertFilmDirector_Edit = new JTextField();
	private final JTextField textFieldInsertFilmRating = new JTextField();
	private final JComboBox<String> comboBoxInsertFilmRating = new JComboBox<String>();
	private final JTextField textFieldInsertFilmDuration = new JTextField();
	private final JSpinner spinnerInsertFilmDuration = new JSpinner();
	private final JTextField textFieldInsertFilmCountry = new JTextField();
	private final JTextField textFieldInsertFilmCountry_Edit = new JTextField();
	private final JPanel panelInsertButton = new JPanel();
	private final JPanel panelInsertSession = new JPanel();
	private final JTextField textFieldUpdateSessionSession = new JTextField();
	private final JTextField textFieldInsertSessionFilm = new JTextField();
	private final JTextField textFieldInsertSessionFilm_Edit = new JTextField();
	private final JTextField textFieldInsertSessionRoom = new JTextField();
	private final JTextField textFieldInsertSessionDate = new JTextField();
	private final JTextField textFieldInsertSessionHour = new JTextField();
	private final JTextField textFieldInsertSessionPrice = new JTextField();
	private final JPanel panelUpdateSession = new JPanel();
	private final JComboBox<String> comboBoxUpdateSession_SelectSession = new JComboBox<String>();
	private final JTextField textFieldUpdateSessionFilm = new JTextField();
	private final JComboBox<String> comboBoxUpdateSession_SelectFilm = new JComboBox<String>();
	private final JTextField textFieldUpdateSessionRoom = new JTextField();
	private final JComboBox<String> comboBoxUpdateSession_SelectRoom = new JComboBox<String>();
	private final JTextField textFieldUpdateSessionDate = new JTextField();
	private final JTextField textFieldUpdateSessionDate_Edit = new JTextField();
	private final JTextField textFieldUpdateSessionHour = new JTextField();
	private final JTextField textFieldUpdateSessionHour_Edit = new JTextField();
	private final JTextField textFieldUpdateSessionPrice = new JTextField();
	private final JTextField textFieldUpdateSession_Price = new JTextField();
	private final JPanel panelUpdateFilm = new JPanel();
	private final JComboBox<String> comboBoxUpdateFilm_SelectFilm = new JComboBox<String>();
	private final JTextField textFieldUpdateFilmTitle = new JTextField();
	private final JTextField textFieldUpdateFilmTitle_Edit = new JTextField();
	private final JTextField textFieldUpdateFilmDirector = new JTextField();
	private final JTextField textFieldUpdateFilmDirector_Edit = new JTextField();
	private final JTextField textFieldUpdateFilmRating = new JTextField();
	private final JComboBox<String> comboBoxUpdateFilmRating = new JComboBox<String>();
	private final JTextField textFieldUpdateFilmDuration = new JTextField();
	private final JSpinner spinnerUpdateFilmDuration = new JSpinner();
	private final JTextField textFieldUpdateFilmCountry = new JTextField();
	private final JTextField textFieldUpdateFilmCountry_Edit = new JTextField();
	private final JPanel panelUpdateButton = new JPanel();
	private final JPanel panelInsertSessionPrice = new JPanel();
	private final JSpinner spinnerInsertFilmPrice = new JSpinner();
	private final JPanel panelInsertSessionHour = new JPanel();
	private final JSpinner spinnerInsertSessionHourHs = new JSpinner();
	private final JSpinner spinnerInsertSessionHourMins = new JSpinner();
	private final JTextField textFieldInsertSessionDate_Edit = new JTextField();
	private final JSpinner spinnerInsertSessionRoom = new JSpinner();
	
	private JPanel panelOptions = new JPanel();
	private JButton btnManageMembers;
	private JButton btnLoginWindow;
	
	private List<SessionDTO> sessionsDTO;
    private List<FilmDTO> filmsDTO;
	
	ResourceBundle messages;
	
	/**
	 * Class Constructor.
	 * @param controller Controller of the application.
 	 * @param messages Strings of certain language. 
	 * @param loginUser Email of the user logged in.
	 */
	public CMAWindow(CMController controller, ResourceBundle messages, String loginUser) {
		this.controller = controller;
		this.loginUser = loginUser;
		this.messages = messages;
		initComponents();
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
		
		setTitle("CMAWindow");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(panelOptions, BorderLayout.NORTH);
		getContentPane().add(panelCentral, BorderLayout.SOUTH);
		
		textFieldInsertSessionDate_Edit.setColumns(10);
		panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelInsert.setBorder(new TitledBorder(null, messages.getString("insert"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelInsert);
		panelCentral.setMaximumSize(new Dimension(600, 120));
		panelInsert.setLayout(new BorderLayout(0, 0));
		tabbedPaneInsert.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				enableButtonInsert();
			}
		});
		panelInsert.add(tabbedPaneInsert);
		panelInsertSession.setBorder(null);
		
		btnUpdate = new JButton(messages.getString("update"));
		btnInsert = new JButton(messages.getString("insert"));
		btnDeleteFilm = new JButton(messages.getString("delete"));
		btnDeleteSession = new JButton(messages.getString("delete"));
		
		tabbedPaneInsert.addTab(messages.getString("session"), null, panelInsertSession, null);
		GridBagLayout gbl_panelInsertSession = new GridBagLayout();
		gbl_panelInsertSession.columnWidths = new int[]{86, 158, 0};
		gbl_panelInsertSession.rowHeights = new int[]{20, 20, 20, 20, 20, 0};
		gbl_panelInsertSession.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInsertSession.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelInsertSession.setLayout(gbl_panelInsertSession);
		
		GridBagConstraints gbc_textFieldInsertSessionFilm = new GridBagConstraints();
		gbc_textFieldInsertSessionFilm.anchor = GridBagConstraints.NORTH;
		gbc_textFieldInsertSessionFilm.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertSessionFilm.gridx = 0;
		gbc_textFieldInsertSessionFilm.gridy = 0;
		textFieldInsertSessionFilm.setText(messages.getString("film"));
		textFieldInsertSessionFilm.setEditable(false);
		textFieldInsertSessionFilm.setColumns(10);
		panelInsertSession.add(textFieldInsertSessionFilm, gbc_textFieldInsertSessionFilm);
		
		GridBagConstraints gbc_comboBoxInsertSessionFilm = new GridBagConstraints();
		gbc_comboBoxInsertSessionFilm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsertSessionFilm.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxInsertSessionFilm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxInsertSessionFilm.gridx = 1;
		gbc_comboBoxInsertSessionFilm.gridy = 0;
		panelInsertSession.add(textFieldInsertSessionFilm_Edit, gbc_comboBoxInsertSessionFilm);
		
		GridBagConstraints gbc_textFieldInsertSessionRoom = new GridBagConstraints();
		gbc_textFieldInsertSessionRoom.anchor = GridBagConstraints.NORTH;
		gbc_textFieldInsertSessionRoom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertSessionRoom.gridx = 0;
		gbc_textFieldInsertSessionRoom.gridy = 1;
		textFieldInsertSessionRoom.setText(messages.getString("room"));
		textFieldInsertSessionRoom.setEditable(false);
		textFieldInsertSessionRoom.setColumns(10);
		panelInsertSession.add(textFieldInsertSessionRoom, gbc_textFieldInsertSessionRoom);
		
		GridBagConstraints gbc_spinnerInsertSessionRoom = new GridBagConstraints();
		gbc_spinnerInsertSessionRoom.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerInsertSessionRoom.gridx = 1;
		gbc_spinnerInsertSessionRoom.gridy = 1;
		spinnerInsertSessionRoom.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				enableButtonInsert();
			}
		});
		spinnerInsertSessionRoom.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		panelInsertSession.add(spinnerInsertSessionRoom, gbc_spinnerInsertSessionRoom);
		
		GridBagConstraints gbc_textFieldInsertSessionDate = new GridBagConstraints();
		gbc_textFieldInsertSessionDate.anchor = GridBagConstraints.NORTH;
		gbc_textFieldInsertSessionDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertSessionDate.gridx = 0;
		gbc_textFieldInsertSessionDate.gridy = 2;
		textFieldInsertSessionDate.setText(messages.getString("date"));
		textFieldInsertSessionDate.setEditable(false);
		textFieldInsertSessionDate.setColumns(10);
		panelInsertSession.add(textFieldInsertSessionDate, gbc_textFieldInsertSessionDate);
		
		GridBagConstraints gbc_textFieldInsertSessionDate_Edit = new GridBagConstraints();
		gbc_textFieldInsertSessionDate_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertSessionDate_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertSessionDate_Edit.gridx = 1;
		gbc_textFieldInsertSessionDate_Edit.gridy = 2;
		panelInsertSession.add(textFieldInsertSessionDate_Edit, gbc_textFieldInsertSessionDate_Edit);
		
		GridBagConstraints gbc_textFieldInsertSessionHour = new GridBagConstraints();
		gbc_textFieldInsertSessionHour.anchor = GridBagConstraints.NORTH;
		gbc_textFieldInsertSessionHour.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertSessionHour.gridx = 0;
		gbc_textFieldInsertSessionHour.gridy = 3;
		textFieldInsertSessionHour.setText(messages.getString("hour"));
		textFieldInsertSessionHour.setEditable(false);
		textFieldInsertSessionHour.setColumns(10);
		panelInsertSession.add(textFieldInsertSessionHour, gbc_textFieldInsertSessionHour);
		
		GridBagConstraints gbc_panelInsertSessionHour = new GridBagConstraints();
		gbc_panelInsertSessionHour.insets = new Insets(0, 0, 5, 0);
		gbc_panelInsertSessionHour.fill = GridBagConstraints.BOTH;
		gbc_panelInsertSessionHour.gridx = 1;
		gbc_panelInsertSessionHour.gridy = 3;
		panelInsertSession.add(panelInsertSessionHour, gbc_panelInsertSessionHour);
		spinnerInsertSessionHourHs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enableButtonInsert();
			}
		});
		spinnerInsertSessionHourHs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		
		panelInsertSessionHour.add(spinnerInsertSessionHourHs);
		spinnerInsertSessionHourMins.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enableButtonInsert();
			}
		});
		spinnerInsertSessionHourMins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		
		panelInsertSessionHour.add(spinnerInsertSessionHourMins);
		
		GridBagConstraints gbc_textFieldInsertSessionPrice = new GridBagConstraints();
		gbc_textFieldInsertSessionPrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldInsertSessionPrice.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldInsertSessionPrice.gridx = 0;
		gbc_textFieldInsertSessionPrice.gridy = 4;
		textFieldInsertSessionPrice.setText(messages.getString("price"));
		textFieldInsertSessionPrice.setEditable(false);
		textFieldInsertSessionPrice.setColumns(10);
		panelInsertSession.add(textFieldInsertSessionPrice, gbc_textFieldInsertSessionPrice);
		
		GridBagConstraints gbc_panelInsertSessionPrice = new GridBagConstraints();
		gbc_panelInsertSessionPrice.fill = GridBagConstraints.BOTH;
		gbc_panelInsertSessionPrice.gridx = 1;
		gbc_panelInsertSessionPrice.gridy = 4;
		panelInsertSession.add(panelInsertSessionPrice, gbc_panelInsertSessionPrice);
		spinnerInsertFilmPrice.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enableButtonInsert();
			}
		});
		spinnerInsertFilmPrice.setModel(new SpinnerNumberModel(new Long(1), new Long(1), new Long(99), new Long(1)));
		
		panelInsertSessionPrice.add(spinnerInsertFilmPrice);
		panelInsertFilm.setBorder(null);
		
		tabbedPaneInsert.addTab(messages.getString("film"), null, panelInsertFilm, null);
		GridBagLayout gbl_panelInsertFilm = new GridBagLayout();
		gbl_panelInsertFilm.columnWidths = new int[]{0, 0, 0};
		gbl_panelInsertFilm.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelInsertFilm.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInsertFilm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInsertFilm.setLayout(gbl_panelInsertFilm);
		
		GridBagConstraints gbc_textFieldInsertFilmTitle = new GridBagConstraints();
		gbc_textFieldInsertFilmTitle.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmTitle.gridx = 0;
		gbc_textFieldInsertFilmTitle.gridy = 0;
		textFieldInsertFilmTitle.setText(messages.getString("title"));
		textFieldInsertFilmTitle.setEditable(false);
		textFieldInsertFilmTitle.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmTitle, gbc_textFieldInsertFilmTitle);
		
		GridBagConstraints gbc_textFieldInsertFilmTitle_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmTitle_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmTitle_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertFilmTitle_Edit.gridx = 1;
		gbc_textFieldInsertFilmTitle_Edit.gridy = 0;
		textFieldInsertFilmTitle_Edit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				enableButtonInsert();
			}
		});
		textFieldInsertFilmTitle_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmTitle_Edit, gbc_textFieldInsertFilmTitle_Edit);
		
		GridBagConstraints gbc_textFieldInsertFilmDirector = new GridBagConstraints();
		gbc_textFieldInsertFilmDirector.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmDirector.gridx = 0;
		gbc_textFieldInsertFilmDirector.gridy = 1;
		textFieldInsertFilmDirector.setText(messages.getString("director"));
		textFieldInsertFilmDirector.setEditable(false);
		textFieldInsertFilmDirector.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDirector, gbc_textFieldInsertFilmDirector);
		
		GridBagConstraints gbc_textFieldInsertFilmDirector_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmDirector_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmDirector_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertFilmDirector_Edit.gridx = 1;
		gbc_textFieldInsertFilmDirector_Edit.gridy = 1;
		textFieldInsertFilmDirector_Edit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				enableButtonInsert();
			}
		});
		textFieldInsertFilmDirector_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDirector_Edit, gbc_textFieldInsertFilmDirector_Edit);
		
		GridBagConstraints gbc_textFieldInsertFilmRating = new GridBagConstraints();
		gbc_textFieldInsertFilmRating.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmRating.gridx = 0;
		gbc_textFieldInsertFilmRating.gridy = 2;
		textFieldInsertFilmRating.setText(messages.getString("rating"));
		textFieldInsertFilmRating.setEditable(false);
		textFieldInsertFilmRating.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmRating, gbc_textFieldInsertFilmRating);
		
		GridBagConstraints gbc_comboBoxInsertFilmRating = new GridBagConstraints();
		gbc_comboBoxInsertFilmRating.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsertFilmRating.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxInsertFilmRating.gridx = 1;
		gbc_comboBoxInsertFilmRating.gridy = 2;
		panelInsertFilm.add(comboBoxInsertFilmRating, gbc_comboBoxInsertFilmRating);
		
		GridBagConstraints gbc_textFieldInsertFilmDuration = new GridBagConstraints();
		gbc_textFieldInsertFilmDuration.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmDuration.gridx = 0;
		gbc_textFieldInsertFilmDuration.gridy = 3;
		textFieldInsertFilmDuration.setText(messages.getString("duration") + " (mins)");
		textFieldInsertFilmDuration.setEditable(false);
		textFieldInsertFilmDuration.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDuration, gbc_textFieldInsertFilmDuration);
		
		GridBagConstraints gbc_spinnerInsertFilmDuration = new GridBagConstraints();
		gbc_spinnerInsertFilmDuration.ipadx = 12;
		gbc_spinnerInsertFilmDuration.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerInsertFilmDuration.gridx = 1;
		gbc_spinnerInsertFilmDuration.gridy = 3;
		spinnerInsertFilmDuration.setModel(new SpinnerNumberModel(1, 1, 360, 1));
		spinnerInsertFilmDuration.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enableButtonInsert();
			}
		});
		panelInsertFilm.add(spinnerInsertFilmDuration, gbc_spinnerInsertFilmDuration);
		
		GridBagConstraints gbc_textFieldInsertFilmCountry = new GridBagConstraints();
		gbc_textFieldInsertFilmCountry.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldInsertFilmCountry.gridx = 0;
		gbc_textFieldInsertFilmCountry.gridy = 4;
		textFieldInsertFilmCountry.setText(messages.getString("country"));
		textFieldInsertFilmCountry.setEditable(false);
		textFieldInsertFilmCountry.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmCountry, gbc_textFieldInsertFilmCountry);
		
		GridBagConstraints gbc_textFieldInsertFilmCountry_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmCountry_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmCountry_Edit.gridx = 1;
		gbc_textFieldInsertFilmCountry_Edit.gridy = 4;
		textFieldInsertFilmCountry_Edit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				enableButtonInsert();
			}
		});
		textFieldInsertFilmCountry_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmCountry_Edit, gbc_textFieldInsertFilmCountry_Edit);
		
		panelInsert.add(panelInsertButton, BorderLayout.SOUTH);
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				enableButtonInsert();
			}
		});
		btnInsert.setEnabled(false);
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonInsertActionPerformed(evt);
			}
		});
		panelInsertButton.add(btnInsert);
		
		panelUpdate.setBorder(new TitledBorder(null, messages.getString("update"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelUpdate);
		panelUpdate.setLayout(new BorderLayout(0, 0));
		
		tabbedPaneUpdate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				enableButtonUpdate();
			}
		});
		panelUpdate.add(tabbedPaneUpdate);
		panelUpdateSession.setBorder(null);
		
		tabbedPaneUpdate.addTab(messages.getString("session"), null, panelUpdateSession, null);
		GridBagLayout gbl_panelUpdateSession = new GridBagLayout();
		gbl_panelUpdateSession.columnWidths = new int[]{86, 158, 0};
		gbl_panelUpdateSession.rowHeights = new int[]{0, 20, 20, 20, 20, 20, 0};
		gbl_panelUpdateSession.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelUpdateSession.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelUpdateSession.setLayout(gbl_panelUpdateSession);
		
		GridBagConstraints gbc_textFieldUpdateSessionSession = new GridBagConstraints();
		gbc_textFieldUpdateSessionSession.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionSession.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionSession.gridx = 0;
		gbc_textFieldUpdateSessionSession.gridy = 0;
		textFieldUpdateSessionSession.setText(messages.getString("session"));
		textFieldUpdateSessionSession.setEditable(false);
		textFieldUpdateSessionSession.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionSession, gbc_textFieldUpdateSessionSession);
		
		GridBagConstraints gbc_comboBoxUpdateSession_SelectSession = new GridBagConstraints();
		gbc_comboBoxUpdateSession_SelectSession.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateSession_SelectSession.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateSession_SelectSession.gridx = 1;
		gbc_comboBoxUpdateSession_SelectSession.gridy = 0;
		panelUpdateSession.add(comboBoxUpdateSession_SelectSession, gbc_comboBoxUpdateSession_SelectSession);
		
		GridBagConstraints gbc_textFieldUpdateSessionFilm = new GridBagConstraints();
		gbc_textFieldUpdateSessionFilm.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionFilm.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionFilm.gridx = 0;
		gbc_textFieldUpdateSessionFilm.gridy = 1;
		textFieldUpdateSessionFilm.setText(messages.getString("film"));
		textFieldUpdateSessionFilm.setEditable(false);
		textFieldUpdateSessionFilm.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionFilm, gbc_textFieldUpdateSessionFilm);
		
		GridBagConstraints gbc_comboBoxUpdateSessionFilm = new GridBagConstraints();
		gbc_comboBoxUpdateSessionFilm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateSessionFilm.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxUpdateSessionFilm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateSessionFilm.gridx = 1;
		gbc_comboBoxUpdateSessionFilm.gridy = 1;
		panelUpdateSession.add(comboBoxUpdateSession_SelectFilm, gbc_comboBoxUpdateSessionFilm);
		
		GridBagConstraints gbc_textFieldUpdateSessionRoom = new GridBagConstraints();
		gbc_textFieldUpdateSessionRoom.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionRoom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionRoom.gridx = 0;
		gbc_textFieldUpdateSessionRoom.gridy = 2;
		textFieldUpdateSessionRoom.setText(messages.getString("room"));
		textFieldUpdateSessionRoom.setEditable(false);
		textFieldUpdateSessionRoom.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionRoom, gbc_textFieldUpdateSessionRoom);
		
		GridBagConstraints gbc_comboBoxUpdateSessionRoom = new GridBagConstraints();
		gbc_comboBoxUpdateSessionRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateSessionRoom.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxUpdateSessionRoom.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateSessionRoom.gridx = 1;
		gbc_comboBoxUpdateSessionRoom.gridy = 2;
		panelUpdateSession.add(comboBoxUpdateSession_SelectRoom, gbc_comboBoxUpdateSessionRoom);
		
		GridBagConstraints gbc_textFieldUpdateSessionDate = new GridBagConstraints();
		gbc_textFieldUpdateSessionDate.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionDate.gridx = 0;
		gbc_textFieldUpdateSessionDate.gridy = 3;
		textFieldUpdateSessionDate.setText(messages.getString("date"));
		textFieldUpdateSessionDate.setEditable(false);
		textFieldUpdateSessionDate.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionDate, gbc_textFieldUpdateSessionDate);
		
		GridBagConstraints gbc_textFieldUpdateSessionDate_Edit = new GridBagConstraints();
		gbc_textFieldUpdateSessionDate_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateSessionDate_Edit.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionDate_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUpdateSessionDate_Edit.gridx = 1;
		gbc_textFieldUpdateSessionDate_Edit.gridy = 3;
		textFieldUpdateSessionDate_Edit.setColumns(10);
		textFieldUpdateSessionDate_Edit.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyPressed(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyReleased(KeyEvent e) {
				enableButtonUpdate();
			}
		});
		panelUpdateSession.add(textFieldUpdateSessionDate_Edit, gbc_textFieldUpdateSessionDate_Edit);
		
		GridBagConstraints gbc_textFieldUpdateSessionHour = new GridBagConstraints();
		gbc_textFieldUpdateSessionHour.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionHour.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionHour.gridx = 0;
		gbc_textFieldUpdateSessionHour.gridy = 4;
		textFieldUpdateSessionHour.setText(messages.getString("hour"));
		textFieldUpdateSessionHour.setEditable(false);
		textFieldUpdateSessionHour.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionHour, gbc_textFieldUpdateSessionHour);
		
		GridBagConstraints gbc_textFieldUpdateSessionHour_Edit = new GridBagConstraints();
		gbc_textFieldUpdateSessionHour_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateSessionHour_Edit.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionHour_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUpdateSessionHour_Edit.gridx = 1;
		gbc_textFieldUpdateSessionHour_Edit.gridy = 4;
		textFieldUpdateSessionHour_Edit.setColumns(10);
		textFieldUpdateSessionHour_Edit.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyPressed(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyReleased(KeyEvent e) {
				enableButtonUpdate();
			}
		});
		panelUpdateSession.add(textFieldUpdateSessionHour_Edit, gbc_textFieldUpdateSessionHour_Edit);
		
		GridBagConstraints gbc_textFieldUpdateSessionPrice = new GridBagConstraints();
		gbc_textFieldUpdateSessionPrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionPrice.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldUpdateSessionPrice.gridx = 0;
		gbc_textFieldUpdateSessionPrice.gridy = 5;
		textFieldUpdateSessionPrice.setText(messages.getString("price"));
		textFieldUpdateSessionPrice.setEditable(false);
		textFieldUpdateSessionPrice.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionPrice, gbc_textFieldUpdateSessionPrice);
		
		GridBagConstraints gbc_textFieldUpdateSession_Price = new GridBagConstraints();
		gbc_textFieldUpdateSession_Price.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateSession_Price.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSession_Price.gridx = 1;
		gbc_textFieldUpdateSession_Price.gridy = 5;
		textFieldUpdateSession_Price.setColumns(10);
		textFieldUpdateSession_Price.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyPressed(KeyEvent e) {
				enableButtonUpdate();
			}
			public void keyReleased(KeyEvent e) {
				enableButtonUpdate();
			}
		});
		panelUpdateSession.add(textFieldUpdateSession_Price, gbc_textFieldUpdateSession_Price);
		panelUpdateFilm.setBorder(null);
		
		tabbedPaneUpdate.addTab(messages.getString("film"), null, panelUpdateFilm, null);
		GridBagLayout gbl_panelUpdateFilm = new GridBagLayout();
		gbl_panelUpdateFilm.columnWidths = new int[]{0, 0, 0};
		gbl_panelUpdateFilm.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelUpdateFilm.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelUpdateFilm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelUpdateFilm.setLayout(gbl_panelUpdateFilm);
		
		GridBagConstraints gbc_comboBoxUpdateFilm_SelectFilm = new GridBagConstraints();
		gbc_comboBoxUpdateFilm_SelectFilm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateFilm_SelectFilm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateFilm_SelectFilm.gridx = 1;
		gbc_comboBoxUpdateFilm_SelectFilm.gridy = 0;
		panelUpdateFilm.add(comboBoxUpdateFilm_SelectFilm, gbc_comboBoxUpdateFilm_SelectFilm);
		
		GridBagConstraints gbc_textFieldUpdateFilmTitle = new GridBagConstraints();
		gbc_textFieldUpdateFilmTitle.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateFilmTitle.gridx = 0;
		gbc_textFieldUpdateFilmTitle.gridy = 1;
		textFieldUpdateFilmTitle.setText(messages.getString("title"));
		textFieldUpdateFilmTitle.setEditable(false);
		textFieldUpdateFilmTitle.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmTitle, gbc_textFieldUpdateFilmTitle);
		
		GridBagConstraints gbc_textFieldUpdateFilmTitle_Edit = new GridBagConstraints();
		gbc_textFieldUpdateFilmTitle_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateFilmTitle_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUpdateFilmTitle_Edit.gridx = 1;
		gbc_textFieldUpdateFilmTitle_Edit.gridy = 1;
		textFieldUpdateFilmTitle_Edit.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmTitle_Edit, gbc_textFieldUpdateFilmTitle_Edit);
		
		GridBagConstraints gbc_textFieldUpdateFilmDirector = new GridBagConstraints();
		gbc_textFieldUpdateFilmDirector.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateFilmDirector.gridx = 0;
		gbc_textFieldUpdateFilmDirector.gridy = 2;
		textFieldUpdateFilmDirector.setText(messages.getString("director"));
		textFieldUpdateFilmDirector.setEditable(false);
		textFieldUpdateFilmDirector.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmDirector, gbc_textFieldUpdateFilmDirector);
		
		GridBagConstraints gbc_textFieldUpdateFilmDirector_Edit = new GridBagConstraints();
		gbc_textFieldUpdateFilmDirector_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateFilmDirector_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUpdateFilmDirector_Edit.gridx = 1;
		gbc_textFieldUpdateFilmDirector_Edit.gridy = 2;
		textFieldUpdateFilmDirector_Edit.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmDirector_Edit, gbc_textFieldUpdateFilmDirector_Edit);
		
		GridBagConstraints gbc_textFieldUpdateFilmRating = new GridBagConstraints();
		gbc_textFieldUpdateFilmRating.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateFilmRating.gridx = 0;
		gbc_textFieldUpdateFilmRating.gridy = 3;
		textFieldUpdateFilmRating.setText(messages.getString("rating"));
		textFieldUpdateFilmRating.setEditable(false);
		textFieldUpdateFilmRating.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmRating, gbc_textFieldUpdateFilmRating);
		
		GridBagConstraints gbc_comboBoxUpdateFilmRating = new GridBagConstraints();
		gbc_comboBoxUpdateFilmRating.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateFilmRating.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateFilmRating.gridx = 1;
		gbc_comboBoxUpdateFilmRating.gridy = 3;
		panelUpdateFilm.add(comboBoxUpdateFilmRating, gbc_comboBoxUpdateFilmRating);
		
		GridBagConstraints gbc_textFieldUpdateFilmDuration = new GridBagConstraints();
		gbc_textFieldUpdateFilmDuration.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateFilmDuration.gridx = 0;
		gbc_textFieldUpdateFilmDuration.gridy = 4;
		textFieldUpdateFilmDuration.setText(messages.getString("duration") + " (mins)");
		textFieldUpdateFilmDuration.setEditable(false);
		textFieldUpdateFilmDuration.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmDuration, gbc_textFieldUpdateFilmDuration);
		
		GridBagConstraints gbc_spinnerUpdateFilmDuration = new GridBagConstraints();
		gbc_spinnerUpdateFilmDuration.ipadx = 12;
		gbc_spinnerUpdateFilmDuration.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerUpdateFilmDuration.gridx = 1;
		gbc_spinnerUpdateFilmDuration.gridy = 4;
		spinnerUpdateFilmDuration.setModel(new SpinnerNumberModel(1, 1, 360, 1));
		spinnerUpdateFilmDuration.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				enableButtonInsert();
			}
		});
		panelUpdateFilm.add(spinnerUpdateFilmDuration, gbc_spinnerUpdateFilmDuration);
		
		GridBagConstraints gbc_textFieldUpdateFilmCountry = new GridBagConstraints();
		gbc_textFieldUpdateFilmCountry.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldUpdateFilmCountry.gridx = 0;
		gbc_textFieldUpdateFilmCountry.gridy = 5;
		textFieldUpdateFilmCountry.setText(messages.getString("country"));
		textFieldUpdateFilmCountry.setEditable(false);
		textFieldUpdateFilmCountry.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmCountry, gbc_textFieldUpdateFilmCountry);
		
		GridBagConstraints gbc_textFieldUpdateFilmCountry_Edit = new GridBagConstraints();
		gbc_textFieldUpdateFilmCountry_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateFilmCountry_Edit.gridx = 1;
		gbc_textFieldUpdateFilmCountry_Edit.gridy = 5;
		textFieldUpdateFilmCountry_Edit.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmCountry_Edit, gbc_textFieldUpdateFilmCountry_Edit);
		
		panelUpdate.add(panelUpdateButton, BorderLayout.SOUTH);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonUpdateActionPerformed(evt);
			}
		});
		btnUpdate.setEnabled(false);
		panelUpdateButton.add(btnUpdate);
		
		panelDelete.setBorder(new TitledBorder(null, messages.getString("delete"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelDelete);
		
		panelDelete.add(tabbedPaneDelete);
		
		tabbedPaneDelete.addTab(messages.getString("session"), null, panelDeleteSession, null);
		
		panelDeleteSession.add(comboBoxDeleteSession);
		
		btnDeleteSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonDeleteSessionActionPerformed(evt);
			}
		});
		panelDeleteSession.add(btnDeleteSession);
		
		tabbedPaneDelete.addTab(messages.getString("film"), null, panelDeleteFilm, null);
		
		panelDeleteFilm.add(comboBoxDeleteFilm);
		
		btnDeleteFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonDeleteFilmActionPerformed(evt);
			}
		});
		panelDeleteFilm.add(btnDeleteFilm);
		
		btnManageMembers = new JButton(messages.getString("goToManageMemberships"));
		btnManageMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonManageMembersActionPerformed(evt);
			}
		});
		btnLoginWindow = new JButton(messages.getString("goToLoginWindow"));
		btnLoginWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonLoginWindowActionPerformed(evt);
			}
		});
		panelOptions.add(new JLabel(messages.getString("options") + ": "), BorderLayout.SOUTH);
		panelOptions.add(btnManageMembers, BorderLayout.SOUTH);
		panelOptions.add(btnLoginWindow, BorderLayout.SOUTH);
		
		updateFilmComboBox();
		updateSessionFilmsComboBox();
		updateSessionSessionsComboBox();
		updateDeleteSessionComboBox();
		
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
	 * Enable Button for inserting.
	 */
	private void enableButtonInsert() {
		if (tabbedPaneInsert.getSelectedIndex() == 0) {
			if (textFieldInsertSessionDate_Edit.getText().equals("")) {
				btnInsert.setEnabled(false);
			} else {
				btnInsert.setEnabled(true);
			}
		} else if (tabbedPaneInsert.getSelectedIndex() == 1) {
			if (textFieldInsertFilmCountry_Edit.getText().equals("") || textFieldInsertFilmDirector_Edit.getText().equals("") || textFieldInsertFilmTitle_Edit.getText().equals("")) {
				btnInsert.setEnabled(false);
			} else {
				btnInsert.setEnabled(true);
			}
		}
	}
	
	/**
	 * Enable Button for updating.
	 */
	private void enableButtonUpdate() {
		if (tabbedPaneUpdate.getSelectedIndex() == 0) {
			if (textFieldUpdateSessionDate_Edit.getText().equals("") || textFieldUpdateSessionHour_Edit.getText().equals("") || textFieldUpdateSession_Price.getText().equals("")) {
				btnUpdate.setEnabled(false);
			} else {
				btnUpdate.setEnabled(true);
			}
		} else if (tabbedPaneUpdate.getSelectedIndex() == 1) {
			if(textFieldUpdateFilmTitle_Edit.getText().equals("") || textFieldUpdateFilmDirector_Edit.getText().equals("") || textFieldUpdateFilmCountry_Edit.getText().equals("")) {
				btnUpdate.setEnabled(false);
			} else {
				btnUpdate.setEnabled(true);
			}
		}
	}
	
	/**
	 * Update Session in Films ComboBox
	 */
	private void updateSessionFilmsComboBox() {
		comboBoxUpdateSession_SelectFilm.removeAllItems();
		filmsDTO = new ArrayList<FilmDTO>();
		filmsDTO = controller.getAllFilms();
		for (FilmDTO filmDTO: filmsDTO) {
			comboBoxUpdateSession_SelectFilm.addItem(filmDTO.getTitle());
		}
	}
	
	/**
	 * Update Session in Sessions ComboBox
	 */
	private void updateSessionSessionsComboBox() {
		comboBoxUpdateSession_SelectSession.removeAllItems();
		sessionsDTO = new ArrayList<SessionDTO>();
		sessionsDTO = controller.getAllSessions();
		HashSet<Integer> rooms = new HashSet<Integer>();
		for (SessionDTO sessionDTO: sessionsDTO) {
			comboBoxUpdateSession_SelectSession.addItem(sessionDTO.getSession());
			rooms.add(sessionDTO.getRoom());
		}
		Iterator<Integer> roomNumbers = rooms.iterator();
		while (roomNumbers.hasNext()) comboBoxUpdateSession_SelectRoom.addItem(roomNumbers.next().toString());
	}
	
	/**
	 * Update Sessions in DeleteSession ComboBox
	 */
	private void updateDeleteSessionComboBox() {
		comboBoxDeleteSession.removeAllItems();
		sessionsDTO = new ArrayList<SessionDTO>();
		sessionsDTO = controller.getAllSessions();
		for (SessionDTO sessionDTO: sessionsDTO) {
			comboBoxDeleteSession.addItem(sessionDTO.getSession());
		}	
	}
	
	/**
	 * Update Film ComboBox
	 */
	private void updateFilmComboBox() {
		comboBoxDeleteFilm.removeAllItems();
		filmsDTO = new ArrayList<FilmDTO>();
		filmsDTO = controller.getAllFilms();
		for (FilmDTO filmDTO: filmsDTO) {
			comboBoxDeleteFilm.addItem(filmDTO.getTitle());
			comboBoxUpdateFilm_SelectFilm.addItem(filmDTO.getTitle());
		}	
	}
	
	/**
	 * Opens the ManageMembers Window
	 * @param evt Action performed over Button 
	 */
	private void buttonManageMembersActionPerformed(ActionEvent evt) {
		MMWindow mmWindow = new MMWindow(controller, messages, loginUser);
		mmWindow.centreWindow();
		mmWindow.setVisible(true);
		dispose();
	}
	
	/**
	 * Opens the Login Window
	 * @param evt Action performed over Button 
	 */
	private void buttonLoginWindowActionPerformed(ActionEvent evt) {
		LoginWindow loginWindow = new LoginWindow(controller, messages);
		loginWindow.centreWindow();
		loginWindow.setVisible(true);
		dispose();
	}
	
	/**
	 * Inserts a Session/Film in the DB by Button-click
	 * @param evt Action performed over Button 
	 */
	private void buttonInsertActionPerformed(ActionEvent evt) {
		if (tabbedPaneInsert.getSelectedIndex() == 1) {
			controller.insertFilm(new FilmDTO(textFieldInsertFilmTitle_Edit.getText(), 
					textFieldInsertFilmDirector_Edit.getText(),
					Integer.parseInt((String) comboBoxInsertFilmRating.getSelectedItem()), 
					new Long((int) spinnerInsertFilmDuration.getValue()), 
					textFieldInsertFilmCountry_Edit.getText()));
			updateSessionFilmsComboBox();
			
		} else {
			controller.insertSession(new SessionDTO(textFieldInsertSessionDate_Edit.getText(),
					spinnerInsertSessionHourHs.getValue()+":"+spinnerInsertSessionHourMins.getValue(),
					(long) spinnerInsertFilmPrice.getValue(),
					(int) spinnerInsertSessionRoom.getValue(),
					seatsPerSession,
					textFieldInsertSessionFilm_Edit.getText()));
			updateSessionSessionsComboBox();
		}
	}
	
	/**
	 * Updates a Session/Film in the DB by Button-click
	 * @param evt Action performed over Button 

	 */
	private void buttonUpdateActionPerformed(ActionEvent evt) {
		if (tabbedPaneUpdate.getSelectedIndex() == 0) {
			for (SessionDTO sessionDTO: sessionsDTO) {
				if (sessionDTO.getSession().equals((String) comboBoxUpdateSession_SelectSession.getSelectedItem())) {
					sessionDTO.setTitleFilm((String) comboBoxUpdateSession_SelectFilm.getSelectedItem());
					sessionDTO.setRoom(Integer.parseInt((String) comboBoxUpdateSession_SelectRoom.getSelectedItem()));
					sessionDTO.setDate(textFieldUpdateSessionDate_Edit.getText().trim());
					sessionDTO.setHour(textFieldUpdateSessionHour_Edit.getText().trim());
					sessionDTO.setPrice(Float.parseFloat(textFieldUpdateSession_Price.getText().trim()));
					if (controller.updateSession(sessionDTO)) {
						logger.info(messages.getString("updatedSession"));
					}
				}
			}
			updateSessionSessionsComboBox();
			updateDeleteSessionComboBox();
			cleanUpdateSessionDetails();
			btnUpdate.setEnabled(false);
		} else {
			for (FilmDTO filmDTO: filmsDTO) {
				if (filmDTO.getTitle().equals((String) comboBoxUpdateFilm_SelectFilm.getSelectedItem())) {
					filmDTO.setCountry(textFieldInsertFilmCountry_Edit.getText().trim());
					filmDTO.setDirector(textFieldInsertFilmDirector_Edit.getText().trim());
					filmDTO.setDuration(new Long((int) spinnerUpdateFilmDuration.getValue()));
					filmDTO.setRating(Integer.parseInt((String) comboBoxUpdateFilmRating.getSelectedItem()));
					filmDTO.setTitle((String) comboBoxUpdateFilm_SelectFilm.getSelectedItem());	
					if (controller.updateFilm(filmDTO)) {
						logger.info(messages.getString("updatedFilm"));
					}
				}
			updateSessionSessionsComboBox();
			updateDeleteSessionComboBox();
			cleanUpdateFilmDetails();
			btnUpdate.setEnabled(false);
			}
		}
	}
	
	/**
	 * Deletes a Session in the DB by Button-click
	 * @param evt Action performed over Button 
	 */
	private void buttonDeleteSessionActionPerformed(ActionEvent evt) {
		if (tabbedPaneUpdate.getSelectedIndex() == 0) {
			for (SessionDTO sessionDTO: sessionsDTO) {
				if (sessionDTO.getSession().equals((String) comboBoxDeleteSession.getSelectedItem())) {
					if (controller.deleteSession(sessionDTO)) {
						logger.info(messages.getString("deletedSession"));
					}
				}
			}
			updateDeleteSessionComboBox();
			updateSessionSessionsComboBox();
		}
		else {
			updateDeleteSessionComboBox();
			updateSessionSessionsComboBox();
		}
	}
	
	/**
	 * Deletes a Film in the DB by Button-click
	 * @param evt Action performed over Button 
	 */
	private void buttonDeleteFilmActionPerformed(ActionEvent evt) {
		String filmTitle = String.valueOf(comboBoxDeleteFilm.getSelectedItem());
		controller.deleteFilm(filmTitle);
		logger.info(messages.getString("deletedFilm"));
		updateFilmComboBox();
		updateSessionFilmsComboBox();
	}
	
	/**
	 * Cleans the user input in UpdateSession-panel.
	 */
	private void cleanUpdateSessionDetails() {
		comboBoxUpdateSession_SelectSession.setSelectedIndex(0);
		comboBoxUpdateSession_SelectFilm.setSelectedIndex(0);
		comboBoxUpdateSession_SelectRoom.setSelectedIndex(0);
		textFieldUpdateSessionDate_Edit.setText("");
		textFieldUpdateSessionHour_Edit.setText("");
		textFieldUpdateSession_Price.setText("");
	}
	
	/**
	 * Cleans the user input in UpdateFilm-panel.
	 */
	private void cleanUpdateFilmDetails() {
		comboBoxUpdateFilm_SelectFilm.setSelectedIndex(0);
		comboBoxUpdateFilmRating.setSelectedIndex(0);
		textFieldUpdateFilmCountry_Edit.setText("");
		textFieldUpdateFilmDirector_Edit.setText("");
		textFieldUpdateFilmTitle_Edit.setText("");
	}
	
	/**
	 *  Exit the Application 
	 * @param evt Window event performed over Button 
	 */
	private void exitForm(WindowEvent evt) {
		controller.exit();
	}

}
