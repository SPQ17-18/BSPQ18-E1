package es.deusto.bspq.cinema.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertSessionWindow extends JFrame {

	private JFrame frmInsertSession;
	private JTextField textFieldHour;
	private JTextField textFieldPrice;
	private JTextField textFieldDate;
	private JTextField txtDate;
	private JTextField txtHour;
	private JTextField txtPrice;
	private JTextField txtFilm;
	private JTextField txtRoom;
	private JComboBox comboBoxRoom;
	private JComboBox comboBoxFilm;
	private JButton btnInsertSession;
	private JPanel panelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertSessionWindow window = new InsertSessionWindow();
					window.frmInsertSession.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertSessionWindow() {
		setResizable(false);
		initialize();
		centreWindow();
		frmInsertSession.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertSession = new JFrame();
		frmInsertSession.setType(Type.UTILITY);
		frmInsertSession.setTitle("Insert Session");
		frmInsertSession.setBounds(100, 100, 450, 300);
		frmInsertSession.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panelFormulary = new JPanel();
		panelFormulary.setBorder(new TitledBorder(null, "Insert Session", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmInsertSession.getContentPane().add(panelFormulary, BorderLayout.CENTER);
		GridBagLayout gbl_panelFormulary = new GridBagLayout();
		gbl_panelFormulary.columnWidths = new int[]{86, 158, 0};
		gbl_panelFormulary.rowHeights = new int[]{20, 20, 20, 20, 20, 0};
		gbl_panelFormulary.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelFormulary.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFormulary.setLayout(gbl_panelFormulary);
		
		txtFilm = new JTextField();
		txtFilm.setEditable(false);
		txtFilm.setText("Film");
		GridBagConstraints gbc_txtFilm = new GridBagConstraints();
		gbc_txtFilm.anchor = GridBagConstraints.NORTH;
		gbc_txtFilm.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilm.gridx = 0;
		gbc_txtFilm.gridy = 0;
		panelFormulary.add(txtFilm, gbc_txtFilm);
		txtFilm.setColumns(10);
		
		comboBoxFilm = new JComboBox();
		GridBagConstraints gbc_comboBoxFilm = new GridBagConstraints();
		gbc_comboBoxFilm.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxFilm.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilm.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFilm.gridx = 1;
		gbc_comboBoxFilm.gridy = 0;
		panelFormulary.add(comboBoxFilm, gbc_comboBoxFilm);
		
		txtRoom = new JTextField();
		txtRoom.setEditable(false);
		txtRoom.setText("Room");
		GridBagConstraints gbc_txtRoom = new GridBagConstraints();
		gbc_txtRoom.anchor = GridBagConstraints.NORTH;
		gbc_txtRoom.insets = new Insets(0, 0, 5, 5);
		gbc_txtRoom.gridx = 0;
		gbc_txtRoom.gridy = 1;
		panelFormulary.add(txtRoom, gbc_txtRoom);
		txtRoom.setColumns(10);
		
		comboBoxRoom = new JComboBox();
		GridBagConstraints gbc_comboBoxRoom = new GridBagConstraints();
		gbc_comboBoxRoom.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxRoom.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRoom.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRoom.gridx = 1;
		gbc_comboBoxRoom.gridy = 1;
		panelFormulary.add(comboBoxRoom, gbc_comboBoxRoom);
		
		txtDate = new JTextField();
		txtDate.setText("Date");
		txtDate.setEditable(false);
		GridBagConstraints gbc_txtDate = new GridBagConstraints();
		gbc_txtDate.anchor = GridBagConstraints.NORTH;
		gbc_txtDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtDate.gridx = 0;
		gbc_txtDate.gridy = 2;
		panelFormulary.add(txtDate, gbc_txtDate);
		txtDate.setColumns(10);
		
		textFieldDate = new JTextField();
		GridBagConstraints gbc_textFieldDate = new GridBagConstraints();
		gbc_textFieldDate.anchor = GridBagConstraints.NORTH;
		gbc_textFieldDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDate.gridx = 1;
		gbc_textFieldDate.gridy = 2;
		panelFormulary.add(textFieldDate, gbc_textFieldDate);
		textFieldDate.setColumns(10);
		
		txtHour = new JTextField();
		txtHour.setEditable(false);
		txtHour.setText("Hour");
		GridBagConstraints gbc_txtHour = new GridBagConstraints();
		gbc_txtHour.anchor = GridBagConstraints.NORTH;
		gbc_txtHour.insets = new Insets(0, 0, 5, 5);
		gbc_txtHour.gridx = 0;
		gbc_txtHour.gridy = 3;
		panelFormulary.add(txtHour, gbc_txtHour);
		txtHour.setColumns(10);
		
		textFieldHour = new JTextField();
		GridBagConstraints gbc_textFieldHour = new GridBagConstraints();
		gbc_textFieldHour.anchor = GridBagConstraints.NORTH;
		gbc_textFieldHour.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHour.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHour.gridx = 1;
		gbc_textFieldHour.gridy = 3;
		panelFormulary.add(textFieldHour, gbc_textFieldHour);
		textFieldHour.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setText("Price");
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.anchor = GridBagConstraints.NORTH;
		gbc_txtPrice.insets = new Insets(0, 0, 0, 5);
		gbc_txtPrice.gridx = 0;
		gbc_txtPrice.gridy = 4;
		panelFormulary.add(txtPrice, gbc_txtPrice);
		txtPrice.setColumns(10);
		
		textFieldPrice = new JTextField();
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.anchor = GridBagConstraints.NORTH;
		gbc_textFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrice.gridx = 1;
		gbc_textFieldPrice.gridy = 4;
		panelFormulary.add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(10);
		
		panelButton = new JPanel();
		frmInsertSession.getContentPane().add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnInsertSession = new JButton("Insert Session");
		btnInsertSession.setEnabled(false);
		panelButton.add(btnInsertSession);
		btnInsertSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

	public void centreWindow() {
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = getBounds();
		setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
	
}
