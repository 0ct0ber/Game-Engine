package me.october.quickgame;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect2 {
	
	private byte[] data;
	private AudioFormat format;
	private boolean preload = true;
	
	private Clip preloaded_clip = null;
	
	public SoundEffect2(File file, boolean preload) {
		this.preload = preload;
		try {
			stream(file);
			if (preload) {
				preloaded_clip = makeClip();
			}
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private void stream(File file) throws IOException, UnsupportedAudioFileException {
		AudioInputStream stream = null;
		try {
			stream = AudioSystem.getAudioInputStream(file);
			int available = stream.available();
				int position = 0;
			byte[] data = new byte[available];
			while (available > 0) {
				if (data.length <= position + available) {
					data = Arrays.copyOf(data, position + available);
				}
				stream.read(data, position, available);
				position += available;
				available = stream.available();
			}
			this.data = data;
			this.format = stream.getFormat();
		} finally {
			if (stream != null) stream.close();
		}
	}
	
	private Clip makeClip() throws LineUnavailableException {
		Clip clip = AudioSystem.getClip();
		clip.open(format, data, 0, data.length);
		return clip;
	}
	
	void preload() {
		try {
		makeClip();
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}
	
	public void play(float volume) throws LineUnavailableException {
		Clip clip;
		if (preload) {
			clip = preloaded_clip;
		} else {
			clip = makeClip();
		}
		if (volume != 1) {
			FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			gain.setValue(volume);
		}
		
		clip.start();
		
		if (preload) {
			preloaded_clip = makeClip();
		}
	}

}
