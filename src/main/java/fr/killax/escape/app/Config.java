package fr.killax.escape.app;

import java.awt.Dimension;

import fr.killax.escape.assets.I18N;

public class Config {
	
	public static Dimension WINDOW_SIZE = new Dimension(800, 600);
	public static String WINDOW_TITLE = I18N.getText("window.title");
	public static int TARGET_FPS = 60;
	public static boolean DEBUG = true;

}
