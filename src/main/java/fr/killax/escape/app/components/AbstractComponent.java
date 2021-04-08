package fr.killax.escape.app.components;

import java.awt.Graphics;

public abstract class AbstractComponent {
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public boolean visible;
	public boolean active;
	
	public AbstractComponent(int x, int y, int width, int height, boolean visible, boolean active) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visible = visible;
		this.active = active;
	}

	public AbstractComponent(int x, int y, int width, int height, boolean visible) {
		this(x, y, width, height, visible, true);
	}
	
	public AbstractComponent(int x, int y, int width, int height) {
		this(x, y, width, height, true, true);
	}
	
	public abstract void update(double delta);
	public abstract void draw(Graphics g);
	
	public boolean isVisible() { return visible; }
	public boolean isActive() { return active; }
	
}
