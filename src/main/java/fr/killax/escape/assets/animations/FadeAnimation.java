package fr.killax.escape.assets.animations;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FadeAnimation extends AbstractAnimation {
	
	private float from;
	private float speed;
	private float step;
	private float to;

	public FadeAnimation(float from, float speed, float to) {
		this.from = from < 0.0f ? 0.0f : from > 1.0f ? 1.0f: from;
		this.speed = from < to ? speed : speed*-1;
		this.to = to < 0.0f ? 0.0f : to > 1.0f ? 1.0f : to;
	}
	
	@Override
	public void update(double delta) {
		if (this.image == null) return;
		if (!this.running) return;
		if (this.speed < 0 
				? (this.step + (speed * delta) <= this.to)
				: (this.step + (speed * delta) >= this.to)
		){
			this.running = false;
			this.ended = true;
			this.step = this.to;
		} else 
			this.step += (speed * delta);
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		if (!this.started) return;
		if (this.image == null) return;
		if (this.image.getImage() == null) return;
		if (this.ended) {
			g.drawImage(image.getImage(), x, y, null);
			return;
		}
		Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.step));
        g2d.drawImage(image.getImage(), 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

	}

	@Override
	public void reset() {
		super.reset();
		this.ended = false;
		this.step = from;
	}
	
	public Dimension getAnimationBounds() {
		return new Dimension((int) (this.image.getImage().getWidth() * this.step), (int) (this.image.getImage().getHeight() * this.step));
	}

	@Override
	public FadeAnimation clone() {
		return new FadeAnimation(from, speed, to);
	}

	@Override
	public String toString() {
		return "FadeAnimation [from=" + from + ", speed=" + speed + ", step=" + step + ", to=" + to + "]";
	}

}
