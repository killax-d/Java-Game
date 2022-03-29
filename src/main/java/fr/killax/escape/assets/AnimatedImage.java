package fr.killax.escape.assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fr.killax.escape.assets.animations.AbstractAnimation;

public class AnimatedImage {

	private BufferedImage image;
	private AbstractAnimation animation;
	
	public AnimatedImage(BufferedImage image, AbstractAnimation animation) {
		this.image = image;
		this.animation = animation;
	}
	
	public AnimatedImage(BufferedImage image) {
		this(image, null);
	}
	
	public AnimatedImage setAnimation(AbstractAnimation animation) {
		this.animation = animation;
		animation.setImage(this);
		return this;
	}
	
	public AbstractAnimation getAnimation() {
		return this.animation;
	}
	
	public AnimatedImage setImage(BufferedImage image) {
		this.image = image;
		if (this.animation != null) this.animation.setImage(this);
		return this;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

	public void draw(int x, int y, int width, int height, Graphics g) {
		if (this.image == null) return;
		if (this.animation == null) g.drawImage(this.image, x, y, width, height, null);
		else this.animation.draw(x, y, g);
	}
	
	public void draw(int x, int y, Graphics g) {
		this.draw(x, y, image.getWidth(), image.getHeight(), g);
	}
	
	public void update(double delta) {
		if (this.animation == null) return;
		this.animation.update(delta);
	}

	@Override
	public String toString() {
		return "AnimatedImage [image=" + image + ", animation=" + animation + "]";
	}
	
}
