package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import fr.killax.escape.app.Config;

public class LoadingScene extends AbstractScene {

	private static final long serialVersionUID = 1L;

	private float alpha = 0;
	private String loadingText = "";
	private float loadingPoints = 0.0f;
	private String[] points = new String[] {"", ".", "..", "..."};
	
	@Override
	public void update(double delta) {
		this.alpha += 0.01 * delta;
		if (this.alpha > 1.0f) this.alpha = 1.0f;
		
		this.loadingPoints += 0.05 * delta;
		if (this.loadingPoints > 4.0f) this.loadingPoints = 0.0f;
		this.loadingText = String.format("Loading%s", points[((int) this.loadingPoints)]);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, this.alpha));
		g.fillRect(0, 0, (int) Config.WINDOW_SIZE.getWidth()-1, (int) Config.WINDOW_SIZE.getHeight()-1);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		Rectangle2D textSize = g.getFontMetrics().getStringBounds(loadingText, g);
		g.drawString(loadingText, 
				(int) (Config.WINDOW_SIZE.getWidth()-textSize.getMaxX()) - 8, 
				(int) (Config.WINDOW_SIZE.getHeight()-textSize.getMaxY()) - 8
			);
	}

}
