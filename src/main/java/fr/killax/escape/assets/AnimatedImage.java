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
	
	public void setAnimation(AbstractAnimation animation) {
		this.animation = animation;
		animation.setImage(this);
	}
	
	public AbstractAnimation getAnimation() {
		return this.animation;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

	public void draw(int x, int y, int width, int height, Graphics g) {
		if (this.animation == null) g.drawImage(this.image, x, y, width, height, null);
		else this.animation.draw(x, y, g);
	}
	
	public void draw(int x, int y, Graphics g) {
		if (this.animation == null) g.drawImage(this.image, x, y, null);
		else this.animation.draw(x, y, g);
	}
	
	public void update(double delta) {
		this.animation.update(delta);
	}
	
}
