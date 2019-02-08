package me.october.quickgame.play.asteroids;

import me.october.quickgame.ImageLoader;
import me.october.quickgame.entity.ImageEntity;
import me.october.quickgame.physics.collision.Collidable;

public class Spacecraft extends ImageEntity {

	public Spacecraft(String name, double width, double height) {
		super(name, width, height, ImageLoader.loadImage("spacecraft.png"));
	}
	
	public Spacecraft(double width, double height) {
		this("Spacecraft", width, height);
	}

	@Override
	public boolean canCollide(Collidable c) {
		return c.collisionName().contains("Asteroid");
	}
	
	@Override
	public void onCollide(Collidable c, boolean primary) {
		if (!(c instanceof Asteroid)) return;
		hit = true;
	}

	@Override
	public boolean isMovable() {
		return true;
	}
	
	public boolean hit = false;

}
