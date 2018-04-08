package es.deusto.bspq.cinema.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import es.deusto.bspq.cinema.client.controller.CMController;

public class CMAWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private CMController controller;
	
	public CMAWindow(CMController controller) {
		this.controller = controller;
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
	private void exitForm(java.awt.event.WindowEvent evt) {
		controller.exit();
	}

	public static void main(String[] args) throws RemoteException {
		final CMAWindow window = new CMAWindow(new CMController(args));
		window.centreWindow();
		window.setVisible(true);
	}
	
}
