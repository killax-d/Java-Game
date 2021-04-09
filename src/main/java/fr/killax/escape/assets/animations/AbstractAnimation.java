package fr.killax.escape.assets.animations;

import java.awt.Dimension;
import java.awt.Graphics;

import fr.killax.escape.assets.AnimatedImage;

public abstract class AbstractAnimation {

	protected boolean started;
	protected boolean running;
	protected boolean ended;
	protected AnimatedImage image;

	public void setImage(AnimatedImage image) {	this.image = image; }
	
	public abstract void update(double delta);
	public abstract void draw(int x, int y, Graphics g);

	public void play() {
		reset();
		this.started = true;
		this.running = true;
	}
	
	public void reset() {
		this.started = false;
	}
	
	public abstract Dimension getAnimationBounds();
	
	public boolean isEnded() { return ended; }
	
	public abstract AbstractAnimation newInstance();
}
