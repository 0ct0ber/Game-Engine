package me.october.quickgame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;


//Same issues as commented in SoundEffects.java
public class ImageLoader {
	
	private static ConcurrentHashMap<String, BufferedImage> images = new ConcurrentHashMap<>();
	
	public static synchronized void loadImages(String... paths) {
		for (String path : paths) {
			loadImage(path);
		}
	}
	
	public static synchronized BufferedImage getImage(String path) {
		if (!path.startsWith("/")) path = "/" + path;
		return images.get(path);
	}
	
	public static synchronized BufferedImage loadImage(String path) {
		if (!path.startsWith("/")) path = "/" + path;
		BufferedImage image = images.get(path);
		if (image != null) return image;
		InputStream resource = ImageLoader.class.getResourceAsStream(path);
		if (resource == null) throw new IllegalArgumentException("Could not find image \"" + path + "\"");
		try {
			image = ImageIO.read(resource);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		images.put(path, image);
		return image;
	}
	
	@Deprecated
	public static synchronized BufferedImage loadImageFromFile(String path) {
		if (!path.startsWith("/")) path = "/" + path;
		BufferedImage image = images.get(path);
		if (image != null) return image;
		URL resource = ImageLoader.class.getResource(path);
		try {
			image = ImageIO.read(resource);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		images.put(path, image);
		return image;
	}

}
