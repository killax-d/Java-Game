package fr.killax.escape.app.components;

import java.awt.event.MouseEvent;

import fr.killax.escape.assets.drawable.ui.AbstractDrawable;

public class ButtonComponent extends AbstractComponent {

	public String text;
	
	public boolean hover;
	public boolean focus;
	
	public ButtonComponent(String text, int x, int y, int width, int height, AbstractDrawable drawable) {
		super(x, y, width, height, drawable);
		this.text = text;
		this.hover = false;
		this.focus = false;
	}

	@Override
	public void update(double delta) {}

	public void move(MouseEvent e) {
		hover = isInside(e.getX(), e.getY());
	}
	
	public void click(MouseEvent e) {
		focus = isInside(e.getX(), e.getY());
	}
	
	public boolean release(MouseEvent e) {
		return focus && isVisible() && isActive() && isInside(e.getX(), e.getY());
	}

	protected boolean isInside(int x, int y) {
		return ((x >= this.x && x <= this.x + this.width)
				&& (y >= this.y && y <= this.y + this.height));
	}
}
