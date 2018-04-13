package es.deusto.bspq.cinema.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertFilmWindow extends JFrame {

	private JFrame frmInsertFilm;
	private JTextField textFieldTitle;
	private JTextField txtTitle;
	private JTextField txtDirector;
	private JTextField txtRating;
	private JTextField textFieldDirector;
	private JTextField txtDuration;
	private JTextField txtCountry;
	private JTextField textFieldCountry;
	private JSpinner spinnerDuration;
	private JComboBox comboBoxRating;
	private JPanel panelFormulary;
	private GridBagLayout gbl_panelFormulary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertFilmWindow window = new InsertFilmWindow();
					window.centreWindow();
					window.frmInsertFilm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertFilmWindow() {
		setResizable(false);
		initialize();
		centreWindow();
		frmInsertFilm.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertFilm = new JFrame();
		frmInsertFilm.setType(Type.UTILITY);
		frmInsertFilm.setTitle("Insert Film");
		frmInsertFilm.setBounds(100, 100, 450, 300);
		frmInsertFilm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panelFormulary = new JPanel();
		panelFormulary.setBorder(new TitledBorder(null, "Insert Film", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmInsertFilm.getContentPane().add(panelFormulary, BorderLayout.CENTER);
		gbl_panelFormulary = new GridBagLayout();
		gbl_panelFormulary.columnWidths = new int[]{0, 0, 0};
		gbl_panelFormulary.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelFormulary.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelFormulary.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFormulary.setLayout(gbl_panelFormulary);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setText("Title");
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.gridx = 0;
		gbc_txtTitle.gridy = 0;
		panelFormulary.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		textFieldTitle = new JTextField();
		GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
		gbc_textFieldTitle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitle.gridx = 1;
		gbc_textFieldTitle.gridy = 0;
		panelFormulary.add(textFieldTitle, gbc_textFieldTitle);
		textFieldTitle.setColumns(10);
		
		txtDirector = new JTextField();
		txtDirector.setEditable(false);
		txtDirector.setText("Director");
		GridBagConstraints gbc_txtDirector = new GridBagConstraints();
		gbc_txtDirector.insets = new Insets(0, 0, 5, 5);
		gbc_txtDirector.gridx = 0;
		gbc_txtDirector.gridy = 1;
		panelFormulary.add(txtDirector, gbc_txtDirector);
		txtDirector.setColumns(10);
		
		textFieldDirector = new JTextField();
		GridBagConstraints gbc_textFieldDirector = new GridBagConstraints();
		gbc_textFieldDirector.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDirector.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDirector.gridx = 1;
		gbc_textFieldDirector.gridy = 1;
		panelFormulary.add(textFieldDirector, gbc_textFieldDirector);
		textFieldDirector.setColumns(10);
		
		txtRating = new JTextField();
		txtRating.setEditable(false);
		txtRating.setText("Rating");
		GridBagConstraints gbc_txtRating = new GridBagConstraints();
		gbc_txtRating.insets = new Insets(0, 0, 5, 5);
		gbc_txtRating.gridx = 0;
		gbc_txtRating.gridy = 2;
		panelFormulary.add(txtRating, gbc_txtRating);
		txtRating.setColumns(10);
		
		comboBoxRating = new JComboBox();
		GridBagConstraints gbc_comboBoxRating = new GridBagConstraints();
		gbc_comboBoxRating.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRating.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRating.gridx = 1;
		gbc_comboBoxRating.gridy = 2;
		panelFormulary.add(comboBoxRating, gbc_comboBoxRating);
		
		txtDuration = new JTextField();
		txtDuration.setEditable(false);
		txtDuration.setText("Duration (mins)");
		GridBagConstraints gbc_txtDuration = new GridBagConstraints();
		gbc_txtDuration.insets = new Insets(0, 0, 5, 5);
		gbc_txtDuration.gridx = 0;
		gbc_txtDuration.gridy = 3;
		panelFormulary.add(txtDuration, gbc_txtDuration);
		txtDuration.setColumns(10);
		
		spinnerDuration = new JSpinner();
		GridBagConstraints gbc_spinnerDuration = new GridBagConstraints();
		gbc_spinnerDuration.ipadx = 12;
		gbc_spinnerDuration.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerDuration.gridx = 1;
		gbc_spinnerDuration.gridy = 3;
		panelFormulary.add(spinnerDuration, gbc_spinnerDuration);
		
		txtCountry = new JTextField();
		txtCountry.setEditable(false);
		txtCountry.setText("Country");
		GridBagConstraints gbc_txtCountry = new GridBagConstraints();
		gbc_txtCountry.insets = new Insets(0, 0, 0, 5);
		gbc_txtCountry.gridx = 0;
		gbc_txtCountry.gridy = 4;
		panelFormulary.add(txtCountry, gbc_txtCountry);
		txtCountry.setColumns(10);
		
		textFieldCountry = new JTextField();
		GridBagConstraints gbc_textFieldCountry = new GridBagConstraints();
		gbc_textFieldCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCountry.gridx = 1;
		gbc_textFieldCountry.gridy = 4;
		panelFormulary.add(textFieldCountry, gbc_textFieldCountry);
		textFieldCountry.setColumns(10);
		
		JPanel panelButton = new JPanel();
		frmInsertFilm.getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		JButton btnInsertFilm = new JButton("Insert Session");
		btnInsertFilm.setEnabled(false);
		panelButton.add(btnInsertFilm);
	}
	
	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}

}
