package me.october.quickgame.play.asteroids;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Random;

import me.october.quickgame.Entity;
import me.october.quickgame.ImageLoader;
import me.october.quickgame.Painter;
import me.october.quickgame.GameCenter;
import me.october.quickgame.SoundLoader;
import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.shape.BBox;
import me.october.quickgame.scene.Camera;
import me.october.quickgame.scene.GridScene;
import me.october.quickgame.util.CPUTimer;

public class AsteroidGame {
	
	private GridScene scene = new GridScene(600, 600, 8, 8);
	public Camera camera = new Camera();
	public Spacecraft spacecraft = new Spacecraft(72, 20);
	public GameCenter game;
	public SpaceController controller = new SpaceController();
	
	private boolean gameOver = false;
	
	private final Font game_over_font = new Font("SansSerif", Font.BOLD, 36);
	
	public AsteroidGame(GameCenter game) {
		this.game = game;
		game.frame.setIconImage(ImageLoader.getImage("spacecraft_icon.png"));
		SoundLoader.loadSounds("laser.wav", "explosion.wav");
	}
	{
		scene.add(spacecraft, 300, 100);
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			spawnAsteroid(random);
		}
	}
	
	public void prepare() {
		
	}
	
	public void onStart() {
		new Thread() {
			@Override
			public void run() {
				game.canvas.addKeyListener(controller);
			}
		}.start();
	}
	
	private void spawnAsteroid(Random random) {
		int size = random.nextInt(70 - 37) + 37;
		double x = random.nextDouble() * scene.width();
		if (x - size/2 - 3 < 0) x = size/2 + 3;
		if (x + size/2 + 3 > scene.width()) x = scene.width() - size/2 - 3;
		double y = random.nextDouble() * scene.height();
		if (y - size/2 - 321 < 0) y = size/2 + 321;
		if (y + size/2 + 3 > scene.width()) y = scene.height() - size/2 - 3;
		Asteroid asteroid = new Asteroid(size);
		asteroid.setMotion(Vec2D.randomVector(random.nextDouble() * 1.7));
		int hp;
		if (size <= 42) {
			hp = 1;
		} else if (size <= 55) {
			hp = 2;
		} else if (size <= 65) {
			hp = 3;
		} else {
			hp = 4;
		}
		asteroid.setHitpoints(hp);
		for (Entity entity : scene.getEntities(new BBox(x, y, size, size))) {
			if (entity instanceof Asteroid) {
				spawnAsteroid(random);
				return;
			}
		}
		scene.add(asteroid, x, y);
	}
	
	private int fire_load_time = 45;
	private int fire = 0;
	
	public void logic() {
		if (gameOver) {
			return;
		}
		if (spacecraft.hit) {
			gameOver = true;
			SoundLoader.playSound("explosion.wav", 0.5);
			return;
		}
		if (fire > 0) {
			fire++;
			if (fire >= fire_load_time) fire = 0;
		}
		double velX = 0;
		if (controller.left() == controller.right()) {
			velX = 0;
		} else if (controller.left()) {
			velX = -0.5;
		} else if (controller.right()) {
			velX = 0.5;
		}
		spacecraft.setMotion(new Vec2D(velX, 0));
		if (velX != 0) scene.move(spacecraft);
		if (controller.spacebar()) {
			if (fire == 0) {
				fire = 1;
				Laser laser = new Laser(3);
				double y = spacecraft.getPosition().getY() + spacecraft.getShape().getBoundingBox().height()/2 + 3;
				scene.add(laser, spacecraft.getPosition().getX(), y);
				SoundLoader.playSound("laser.wav", 0.5f);
			}
		}
		for (Entity entity : scene.getAllEntities()) {
			if (entity == spacecraft || !entity.isMoving()) continue;
			scene.move(entity);
		}
	}
	
	private boolean drawn = false;
	public void draw(Painter painter) {
		if (!gameOver) {
			camera.snap(painter, scene);
		} else {
			if (drawn) return;
			CPUTimer gameovertimer = new CPUTimer();
			gameovertimer.start();
			drawn = true;
			painter.getGraphics().setColor(Color.WHITE);
			painter.getGraphics().setFont(game_over_font);
			
			Component component = painter.getComponent();
			if (component == null) System.out.println("component is null");
			int fontWidth = 204; /*game_over_metrics.stringWidth("Game Over");*/
			int fontHeight = 35; /*game_over_metrics.getAscent();*/
			painter.getGraphics().drawString("Game Over", 300 - fontWidth/2, 300 - fontHeight/2);
			gameovertimer.end();
			gameovertimer.print("Took [millis] milliseconds ([nano] nanoseconds) to load the Game Over screen");
		}
	}

}
