package me.october.quickgame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;


//See SoundEffect.java for issues
public class SoundLoader {
	
	private static ConcurrentHashMap<String, SoundEffect> sounds = new ConcurrentHashMap<>();
	
	public static void loadSounds(String... soundPaths) {
		for (String soundPath : soundPaths) {
			loadSound(soundPath);
		}
	}
	
	public static synchronized SoundEffect loadSound(String path) {
		if (!path.startsWith("/")) path = "/".concat(path);
		SoundEffect effect = sounds.get(path);
		if (effect != null) return effect;
		InputStream resource = SoundEffect.class.getResourceAsStream(path);
		if (resource == null) throw new IllegalArgumentException("Could not find sound \"" + path + "\"");
		resource = new BufferedInputStream(resource);
		effect = new SoundEffect(resource);
		sounds.put(path, effect);
		return effect;
	}
	
	@Deprecated
	public static synchronized SoundEffect loadSoundFromLocalFile(String path) {
		
		SoundEffect effect = sounds.get(path);
		if (effect != null) {
			return effect;
		}
		
		;
		URL url = SoundLoader.class.getResource(path);
		URI uri;
		try {
			uri = url.toURI();
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
		File file = new File(uri);
		effect = new SoundEffect(file);
		effect.preload();
		sounds.put(path, effect);
		return effect;
		
	}
	
	public static synchronized SoundEffect getSound(String path) {
		return sounds.get(path);
	}
	
	public static void playSound(String path, double volume) {
		loadSound(path).play((float)volume);
	}

}
