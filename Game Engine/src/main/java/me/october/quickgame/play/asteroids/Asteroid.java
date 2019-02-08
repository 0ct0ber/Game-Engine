package me.october.quickgame.play.asteroids;

import java.awt.Image;
import java.util.Random;

import me.october.quickgame.ImageLoader;
import me.october.quickgame.entity.ImageEntity;
import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.collision.Collidable;
import me.october.quickgame.physics.shape.BCircle;
import me.october.quickgame.physics.shape.Boundary;
import me.october.quickgame.physics.shape.Circle;

public class Asteroid extends ImageEntity {
	
	private static Random rand = new Random();
	protected int hp;

	public Asteroid(String name, double width, double height, Image image) {
		super(name, width, height, ImageLoader.loadImage("asteroid.png"));
	}
	
	public Asteroid(double size) {
		this("Asteroid", size, size, ImageLoader.loadImage("asteroid.png"));
	}
	
	@Override
	public void onBoundary(Boundary boundary) {
		System.out.println("bounds");
		double velX = this.getMotion().getX();
		double velY = this.getMotion().getY();
		if (boundary.vertical) velX *= -1;
		if (boundary.horizontal) velY *= -1;
		this.setMotion(new Vec2D(velX, velY));
	}

	@Override
	public boolean canCollide(Collidable c) {
		return true;
	}
	
	@Override
	public void onCollide(Collidable c, boolean primary) {
		if (c instanceof Laser) {
			hp--;
			if (hp <= 0) {
			    this.remove();
			}
		}
		if (c instanceof Asteroid) {
			double velX = this.getMotion().getX();
			double velY = this.getMotion().getY();
			if (rand.nextInt(8) == 0) {
				this.setMotion(Vec2D.randomVector(this.getMotion().length()));
				return;
			}
			
			if (rand.nextBoolean()) {
				velX *= -1;
				velY *= -1;
			} else if (rand.nextBoolean()) {
				velX *= -1;
			} else {
				velY *= -1;
			}
			this.setMotion(new Vec2D(velX, velY));
		}
	}

	@Override
	public boolean isMovable() {
		return true;
	}
	
	@Override
	public Circle getShape() {
		return new BCircle(this.getPosition(), this.width/2);
	}
	
	public void setHitpoints(int hp) {
		this.hp = hp;
	}
	
	public int getHitpoints() {
		return hp;
	}

}
