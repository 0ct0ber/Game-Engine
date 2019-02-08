package me.october.quickgame;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

public abstract class Game {
	
	public Frame frame;
	public Canvas canvas;
	
	protected GameCenter center;
	
	private Object fpsLock = new Object();
	
	private int framerate = 60;
	private long nano = 1000000000;
	
	public Game(int width, int height, String title, GameCenter center) {
		this.center = center;
		this.canvas = center.canvas;
		this.frame = center.frame;
		center.setWidthAndHeight(width, height);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public int width() {
		return center.WIDTH;
	}
	
	public int height() {
		return center.HEIGHT;
	}
	
	public int getFPS() {
		synchronized (fpsLock) {
			return framerate;
		}
	}
	
	protected long getNanoRate() {
		synchronized (fpsLock) {
			return nano;
		}
	}
	
	public void setFPS(int fps) {
		synchronized(fpsLock) {
			framerate = fps;
			nano = 1000000000/fps;
		}
	}
	
	public void display() {
		frame.setVisible(true);
	}
	
	public abstract void setup();
	
	public void start() {
		onStart();
		while (!stopped) {
			tick();
		}
	}
	/**Called before the first tick, after {@code start()}*/
	public abstract void onStart();
	
	private volatile boolean stopped = false;
	
	public void stop() {
		stopped = true;
	}
	
	public boolean isStopped() {
		return stopped;
	}
	
	private Toolkit toolkit = null;
	private long lastTime;
	
	public void tick() {
		long time = System.nanoTime();
		long diff = time - lastTime;
		lastTime = time;
		
		if (diff < nano) {
			long millis = diff / 1000000;
			int nanos = (int)diff % 1000000;
			synchronized (this) {
				try {
					this.wait(millis, nanos);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
		}
		
		logic();
		render();
	}
	
	public boolean doRender() {
		return true;
	}
	
	public abstract void logic();
	
	/**Not recommended to be overridden*/
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			bs = canvas.getBufferStrategy();
			if (bs == null) return;
		}
		
		
		
		Graphics g = bs.getDrawGraphics();
		try {
		    Painter painter = new Painter(canvas, g, this.width(), this.height());
		    draw(painter);
		} finally {
			g.dispose();
		}
		bs.show();
		
		if (toolkit == null) {
			toolkit = Toolkit.getDefaultToolkit();
		}
		
		toolkit.sync();
	}
	
	public abstract void draw(Painter painter);

}
