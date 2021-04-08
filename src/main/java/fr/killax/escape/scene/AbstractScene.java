package fr.killax.escape.scene;

import java.awt.Container;
import java.awt.Graphics;

import fr.killax.escape.app.Config;

public abstract class AbstractScene extends Container {
	
	protected int capFPS = Config.TARGET_FPS;

	public int getFPSCap() { return capFPS; };
	
	private static final long serialVersionUID = 1L;
	
	public abstract void update(double delta);
	
	public abstract void draw(Graphics g);
}
