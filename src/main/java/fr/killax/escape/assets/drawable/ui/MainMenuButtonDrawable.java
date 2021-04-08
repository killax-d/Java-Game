package fr.killax.escape.assets.drawable.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Rectangle2D;

import fr.killax.escape.app.components.AbstractComponent;
import fr.killax.escape.app.components.ButtonComponent;

public class MainMenuButtonDrawable extends AbstractDrawable {

	@Override
	public void paint(AbstractComponent component, Graphics g) {
		ButtonComponent button = (ButtonComponent) component;

        Graphics2D g2d = (Graphics2D) g;
        Point p1 = new Point(button.x, button.y);
        
        RadialGradientPaint gradient = new RadialGradientPaint(
        		new Point(button.x + button.width / 2, button.y + button.height/2), 
        		button.width/2,
        		new float[] {0.2f, 0.6f}, 
        		new Color[] {Color.WHITE, new Color(0, 0, 0, 0)}
        	);
        g2d.setPaint(gradient);
        g2d.fillRect((int) p1.getX(), (int) p1.getY(), button.width, button.height);
        
		g.setFont(new Font("Arial", Font.BOLD, 36));

		Rectangle2D bounds = g.getFontMetrics().getStringBounds(button.text, g);
		
		g.setColor(button.hover ? Color.GRAY : Color.BLACK);
		
		g.drawString(button.text, 
				(int) (button.x + button.width / 2 - bounds.getWidth() / 2), 
				(int) (button.y + bounds.getHeight())
			);
	}

}
