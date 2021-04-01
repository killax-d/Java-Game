package fr.killax.escape.app;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		this.setSize(Config.WINDOW_SIZE);
		this.setTitle(Config.WINDOW_TITLE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		
		this.addWindowListener(this);
	}

	// Window in front
	public void windowActivated(WindowEvent e) {
		// TODO: Unpause the game
	}

	// Window in back
	public void windowDeactivated(WindowEvent e) {
		// TODO: Pause the game
	}

	public void windowClosed(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		App.instance().quit();
	}

	public void windowDeiconified(WindowEvent e) {
		App.instance().pauseGraphics(false);
	}

	public void windowIconified(WindowEvent e) {
		App.instance().pauseGraphics(true);
	}

	public void windowOpened(WindowEvent e) {}

	
	
}
