package fr.killax.escape.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fr.killax.escape.game.utils.Tips;

public class I18N {

	private final static HashMap<String, String> fallback_texts = new HashMap<String, String>();
	private final static HashMap<String, String> texts = new HashMap<String, String>();
	
	public static void init() {
		load(fallback_texts, "en");
		Tips.init();
	}
	
	private static void load(HashMap<String, String> textsMap, String lang) {
		if (textsMap == null || lang == null) return;
		textsMap.clear();
		
		try (BufferedReader bfr = new BufferedReader(new InputStreamReader(I18N.class.getResourceAsStream(String.format("langs/%s.lang", lang)), StandardCharsets.UTF_8))) {
			String line = null;
			while((line = bfr.readLine()) != null) {
				String[] parts = line.split("=", 2);
				
				if (textsMap.containsKey(parts[0])) System.err.println(String.format("[I18N][Loader] Duplicate key : %s", parts[0]));
				textsMap.put(parts[0], parser(parts[1]));
			}
		} catch (IOException e) {
			System.err.println(String.format("[I18N][Loader] Error while loading langs/%s.lang", lang));
			e.printStackTrace();
		}
	}
	
	public static void load(String lang) {
		if (lang == "en") return;
		load(texts, lang);
	}
	
	public static String getText(String key) {
		if (texts.containsKey(key)) return texts.get(key);
		if (fallback_texts.containsKey(key)) return fallback_texts.get(key);
		return key; // Key not present
	}
	
	public static String parser(String str) {
		// Add all replacement
		str = str.replaceAll("%newline%", "\n");
		return str;
	}
	
	public static Set<String> getKeysStartWith(String str) {
		Set<String> keys = new HashSet<String>();
		for (String key : fallback_texts.keySet()) if (key.startsWith(str)) keys.add(key);
		return keys;
	}
	
}
