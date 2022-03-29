package fr.killax.escape.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import fr.killax.escape.app.Config;
import fr.killax.escape.assets.Assets;
import fr.killax.escape.game.utils.Tips;
import fr.killax.escape.utils.texts.TextAlignment;
import fr.killax.escape.utils.texts.TextFormat;
import fr.killax.escape.utils.texts.TextRenderer;

public class LoadingScene extends AbstractScene {

	private static final long serialVersionUID = 1L;

	private float loadingImageStep = 0;
	private String loadingText = "Loading";
	private String tip = Tips.getTip();
	
	@Override
	public void update(double delta) {
		this.loadingImageStep += 1 * delta;
		if (this.loadingImageStep >= 30) this.loadingImageStep = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Assets.getImage("textures/ui/loadingBackground.jpg").getImage(), 0, 0, (int) (Config.WINDOW_SIZE.getWidth()), (int) (Config.WINDOW_SIZE.getHeight()), null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		Rectangle2D textSize = g.getFontMetrics().getStringBounds(loadingText, g);
		g.drawString(loadingText, 
				(int) (Config.WINDOW_SIZE.getWidth()-textSize.getMaxX()) - 48, 
				(int) (Config.WINDOW_SIZE.getHeight()-textSize.getMaxY()) - 12
			);
		
		g.drawImage(Assets.getTile("textures/ui/loading.png", 200, 200, (int) this.loadingImageStep, 0).getScaledInstance(32, 32, Image.SCALE_FAST),
				(int) Config.WINDOW_SIZE.getWidth() - 40,
				(int) Config.WINDOW_SIZE.getHeight() - 40, null);

		g.setColor(new Color(0.f, 0.f, 0.f, 0.75f));
		g.fillRoundRect(20, (int) Config.WINDOW_SIZE.getHeight() - 200, (int) Config.WINDOW_SIZE.getWidth() - 40, 140, 25, 25);
		g.setColor(new Color(1.f, 1.f, 1.f, 0.75f));
		g.drawRoundRect(20, (int) Config.WINDOW_SIZE.getHeight() - 200, (int) Config.WINDOW_SIZE.getWidth() - 40, 140, 25, 25);
		
		Rectangle bounds = new Rectangle(40, (int) Config.WINDOW_SIZE.getHeight() - 180, (int) Config.WINDOW_SIZE.getWidth() - 80, 100);

	    TextRenderer.drawString(
	        g,
	        this.tip,
	        g.getFont(),
	        Color.WHITE,
	        bounds,
	        TextAlignment.MIDDLE,
	        TextFormat.FIRST_LINE_VISIBLE
	    );
		
	}

}
