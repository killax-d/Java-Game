package fr.killax.escape.assets.animations;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import fr.killax.escape.assets.AnimatedImage;

public class ZoomAnimation extends AbstractAnimation {
	
	private float zoom;
	private float speed;
	private float step;
	private float end;
	
	private AnimatedImage image;

	public ZoomAnimation(float zoom, float speed, float end) {
		this.zoom = zoom;
		this.speed = speed;
		this.end = end;
		reset();
	}

	public void setImage(AnimatedImage image) {
		this.image = image;
	}
	
	@Override
	public void update(double delta) {
		if (this.image == null) return;
		if (!this.running) return;
		if (this.step - (speed * delta) <= this.end) {
			this.running = false;
			this.ended = true;
			this.step = this.end;
		} else 
			this.step -= (speed * delta);
			
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		if (this.image == null) return;
		g.drawImage(image.getImage().getScaledInstance((int) (image.getImage().getWidth() * this.step), (int) (image.getImage().getHeight() * this.step), Image.SCALE_FAST), x, y, null);
	}

	@Override
	public void play() {
		this.running = true;
	}

	@Override
	public void reset() {
		this.step = zoom;
	}
	
	public Dimension getAnimationBounds() {
		return new Dimension((int) (this.image.getImage().getWidth() * this.step), (int) (this.image.getImage().getHeight() * this.step));
	}

}
