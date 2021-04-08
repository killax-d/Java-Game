package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import fr.killax.escape.app.Config;
import fr.killax.escape.assets.Assets;

public class LoadingScene extends AbstractScene {

	private static final long serialVersionUID = 1L;

	private int loadingImageStep = 0;
	private String loadingText = "Loading";
	
	@Override
	public void update(double delta) {
		this.loadingImageStep += 1;
		if (this.loadingImageStep == 30) this.loadingImageStep = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.getImage("textures/ui/loadingBackground.jpg"), 0, 0, (int) (Config.WINDOW_SIZE.getWidth()), (int) (Config.WINDOW_SIZE.getHeight()), null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		Rectangle2D textSize = g.getFontMetrics().getStringBounds(loadingText, g);
		g.drawString(loadingText, 
				(int) (Config.WINDOW_SIZE.getWidth()-textSize.getMaxX()) - 48, 
				(int) (Config.WINDOW_SIZE.getHeight()-textSize.getMaxY()) - 12
			);
		
		g.drawImage(Assets.getTile("textures/ui/loading.png", 200, 200, this.loadingImageStep, 0).getScaledInstance(32, 32, Image.SCALE_FAST),
				(int) Config.WINDOW_SIZE.getWidth() - 40,
				(int) Config.WINDOW_SIZE.getHeight() - 40, null);
		
	}

}
