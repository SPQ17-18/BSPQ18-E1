package es.deusto.bspq.cinema.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import es.deusto.bspq.cinema.client.controller.CMController;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;

public class CMAWindow extends JFrame {

	private String[] foo = {"Item 1", "Item 2", "Item 3", "Item 4"};
	
	private static final long serialVersionUID = 1L;
	private CMController controller;
	private JPanel panelCentral = new JPanel();
	private JPanel panelInsert = new JPanel();
	private JTabbedPane tabbedPaneInsert = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelUpdate = new JPanel();
	private JTabbedPane tabbedPaneUpdate = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelDelete = new JPanel();
	private JTabbedPane tabbedPaneDelete = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelDeleteSession = new JPanel();
	private JComboBox comboBoxDeleteSession = new JComboBox(foo);
	private JButton btnDeleteSession = new JButton("Delete");
	private JPanel panelDeleteFilm = new JPanel();
	private JComboBox comboBoxDeleteFilm = new JComboBox(foo);
	private JButton btnDeleteFilm = new JButton("Delete");
	private final JPanel panelInsertFilm = new JPanel();
	private final JTextField textFieldInsertFilmTitle = new JTextField();
	private final JTextField textFieldInsertFilmTitle_Edit = new JTextField();
	private final JTextField textFieldInsertFilmDirector = new JTextField();
	private final JTextField textFieldInsertFilmDirector_Edit = new JTextField();
	private final JTextField textFieldInsertFilmRating = new JTextField();
	private final JComboBox comboBoxInsertFilmRating = new JComboBox(foo);
	private final JTextField textFieldInsertFilmDuration = new JTextField();
	private final JSpinner spinnerInsertFilmDuration = new JSpinner();
	private final JTextField textFieldInsertFilmCountry = new JTextField();
	private final JTextField textFieldInsertFilmCountry_Edit = new JTextField();
	private final JPanel panelInsertButton = new JPanel();
	private final JButton btnInsert = new JButton("Insert");
	private final JPanel panelInsertSession = new JPanel();
	private final JTextField textField_8 = new JTextField();
	private final JComboBox comboBox_1 = new JComboBox(foo);
	private final JTextField textField_9 = new JTextField();
	private final JComboBox comboBox_2 = new JComboBox(foo);
	private final JTextField textField_10 = new JTextField();
	private final JTextField textField_11 = new JTextField();
	private final JTextField textField_12 = new JTextField();
	private final JTextField textField_13 = new JTextField();
	private final JTextField textField_14 = new JTextField();
	private final JTextField textField_15 = new JTextField();
	private final JPanel panelUpdateSession = new JPanel();
	private final JComboBox comboBoxUpdateSession_SelectSession = new JComboBox(foo);
	private final JTextField textFieldUpdateSessionFilm = new JTextField();
	private final JComboBox comboBoxUpdateSessionFilm = new JComboBox(foo);
	private final JTextField textFieldUpdateSessionRoom = new JTextField();
	private final JComboBox comboBoxUpdateSessionRoom = new JComboBox(foo);
	private final JTextField textFieldUpdateSessionDate = new JTextField();
	private final JTextField textFieldUpdateSessionDate_Edit = new JTextField();
	private final JTextField textFieldUpdateSessionHour = new JTextField();
	private final JTextField textFieldUpdateSessionHour_Edit = new JTextField();
	private final JTextField textFieldUpdateSessionPrice = new JTextField();
	private final JTextField textFieldUpdateSession_Price = new JTextField();
	private final JPanel panelUpdateFilm = new JPanel();
	private final JComboBox comboBoxUpdateFilm_SelectFilm = new JComboBox(foo);
	private final JTextField textFieldUpdateFilmTitle = new JTextField();
	private final JTextField textFieldUpdateFilmTitle_Edit = new JTextField();
	private final JTextField textFieldUpdateFilmDirector = new JTextField();
	private final JTextField textFieldUpdateFilmDirector_Edit = new JTextField();
	private final JTextField textFieldUpdateFilmRating = new JTextField();
	private final JComboBox comboBoxUpdateFilmRating = new JComboBox(foo);
	private final JTextField textFieldUpdateFilmDuration = new JTextField();
	private final JSpinner spinnerUpdateFilmDuration = new JSpinner();
	private final JTextField textFieldUpdateFilmCountry = new JTextField();
	private final JTextField textFieldUpdateFilmCountry_Edit = new JTextField();
	private final JPanel panelUpdateButton = new JPanel();
	private final JButton btnUpdate = new JButton("Update");
	
