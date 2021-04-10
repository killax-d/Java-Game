package fr.killax.escape.scene.transition;

import java.awt.Color;
import java.awt.Graphics;

import fr.killax.escape.app.Config;

public class FadeInTransition extends AbstractTransition {

	private float alpha;
	private final static float SPEED = 0.025f;
	
	public FadeInTransition() {
		super();
		this.alpha = 1;
		this.ended = false;
	}
	
	public void update(double delta) {
		if (this.ended) return;
		if (!this.ended) this.alpha -= SPEED * delta;
		if (this.alpha < 0.0f) {
			this.alpha = 0.0f;
			this.ended = true;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, this.alpha >= 1.0f ? 1.0f : this.alpha));
		g.fillRect(0, 0, (int) Config.WINDOW_SIZE.getWidth()-1, (int) Config.WINDOW_SIZE.getHeight()-1);
	}
	
}
