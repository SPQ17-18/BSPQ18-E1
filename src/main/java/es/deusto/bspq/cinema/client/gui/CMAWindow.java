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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

public class CMAWindow extends JFrame {

	private String[] foo = {"Item 1", "Item 2", "Item 3", "Item 4"};
	
	private InsertSessionWindow insertSessionWindow;
	private InsertFilmWindow insertFilmWindow;
	private UpdateSessionWindow updateSessionWindow;
	private UpdateFilmWindow updateFilmWindow;
	
	private static final long serialVersionUID = 1L;
	private CMController controller;
	private JPanel panelCentral = new JPanel();
	private JPanel panelInsert = new JPanel();
	private JTabbedPane tabbedPaneInsert = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelInsertSession = new JPanel();
	private JButton btnInsertSession = new JButton("Insert");
	private JPanel panelInsertFilm = new JPanel();
	private JButton btnInsertFilm = new JButton("Insert");
	private JPanel panelUpdate = new JPanel();
	private JTabbedPane tabbedPaneUpdate = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelUpdateSession = new JPanel();
	private JComboBox comboBoxUpdateSession = new JComboBox(foo);
	private JButton btnUpdateSession = new JButton("Update");
	private JPanel panelUpdateFilm = new JPanel();
	private JComboBox comboBoxUpdateFilm = new JComboBox(foo);
	private JButton btnUpdateFilm = new JButton("Update");
	private JPanel panelDelete = new JPanel();
	private JTabbedPane tabbedPaneDelete = new JTabbedPane(JTabbedPane.TOP);
	private JPanel panelDeleteSession = new JPanel();
	private JComboBox comboBoxDeleteSession = new JComboBox(foo);
	private JButton btnDeleteSession = new JButton("Delete");
	private JPanel panelDeleteFilm = new JPanel();
	private JComboBox comboBoxDeleteFilm = new JComboBox(foo);
	private JButton btnDeleteFilm = new JButton("Delete");
	
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
		
		panelInsert.add(tabbedPaneInsert);
		
		tabbedPaneInsert.addTab("Session", null, panelInsertSession, null);
		btnInsertSession.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Go to InsertSessionWindow
				insertSessionWindow = new InsertSessionWindow();
			}
		});
		
		panelInsertSession.add(btnInsertSession);
		panelInsertSession.setPreferredSize(new java.awt.Dimension(280, 100));
		
		tabbedPaneInsert.addTab("Film", null, panelInsertFilm, null);
		btnInsertFilm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Go to InsertFilmWindow
				insertFilmWindow = new InsertFilmWindow();
			}
		});
		
		panelInsertFilm.add(btnInsertFilm);
		
		panelUpdate.setBorder(new TitledBorder(null, "Update", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelUpdate);
		
		panelUpdate.add(tabbedPaneUpdate);
		
		tabbedPaneUpdate.addTab("Session", null, panelUpdateSession, null);
		
		panelUpdateSession.add(comboBoxUpdateSession);
		btnUpdateSession.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Go to UpdateSessionWindow
				updateSessionWindow = new UpdateSessionWindow();
			}
		});
		
		panelUpdateSession.add(btnUpdateSession);
		panelUpdateSession.setPreferredSize(new java.awt.Dimension(280, 100));
		
		tabbedPaneUpdate.addTab("Film", null, panelUpdateFilm, null);
		
		panelUpdateFilm.add(comboBoxUpdateFilm);
		btnUpdateFilm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO Go to UpdateFilmWindow
				updateFilmWindow = new UpdateFilmWindow();
			}
		});
		
		panelUpdateFilm.add(btnUpdateFilm);
		
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

	// Exit the Application 
	private void exitForm(java.awt.event.WindowEvent evt) {
		controller.exit();
	}

	public static void main(String[] args) throws RemoteException {
		final CMAWindow window = new CMAWindow(new CMController(args));
		window.centreWindow();
		window.setVisible(true);
	}
	
	
	
}