	public CMAWindow(CMController controller) {
		setResizable(false);
		setTitle("CMAWindow");
		this.controller = controller;
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(1, 0, 0, 0));
		
		panelInsert.setBorder(new TitledBorder(null, "Insert", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelInsert);
		panelCentral.setMaximumSize(new java.awt.Dimension(1200, 120));
		panelInsert.setLayout(new BorderLayout(0, 0));
		
		panelInsert.add(tabbedPaneInsert);
		panelInsertFilm.setBorder(null);
		
		tabbedPaneInsert.addTab("Film", null, panelInsertFilm, null);
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
		textFieldInsertFilmTitle.setText("Title");
		textFieldInsertFilmTitle.setEditable(false);
		textFieldInsertFilmTitle.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmTitle, gbc_textFieldInsertFilmTitle);
		
		GridBagConstraints gbc_textFieldInsertFilmTitle_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmTitle_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmTitle_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertFilmTitle_Edit.gridx = 1;
		gbc_textFieldInsertFilmTitle_Edit.gridy = 0;
		textFieldInsertFilmTitle_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmTitle_Edit, gbc_textFieldInsertFilmTitle_Edit);
		
		GridBagConstraints gbc_textFieldInsertFilmDirector = new GridBagConstraints();
		gbc_textFieldInsertFilmDirector.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmDirector.gridx = 0;
		gbc_textFieldInsertFilmDirector.gridy = 1;
		textFieldInsertFilmDirector.setText("Director");
		textFieldInsertFilmDirector.setEditable(false);
		textFieldInsertFilmDirector.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDirector, gbc_textFieldInsertFilmDirector);
		
		GridBagConstraints gbc_textFieldInsertFilmDirector_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmDirector_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmDirector_Edit.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldInsertFilmDirector_Edit.gridx = 1;
		gbc_textFieldInsertFilmDirector_Edit.gridy = 1;
		textFieldInsertFilmDirector_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDirector_Edit, gbc_textFieldInsertFilmDirector_Edit);
		
		GridBagConstraints gbc_textFieldInsertFilmRating = new GridBagConstraints();
		gbc_textFieldInsertFilmRating.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldInsertFilmRating.gridx = 0;
		gbc_textFieldInsertFilmRating.gridy = 2;
		textFieldInsertFilmRating.setText("Rating");
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
		textFieldInsertFilmDuration.setText("Duration (mins)");
		textFieldInsertFilmDuration.setEditable(false);
		textFieldInsertFilmDuration.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmDuration, gbc_textFieldInsertFilmDuration);
		
		GridBagConstraints gbc_spinnerInsertFilmDuration = new GridBagConstraints();
		gbc_spinnerInsertFilmDuration.ipadx = 12;
		gbc_spinnerInsertFilmDuration.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerInsertFilmDuration.gridx = 1;
		gbc_spinnerInsertFilmDuration.gridy = 3;
		panelInsertFilm.add(spinnerInsertFilmDuration, gbc_spinnerInsertFilmDuration);
		
		GridBagConstraints gbc_textFieldInsertFilmCountry = new GridBagConstraints();
		gbc_textFieldInsertFilmCountry.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldInsertFilmCountry.gridx = 0;
		gbc_textFieldInsertFilmCountry.gridy = 4;
		textFieldInsertFilmCountry.setText("Country");
		textFieldInsertFilmCountry.setEditable(false);
		textFieldInsertFilmCountry.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmCountry, gbc_textFieldInsertFilmCountry);
		
		GridBagConstraints gbc_textFieldInsertFilmCountry_Edit = new GridBagConstraints();
		gbc_textFieldInsertFilmCountry_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldInsertFilmCountry_Edit.gridx = 1;
		gbc_textFieldInsertFilmCountry_Edit.gridy = 4;
		textFieldInsertFilmCountry_Edit.setColumns(10);
		panelInsertFilm.add(textFieldInsertFilmCountry_Edit, gbc_textFieldInsertFilmCountry_Edit);
		panelInsertSession.setBorder(null);
		
		tabbedPaneInsert.addTab("Session", null, panelInsertSession, null);
		GridBagLayout gbl_panelInsertSession = new GridBagLayout();
		gbl_panelInsertSession.columnWidths = new int[]{86, 158, 0};
		gbl_panelInsertSession.rowHeights = new int[]{20, 20, 20, 20, 20, 0};
		gbl_panelInsertSession.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInsertSession.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInsertSession.setLayout(gbl_panelInsertSession);
		
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.anchor = GridBagConstraints.NORTH;
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.gridx = 0;
		gbc_textField_8.gridy = 0;
		textField_8.setText("Film");
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		panelInsertSession.add(textField_8, gbc_textField_8);
		
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		panelInsertSession.add(comboBox_1, gbc_comboBox_1);
		
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.anchor = GridBagConstraints.NORTH;
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 0;
		gbc_textField_9.gridy = 1;
		textField_9.setText("Room");
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		panelInsertSession.add(textField_9, gbc_textField_9);
		
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 1;
		panelInsertSession.add(comboBox_2, gbc_comboBox_2);
		
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.anchor = GridBagConstraints.NORTH;
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.gridx = 0;
		gbc_textField_10.gridy = 2;
		textField_10.setText("Date");
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		panelInsertSession.add(textField_10, gbc_textField_10);
		
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11.anchor = GridBagConstraints.NORTH;
		gbc_textField_11.insets = new Insets(0, 0, 5, 0);
		gbc_textField_11.gridx = 1;
		gbc_textField_11.gridy = 2;
		textField_11.setColumns(10);
		panelInsertSession.add(textField_11, gbc_textField_11);
		
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.anchor = GridBagConstraints.NORTH;
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.gridx = 0;
		gbc_textField_12.gridy = 3;
		textField_12.setText("Hour");
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		panelInsertSession.add(textField_12, gbc_textField_12);
		
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13.anchor = GridBagConstraints.NORTH;
		gbc_textField_13.insets = new Insets(0, 0, 5, 0);
		gbc_textField_13.gridx = 1;
		gbc_textField_13.gridy = 3;
		textField_13.setColumns(10);
		panelInsertSession.add(textField_13, gbc_textField_13);
		
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.anchor = GridBagConstraints.NORTH;
		gbc_textField_14.insets = new Insets(0, 0, 0, 5);
		gbc_textField_14.gridx = 0;
		gbc_textField_14.gridy = 4;
		textField_14.setText("Price");
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		panelInsertSession.add(textField_14, gbc_textField_14);
		
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15.anchor = GridBagConstraints.NORTH;
		gbc_textField_15.gridx = 1;
		gbc_textField_15.gridy = 4;
		textField_15.setColumns(10);
		panelInsertSession.add(textField_15, gbc_textField_15);
		
		panelInsert.add(panelInsertButton, BorderLayout.SOUTH);
		
		panelInsertButton.add(btnInsert);
		
		panelUpdate.setBorder(new TitledBorder(null, "Update", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelUpdate);
		panelUpdate.setLayout(new BorderLayout(0, 0));
		
		panelUpdate.add(tabbedPaneUpdate);
		panelUpdateSession.setBorder(null);
		
		tabbedPaneUpdate.addTab("Session", null, panelUpdateSession, null);
		GridBagLayout gbl_panelUpdateSession = new GridBagLayout();
		gbl_panelUpdateSession.columnWidths = new int[]{86, 158, 0};
		gbl_panelUpdateSession.rowHeights = new int[]{0, 20, 20, 20, 20, 20, 0};
		gbl_panelUpdateSession.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelUpdateSession.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelUpdateSession.setLayout(gbl_panelUpdateSession);
		
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
		textFieldUpdateSessionFilm.setText("Film");
		textFieldUpdateSessionFilm.setEditable(false);
		textFieldUpdateSessionFilm.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionFilm, gbc_textFieldUpdateSessionFilm);
		
		GridBagConstraints gbc_comboBoxUpdateSessionFilm = new GridBagConstraints();
		gbc_comboBoxUpdateSessionFilm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateSessionFilm.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxUpdateSessionFilm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateSessionFilm.gridx = 1;
		gbc_comboBoxUpdateSessionFilm.gridy = 1;
		panelUpdateSession.add(comboBoxUpdateSessionFilm, gbc_comboBoxUpdateSessionFilm);
		
		GridBagConstraints gbc_textFieldUpdateSessionRoom = new GridBagConstraints();
		gbc_textFieldUpdateSessionRoom.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionRoom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionRoom.gridx = 0;
		gbc_textFieldUpdateSessionRoom.gridy = 2;
		textFieldUpdateSessionRoom.setText("Room");
		textFieldUpdateSessionRoom.setEditable(false);
		textFieldUpdateSessionRoom.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionRoom, gbc_textFieldUpdateSessionRoom);
		
		GridBagConstraints gbc_comboBoxUpdateSessionRoom = new GridBagConstraints();
		gbc_comboBoxUpdateSessionRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUpdateSessionRoom.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxUpdateSessionRoom.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUpdateSessionRoom.gridx = 1;
		gbc_comboBoxUpdateSessionRoom.gridy = 2;
		panelUpdateSession.add(comboBoxUpdateSessionRoom, gbc_comboBoxUpdateSessionRoom);
		
		GridBagConstraints gbc_textFieldUpdateSessionDate = new GridBagConstraints();
		gbc_textFieldUpdateSessionDate.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionDate.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionDate.gridx = 0;
		gbc_textFieldUpdateSessionDate.gridy = 3;
		textFieldUpdateSessionDate.setText("Date");
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
		panelUpdateSession.add(textFieldUpdateSessionDate_Edit, gbc_textFieldUpdateSessionDate_Edit);
		
		GridBagConstraints gbc_textFieldUpdateSessionHour = new GridBagConstraints();
		gbc_textFieldUpdateSessionHour.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionHour.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUpdateSessionHour.gridx = 0;
		gbc_textFieldUpdateSessionHour.gridy = 4;
		textFieldUpdateSessionHour.setText("Hour");
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
		panelUpdateSession.add(textFieldUpdateSessionHour_Edit, gbc_textFieldUpdateSessionHour_Edit);
		
		GridBagConstraints gbc_textFieldUpdateSessionPrice = new GridBagConstraints();
		gbc_textFieldUpdateSessionPrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSessionPrice.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldUpdateSessionPrice.gridx = 0;
		gbc_textFieldUpdateSessionPrice.gridy = 5;
		textFieldUpdateSessionPrice.setText("Price");
		textFieldUpdateSessionPrice.setEditable(false);
		textFieldUpdateSessionPrice.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSessionPrice, gbc_textFieldUpdateSessionPrice);
		
		GridBagConstraints gbc_textFieldUpdateSession_Price = new GridBagConstraints();
		gbc_textFieldUpdateSession_Price.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUpdateSession_Price.anchor = GridBagConstraints.NORTH;
		gbc_textFieldUpdateSession_Price.gridx = 1;
		gbc_textFieldUpdateSession_Price.gridy = 5;
		textFieldUpdateSession_Price.setColumns(10);
		panelUpdateSession.add(textFieldUpdateSession_Price, gbc_textFieldUpdateSession_Price);
		panelUpdateFilm.setBorder(null);
		
		tabbedPaneUpdate.addTab("Film", null, panelUpdateFilm, null);
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
		textFieldUpdateFilmTitle.setText("Title");
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
		textFieldUpdateFilmDirector.setText("Director");
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
		textFieldUpdateFilmRating.setText("Rating");
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
		textFieldUpdateFilmDuration.setText("Duration (mins)");
		textFieldUpdateFilmDuration.setEditable(false);
		textFieldUpdateFilmDuration.setColumns(10);
		panelUpdateFilm.add(textFieldUpdateFilmDuration, gbc_textFieldUpdateFilmDuration);
		
		GridBagConstraints gbc_spinnerUpdateFilmDuration = new GridBagConstraints();
		gbc_spinnerUpdateFilmDuration.ipadx = 12;
		gbc_spinnerUpdateFilmDuration.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerUpdateFilmDuration.gridx = 1;
		gbc_spinnerUpdateFilmDuration.gridy = 4;
		panelUpdateFilm.add(spinnerUpdateFilmDuration, gbc_spinnerUpdateFilmDuration);
		
		GridBagConstraints gbc_textFieldUpdateFilmCountry = new GridBagConstraints();
		gbc_textFieldUpdateFilmCountry.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldUpdateFilmCountry.gridx = 0;
		gbc_textFieldUpdateFilmCountry.gridy = 5;
		textFieldUpdateFilmCountry.setText("Country");
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
		
		panelUpdateButton.add(btnUpdate);
		
		panelDelete.setBorder(new TitledBorder(null, "Delete", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelDelete);
		
		panelDelete.add(tabbedPaneDelete);
		
		tabbedPaneDelete.addTab("Session", null, panelDeleteSession, null);
		
		panelDeleteSession.add(comboBoxDeleteSession);
		btnDeleteSession.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Delete Session
			}
		});
		
		panelDeleteSession.add(btnDeleteSession);
		panelDeleteSession.setPreferredSize(new java.awt.Dimension(280, 100));
		
		tabbedPaneDelete.addTab("Film", null, panelDeleteFilm, null);
		
		panelDeleteFilm.add(comboBoxDeleteFilm);
		btnDeleteFilm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Delete Film
			}
		});
		
		panelDeleteFilm.add(btnDeleteFilm);
		initComponents();
	}

	private void initComponents() {
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

	public static void main(String[] args) throws RemoteException {
		final CMAWindow window = new CMAWindow(new CMController(args));
		window.centreWindow();
		window.setVisible(true);
	}
	
}
