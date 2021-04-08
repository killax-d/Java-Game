package fr.killax.escape.assets.animations;

import java.awt.Dimension;
import java.awt.Graphics;

import fr.killax.escape.assets.AnimatedImage;

public abstract class AbstractAnimation {

	protected boolean running;
	protected boolean ended;

	public abstract void setImage(AnimatedImage image);
	public abstract void update(double delta);
	public abstract void draw(int x, int y, Graphics g);
	public abstract void play();
	public abstract void reset();
	
	public abstract Dimension getAnimationBounds();
	
	public boolean isEnded() { return ended; }
}
