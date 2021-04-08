package fr.killax.escape.app.components;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import fr.killax.escape.assets.AnimatedImage;

public class ImageButtonComponent extends AbstractComponent {

	public AnimatedImage image;
	
	public boolean hover;
	public boolean focus;
	
	public ImageButtonComponent(AnimatedImage image, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.image = image;
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

	@Override
	public void draw(Graphics g) {
		image.draw(x, y, width, height, g);
	}
}
