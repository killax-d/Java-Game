package fr.killax.escape.scene.transition;

import java.awt.Graphics;

public abstract class AbstractTransition {

	protected boolean ended;
	
	public abstract void update(double delta);
	
	public abstract void draw(Graphics g);

	public boolean isEnded() { return ended; };
}
