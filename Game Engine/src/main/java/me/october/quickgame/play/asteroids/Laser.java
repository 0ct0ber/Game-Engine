package me.october.quickgame.play.asteroids;

import java.awt.Color;

import me.october.quickgame.Painter;
import me.october.quickgame.entity.BaseEntity;
import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.collision.Collidable;
import me.october.quickgame.physics.shape.Boundary;

public class Laser extends BaseEntity {

	public Laser(String name, double width, double height, double speed) {
		super(name, width, height);
		this.setMotion(new Vec2D(0, speed));
	}
	
	public Laser(double speed) {
		this("Laser", 3, 20, speed);
	}

	@Override
	public boolean canCollide(Collidable c) {
		return c instanceof Asteroid;
	}
	
	@Override
	public void onCollide(Collidable c, boolean primary) {
		this.remove();
	}

	@Override
	public boolean isMovable() {
		return true;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void draw(Painter painter) {
		painter.fillRect(this.position.getX(), this.position.getY(), width, height, Color.CYAN);
	}
	
	@Override
	public void onBoundary(Boundary boundary) {
		this.remove();
	}

}
