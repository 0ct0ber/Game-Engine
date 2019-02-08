package me.october.quickgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import me.october.quickgame.play.asteroids.AsteroidGame;

public class GameCenter {
	
	public Frame frame;
	public Canvas canvas;
	
	public int WIDTH = 600;
	public int HEIGHT = 600;
	
	private long nano = 1000000000/60;
	
	private static boolean stopped = false;
	
	public AsteroidGame game;
	
	public static void main(String[] args) {
		new GameCenter().start();
	}
	
	public GameCenter() {
		frame = new Frame("Quick Game");
		canvas = new Canvas();
		frame.setSize(WIDTH, HEIGHT);
		canvas.setSize(WIDTH, HEIGHT);
		canvas.setBackground(Color.BLACK);
		frame.add(canvas);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public void start() {
		game = new AsteroidGame(this);
		game.onStart();
		frame.setVisible(true);
		canvas.requestFocus();
		game.onStart();
		long lastTime = System.nanoTime();
		while (!stopped) {
			long time = System.nanoTime();
			long diff = time - lastTime;
			lastTime = time;
			if (diff < nano) {
				long wait = nano - diff;
				long millisWait = wait/1000000;
				int nanoWait = (int)(wait % 1000000);
				try {
					synchronized (this) {
					    this.wait(millisWait, nanoWait);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
			}
			logic();
			render();
		}
	}
	
	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			bs = canvas.getBufferStrategy();
			if (bs == null) return;
		}
		Graphics g = bs.getDrawGraphics();
		Painter painter = new Painter(canvas, g, WIDTH, HEIGHT);
		try {
			draw(painter);
		} finally {
			g.dispose();
		}
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	
	public void logic() {
		game.logic();
	}
	
	public void draw(Painter painter) {
		game.draw(painter);
	}
	
	public static void stop() {
		stopped = true;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public void setWidth(int width) {
		this.WIDTH = width;
		frame.setSize(WIDTH, HEIGHT);
		canvas.setSize(WIDTH, HEIGHT);
	}
	
	public void setHeight(int height) {
		this.HEIGHT = height;
		frame.setSize(WIDTH, HEIGHT);
		canvas.setSize(WIDTH, HEIGHT);
	}
	
	public void setWidthAndHeight(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		frame.setSize(WIDTH, HEIGHT);
		canvas.setSize(WIDTH, HEIGHT);
	}

}
