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

import fr.killax.escape.scene.AbstractScene;

public abstract class AbstractSceneManager extends Container implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private static final long serialVersionUID = 1L;
	
	public abstract AbstractScene getScene();
	public abstract void paint(Graphics g);
	public abstract void updateScene(double delta);
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (getScene() instanceof MouseWheelListener)
			((MouseWheelListener) getScene()).mouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (getScene() instanceof MouseMotionListener)
			((MouseMotionListener) getScene()).mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (getScene() instanceof MouseMotionListener)
			((MouseMotionListener) getScene()).mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getScene() instanceof MouseListener)
			((MouseListener) getScene()).mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (getScene() instanceof MouseListener)
			((MouseListener) getScene()).mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (getScene() instanceof MouseListener)
			((MouseListener) getScene()).mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (getScene() instanceof MouseListener)
			((MouseListener) getScene()).mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (getScene() instanceof MouseListener)
			((MouseListener) getScene()).mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (getScene() instanceof KeyListener)
			((KeyListener) getScene()).keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (getScene() instanceof KeyListener)
			((KeyListener) getScene()).keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (getScene() instanceof KeyListener)
			((KeyListener) getScene()).keyTyped(e);
	}
	
}
