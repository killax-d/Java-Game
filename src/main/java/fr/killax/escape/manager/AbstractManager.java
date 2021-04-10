package fr.killax.escape.manager;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.killax.escape.app.App;
import fr.killax.escape.overlay.EscapeMenuOverlay;
import fr.killax.escape.scene.LoadingScene;
import fr.killax.escape.scene.MainMenuScene;
import fr.killax.escape.scene.SplashScene;
import fr.killax.escape.scene.transition.FadeInTransition;

public abstract class AbstractManager<T> extends Container implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param overlay Does the Manager need to return the overlay ?
	 * @return
	 */
	public abstract T current(boolean overlay);
	public abstract void paint(Graphics g);
	public abstract void update(double delta);
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (current(true) instanceof MouseWheelListener)
			((MouseWheelListener) current(true)).mouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (current(true) instanceof MouseMotionListener)
			((MouseMotionListener) current(true)).mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (current(true) instanceof MouseMotionListener)
			((MouseMotionListener) current(true)).mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (current(true) instanceof MouseListener)
			((MouseListener) current(true)).mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (current(true) instanceof MouseListener)
			((MouseListener) current(true)).mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (current(true) instanceof MouseListener)
			((MouseListener) current(true)).mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (current(true) instanceof MouseListener)
			((MouseListener) current(true)).mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (current(true) instanceof MouseListener)
			((MouseListener) current(true)).mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		SceneManager sceneManager = App.instance().getSceneManager();
		if (e.getKeyCode() == KeyEvent.VK_S) sceneManager.pushScene(new SplashScene(), new FadeInTransition());
		if (e.getKeyCode() == KeyEvent.VK_L) sceneManager.pushScene(new LoadingScene(), new FadeInTransition());
		if (e.getKeyCode() == KeyEvent.VK_M) sceneManager.pushScene(new MainMenuScene(), new FadeInTransition());
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) sceneManager.getOverlayManager().pushOverlay(sceneManager.hasOverlay() ? null : new EscapeMenuOverlay(), null);
		if (current(true) instanceof KeyListener)
			((KeyListener) current(true)).keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (current(true) instanceof KeyListener)
			((KeyListener) current(true)).keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (current(true) instanceof KeyListener)
			((KeyListener) current(true)).keyTyped(e);
	}
	
}
