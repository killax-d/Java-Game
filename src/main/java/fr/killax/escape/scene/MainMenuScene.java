package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.app.components.ImageButtonComponent;
import fr.killax.escape.assets.AnimatedImage;
import fr.killax.escape.assets.Assets;

public class MainMenuScene extends AbstractScene implements KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final AnimatedImage BACKGROUND = Assets.getImage("textures/ui/loadingBackground.jpg");
	private static final ImageButtonComponent BUTTON_START = new ImageButtonComponent(Assets.getImage("textures/ui/play.png"), Assets.getImage("textures/ui/play_hover.png"), (int) (Config.WINDOW_SIZE.getWidth()/2 - 150), (int) Config.WINDOW_SIZE.getHeight()/3, 300, 75);
	private static final ImageButtonComponent BUTTON_QUIT = new ImageButtonComponent(Assets.getImage("textures/ui/quit.png"), Assets.getImage("textures/ui/quit_hover.png"), (int) (Config.WINDOW_SIZE.getWidth()/2 - 150), (int) Config.WINDOW_SIZE.getHeight()/3+80, 300, 75);
	
	public MainMenuScene() {}
	
	@Override
	public void update(double delta) {}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int) (Config.WINDOW_SIZE.getWidth()), (int) (Config.WINDOW_SIZE.getHeight()));
		
		BACKGROUND.draw(0, 0, (int) Config.WINDOW_SIZE.getWidth(), (int) Config.WINDOW_SIZE.getHeight(), g);
		BUTTON_START.draw(g);
		BUTTON_QUIT.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		BUTTON_START.move(e);
		BUTTON_QUIT.move(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		BUTTON_START.click(e);
		BUTTON_QUIT.click(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		//if (BUTTON_START.release(e))
		if (BUTTON_QUIT.release(e))
			App.instance().quit();
	}

}
