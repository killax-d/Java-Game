package fr.killax.escape.assets.animations.texts;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import fr.killax.escape.assets.AnimatedImage;
import fr.killax.escape.assets.animations.AbstractAnimation;

public class AnimatedText {
	
	private String text;
	private Font font;
	private Boolean eachLetter;
	private AnimatedImage[] images;
	private int msDelay;
	
	private int stringWidth;
	private int indexAnimation;
	
	public AnimatedText(String text, Font font, AbstractAnimation animation, boolean eachLetter, int msDelay) {
		this.text = text;
		this.font = font;
		this.eachLetter = eachLetter;
		this.images = new AnimatedImage[eachLetter ? text.length() : 1];
		if (eachLetter)
			for (int i = 0; i < text.length(); i++)
				this.images[i] = new AnimatedImage(null, animation.clone());
		else this.images[0] = new AnimatedImage(null, animation);
		this.msDelay = msDelay;
		
		this.stringWidth = 0;
	}
	
	public AnimatedText(String text, Font font, AbstractAnimation animation) {
		this(text, font, animation, false, 0);
	}
	
	public int getWidth() { 
		if (!eachLetter && this.images[0].getImage() != null)
			return (int) this.images[0].getAnimation().getAnimationBounds().getWidth();
		return getXCoordinate(images.length);
	}
	public int getHeight() {
		if (!eachLetter && this.images[0].getImage() != null)
			return (int) this.images[0].getAnimation().getAnimationBounds().getHeight();
		return font.getSize();
	}

	public void play() {
		if (msDelay > 0)
			new Timer().scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if (indexAnimation < images.length)
						images[indexAnimation++].getAnimation().play();
					else
						cancel();
				}
				
			}, 0, msDelay);
		else
			for (AnimatedImage image : this.images)
				image.getAnimation().play();
	}
	
	public void update(double delta) {
		for (AnimatedImage image : this.images) if (image != null) image.update(delta);
	}
	
	public void draw(int x, int y, Graphics g) {
		if (this.images[0] == null) return;
		if (this.images[0].getImage() == null) proceedTextToImage(g);
		
		g.setFont(font);
		if (this.stringWidth == 0) this.stringWidth = g.getFontMetrics().stringWidth(text);
		for (int i = 0; i < this.images.length; i++) this.images[i].draw(
				x + getXCoordinate(i),
				y,
				g);
	}
	
	private int getXCoordinate(int i) {
		int sum = 0;
		for (int index = 0; index < i; index++) if (this.images[index].getImage() != null) sum += this.images[index].getImage().getWidth();
		// 1 pixel padding
		return 1 + i * 2 + sum;
	}
	
	public void proceedTextToImage(Graphics g) {
		if (eachLetter) 
			for (int i = 0; i < this.text.length(); i++) 
				this.images[i].setImage(charToImage(text.charAt(i), g));
		else this.images[0].setImage(stringToImage(text, g));
	}
	
	public BufferedImage stringToImage(String str, Graphics g) {
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		
		BufferedImage img = new BufferedImage(fontMetrics.stringWidth(str), fontMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(str, 0, fm.getAscent());
        g2d.dispose();
        return img;
	}
	
	public BufferedImage charToImage(char c, Graphics g) {
		return this.stringToImage(new String(new char[] {c}), g);
	}

}
