package me.october.quickgame.testresources;

import java.io.InputStream;

import me.october.quickgame.SoundEffect;

public class SoundTester {
	
	public static void main(String[] args) {
		InputStream resource = SoundTester.class.getResourceAsStream("/laser.wav");
		SoundEffect effect = new SoundEffect(resource);
		System.out.println("It worked!");
		effect.play(1.0f);
	}

}
