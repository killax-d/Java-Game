package fr.killax.escape.assets;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

public class Assets {
	
	/**
	 * @author Donné Dylan
	 * Simple assets loader
	 */

	private static Map<String, AnimatedImage> assets = new HashMap<String, AnimatedImage>();
	private static Map<String, BufferedSound> sounds = new HashMap<String, BufferedSound>();
	private static Map<String, Font> fonts = new HashMap<String, Font>();

	// The fail-safe default texture for missing assets
	private static AnimatedImage NO_TEXTURE = new AnimatedImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
	private static Font NO_FONT = new Font("Arial", Font.TRUETYPE_FONT, 12);
	static {
		NO_TEXTURE.getImage().setRGB(0, 0, new Color(140, 87, 113).getRGB());
	}
	
	
	/**
	 * Image finder and loader
	 * @param path
	 * @return BufferedImage
	 */
	public static AnimatedImage getImage(String path) {
		if (assets.containsKey(path)) {
			return assets.get(path);
		}
		try (InputStream is = Assets.class.getResourceAsStream(path)) {
			if (is != null) {
				AnimatedImage img = new AnimatedImage(ImageIO.read(is));
				assets.putIfAbsent(path, img);
				return img;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assets.putIfAbsent(path, NO_TEXTURE);
		return NO_TEXTURE;
	}

	
	/**
	 * Sound finder and loader
	 * @param path
	 * @return BufferedSound
	 */
	public static BufferedSound getSound(String path, int type) {
		if (sounds.containsKey(path)) {
			return sounds.get(path);
		}
		try (InputStream is = Assets.class.getResourceAsStream(path)) {
			if (is != null) {
				BufferedSound sound = new BufferedSound(is, type);
				sounds.putIfAbsent(path, sound);
				return sound;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Font finder and loader
	 * @param path
	 * @return Font
	 */
	public static Font getFont(String path, float size) {
		if (fonts.containsKey(path)) {
			return fonts.get(path).deriveFont(size);
		}
		try (InputStream is = Assets.class.getResourceAsStream(path)) {
		    Hashtable<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
		    map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
		    map.put(TextAttribute.TRACKING, 0.08);
		    map.put(TextAttribute.SIZE, size);
			if (is != null) {
				Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(map);
				fonts.putIfAbsent(path, font);
				return font;
			}
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		fonts.putIfAbsent(path, NO_FONT);
		return NO_FONT.deriveFont(size);
	}

	
	/**
	 * Sprite cropper
	 * @param path
	 * @return BufferedImage
	 */
	public static BufferedImage getTile(String path, int width, int height, int x, int y) {
		BufferedImage tileset = getImage(path).getImage();
		boolean validTexture = tileset.getWidth()>= width && tileset.getHeight() >= height;
		if (validTexture) {
			return tileset.getSubimage(x * width, y * height, width, height);
		}
		return NO_TEXTURE.getImage();
	}

}