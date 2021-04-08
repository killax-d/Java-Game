package fr.killax.escape.scene;

import java.awt.Dimension;
import java.awt.Graphics;

import fr.killax.escape.app.Config;
import fr.killax.escape.assets.AnimatedImage;
import fr.killax.escape.assets.Assets;
import fr.killax.escape.assets.animations.ZoomAnimation;

public class SplashScene extends AbstractScene {

	private static final long serialVersionUID = 1L;
	
	private final AnimatedImage LOGO = Assets.getImage("textures/ui/logo.png");
	
	public SplashScene() {
		LOGO.setAnimation(new ZoomAnimation(3.0f, 0.05f, 0.5f));
		LOGO.getAnimation().play();
	}
	
	@Override
	public void update(double delta) {
		LOGO.update(delta);
	}

	@Override
	public void draw(Graphics g) {
		Dimension size = LOGO.getAnimation().getAnimationBounds();
		LOGO.draw((int) (Config.WINDOW_SIZE.getWidth()/2 - size.getWidth()/2), (int) (Config.WINDOW_SIZE.getHeight()/2 - size.getHeight()/2), g);
	}

}
