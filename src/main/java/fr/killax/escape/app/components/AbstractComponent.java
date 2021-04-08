package fr.killax.escape.app.components;

import java.awt.Graphics;

import fr.killax.escape.assets.drawable.ui.AbstractDrawable;

public abstract class AbstractComponent {
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public boolean visible;
	public boolean active;
	
	protected AbstractDrawable drawable;
	
	public AbstractComponent(int x, int y, int width, int height, boolean visible, boolean active, AbstractDrawable drawable) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visible = visible;
		this.active = active;
		this.drawable = drawable;
	}

	public AbstractComponent(int x, int y, int width, int height, boolean visible, AbstractDrawable drawable) {
		this(x, y, width, height, visible, true, drawable);
	}
	
	public AbstractComponent(int x, int y, int width, int height, AbstractDrawable drawable) {
		this(x, y, width, height, true, true, drawable);
	}
	
	public abstract void update(double delta);
	
	public void draw(Graphics g) {
		if (drawable == null) return;
		drawable.paint(this, g);
	}
	
	public boolean isVisible() { return visible; }
	public boolean isActive() { return active; }
	
}
