package fr.killax.escape.app.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import fr.killax.escape.assets.AnimatedImage;

public class ImageButtonComponent extends AbstractComponent {

	public String text;
	public Font font;
	public AnimatedImage image;
	public AnimatedImage imageHover;
	
	public boolean hover;
	public boolean focus;
	
	public ImageButtonComponent(String text, Font font, AnimatedImage image, AnimatedImage imageHover, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
		this.font = font;
		this.image = image;
		this.imageHover = imageHover;
		this.hover = false;
		this.focus = true;
	}
	
	public ImageButtonComponent(AnimatedImage image, AnimatedImage imageHover, int x, int y, int width, int height) {
		this(null, null, image, imageHover, x, y, width, height);
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
		return hover && focus && isVisible() && isActive() && isInside(e.getX(), e.getY());
	}

	protected boolean isInside(int x, int y) {
		return ((x >= this.x && x <= this.x + this.width)
				&& (y >= this.y && y <= this.y + this.height));
	}

	@Override
	public void draw(Graphics g) {
		if (hover) imageHover.draw(x, y, width, height, g);
		else image.draw(x, y, width, height, g);
		
		if (font != null) g.setFont(font);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
		g.drawString(text, x + width / 2 - (int) bounds.getWidth() / 2, y + height / 2 + (int) bounds.getHeight() / 2 - (int) bounds.getHeight() / 8);
	}
}
