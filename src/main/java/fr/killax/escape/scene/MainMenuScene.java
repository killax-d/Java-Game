package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.app.components.ButtonComponent;
import fr.killax.escape.assets.drawable.ui.MainMenuButtonDrawable;
import fr.killax.escape.scene.transition.FadeInTransition;

public class MainMenuScene extends AbstractScene implements KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final ButtonComponent BUTTON_START = new ButtonComponent("start", 0, (int) Config.WINDOW_SIZE.getHeight()/3, (int) Config.WINDOW_SIZE.getWidth(), 50, new MainMenuButtonDrawable());
	private static final ButtonComponent BUTTON_QUIT = new ButtonComponent("quit", 0, (int) Config.WINDOW_SIZE.getHeight()/3+75, (int) Config.WINDOW_SIZE.getWidth(), 50, new MainMenuButtonDrawable());
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, (int) (Config.WINDOW_SIZE.getWidth()), (int) (Config.WINDOW_SIZE.getHeight()));
		
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
	public void mouseDragged(MouseEvent e) {
		BUTTON_START.move(e);
		BUTTON_QUIT.move(e);
	}

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
		if (BUTTON_START.release(e)) {
			App.instance().getSceneManager().setScene(new LoadingScene(), new FadeInTransition());
			
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					App.instance().getSceneManager().setScene(new MainMenuScene(), new FadeInTransition());
				}
				
			}, 2000);
		}
		if (BUTTON_QUIT.release(e))
			App.instance().getFrame().dispose();
	}

}
