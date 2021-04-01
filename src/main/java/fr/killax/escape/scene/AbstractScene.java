package fr.killax.escape.scene;

import java.awt.Container;
import java.awt.Graphics;

public abstract class AbstractScene extends Container {

	private static final long serialVersionUID = 1L;
	
	public abstract void update(double delta);
	
	public abstract void draw(Graphics g);
}
