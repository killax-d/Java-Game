package fr.killax.escape.scene;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import fr.killax.escape.app.Config;
import fr.killax.escape.assets.AnimatedImage;
import fr.killax.escape.assets.Assets;
import fr.killax.escape.assets.animations.ZoomAnimation;
import fr.killax.escape.assets.animations.texts.AnimatedText;

public class SplashScene extends AbstractScene {

	private static final long serialVersionUID = 1L;
	
	private final AnimatedImage LOGO = Assets.getImage("textures/ui/logo.png").setAnimation(new ZoomAnimation(3.0f, 0.05f, 0.5f));
	private final AnimatedText KILLAX = new AnimatedText("KILLAX", new Font("Arial", Font.BOLD, 32), new ZoomAnimation(0.25f, 0.05f, 1.0f), true, 250);
	private final AnimatedText KILLAX2 = new AnimatedText("KILLAX", new Font("Arial", Font.BOLD, 32), new ZoomAnimation(0.25f, 0.05f, 1.0f));
	
	public SplashScene() {
		LOGO.getAnimation().play();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				KILLAX.play();
				KILLAX2.play();
			}
			
		}, 500);
	}
	
	@Override
	public void update(double delta) {
		LOGO.update(delta);
		KILLAX.update(delta);
		KILLAX2.update(delta);
	}

	@Override
	public void draw(Graphics g) {
		Dimension size = LOGO.getAnimation().getAnimationBounds();
		LOGO.draw((int) (Config.WINDOW_SIZE.getWidth()/2 - size.getWidth()/2), (int) (Config.WINDOW_SIZE.getHeight()/3 - size.getHeight()/2), g);
		KILLAX.draw((int) (Config.WINDOW_SIZE.getWidth()/2 - KILLAX.getWidth()/2), (int) (Config.WINDOW_SIZE.getHeight()/3*2 - KILLAX.getHeight()/2), g);
		KILLAX2.draw((int) (Config.WINDOW_SIZE.getWidth()/2 - KILLAX2.getWidth()/2), (int) (Config.WINDOW_SIZE.getHeight()/3*2 - KILLAX2.getHeight()/2 + 40), g);
	}

}
