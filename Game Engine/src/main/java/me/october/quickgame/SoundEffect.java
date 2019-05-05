package me.october.quickgame;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	
	private byte[] data;
	private AudioFormat format;
	
	public SoundEffect(byte[] data, AudioFormat format) {
		this.data = data;
		this.format = format;
	}
	
	public SoundEffect(File file) {
		setup(file);
	}
	
	public SoundEffect(InputStream input) throws RuntimeException {
		setup(input);
	}
	
	private void setup(Object input) {
		AudioInputStream stream = null;
		
		try {
			
			if (input instanceof File) {
				stream = AudioSystem.getAudioInputStream((File)input);
			} else if (input instanceof InputStream) {
				InputStream istream = (InputStream)input;
				if (!istream.markSupported()) istream = new BufferedInputStream(istream);
				//AudioInputStream requires mark supported, or throws an exception
				stream = AudioSystem.getAudioInputStream(istream);
			}
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
		} catch (UnsupportedAudioFileException | IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
			if (stream != null) {
				stream.close();
			}
			if (input instanceof InputStream) {
				((InputStream)input).close();
			}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void preload() {
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(format, data, 0, data.length);
			clip.close();
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			return;
		}
	}
	
	public void play(float volume) {
		Clip clip;
			try {
				clip = AudioSystem.getClip();
				clip.open(format, data, 0, data.length);
				FloatControl gain = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
				gain.setValue(volume);
			} catch (LineUnavailableException ex) {
				ex.printStackTrace();
				return;
			}
			
		new Thread() {
			public void run() {
				clip.setFramePosition(0);
				clip.start();
			}
		}.start();
	}

}
