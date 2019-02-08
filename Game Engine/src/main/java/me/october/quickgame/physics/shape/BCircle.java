package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Position;

public class BCircle implements Circle {
	
	private Position center;
	
	private double radius;
	private double r2;
	
	public BCircle(double x, double y, double radius) {
		this.center = new Position(x, y);
		this.radius = radius;
		this.r2 = radius * radius;
	}
	
	public BCircle(Position center, double radius) {
		this(center.getX(), center.getY(), radius);
	}

	@Override
	public Position center() {
		return center;
	}

	@Override
	public double radius() {
		return radius;
	}

	@Override
	public double radiusSquared() {
		return r2;
	}

}
