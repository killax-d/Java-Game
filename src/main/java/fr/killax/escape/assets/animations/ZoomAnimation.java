package fr.killax.escape.assets.animations;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ZoomAnimation extends AbstractAnimation {
	
	private float from;
	private float speed;
	private float step;
	private float to;

	public ZoomAnimation(float from, float speed, float to) {
		this.from = from <= 0.0f ? 1.0f : from;
		this.speed = speed;
		this.to = to <= 0.0f ? 1.0f : to;
	}
	
	@Override
	public void update(double delta) {
		if (this.image == null) return;
		if (!this.running) return;
		if (this.step - (speed * delta) <= this.to) {
			this.running = false;
			this.ended = true;
			this.step = this.to;
		} else 
			this.step -= (speed * delta);
			
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		if (!this.started) return;
		if (this.image == null) return;
		if (this.image.getImage() == null) return;
		g.drawImage(image.getImage().getScaledInstance((int) (image.getImage().getWidth() * this.step), (int) (image.getImage().getHeight() * this.step), Image.SCALE_FAST), x, y, null);
	}

	@Override
	public void reset() {
		super.reset();
		this.step = from;
	}
	
	public Dimension getAnimationBounds() {
		return new Dimension((int) (this.image.getImage().getWidth() * this.step), (int) (this.image.getImage().getHeight() * this.step));
	}

	@Override
	public ZoomAnimation newInstance() {
		return new ZoomAnimation(from, speed, to);
	}

	@Override
	public String toString() {
		return "ZoomAnimation [from=" + from + ", speed=" + speed + ", step=" + step + ", to=" + to + "]";
	}

}
