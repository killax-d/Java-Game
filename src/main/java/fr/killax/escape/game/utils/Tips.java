package fr.killax.escape.game.utils;

import java.util.Random;

import fr.killax.escape.assets.I18N;

public class Tips {

	private static final Random random = new Random();
	private static String[] TIPS_KEYS;
	
	public static void init() {
		TIPS_KEYS = I18N.getKeysStartWith("game.tips.").toArray(new String[] {});
	}
	
	public static String getTip() {
		if (TIPS_KEYS == null || TIPS_KEYS.length == 0) return "game.tips.notips";
		return I18N.getText(TIPS_KEYS[random.nextInt(TIPS_KEYS.length)]);
	}
	
}
