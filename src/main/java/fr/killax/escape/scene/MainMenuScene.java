package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.killax.escape.app.App;
import fr.killax.escape.app.Config;
import fr.killax.escape.scene.transition.FadeInTransition;

public class MainMenuScene extends AbstractScene implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) (Config.WINDOW_SIZE.getWidth()), (int) (Config.WINDOW_SIZE.getHeight()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			App.instance().getSceneManager().setScene(new LoadingScene(), new FadeInTransition());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
