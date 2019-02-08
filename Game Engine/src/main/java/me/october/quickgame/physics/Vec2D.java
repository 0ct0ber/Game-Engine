package me.october.quickgame.physics;

import java.util.Random;

public class Vec2D implements Cloneable {
	
	protected double x, y;
	
	private static Random random = new Random();
	
	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public Position toPosition() {
		return new Position(x, y);
	}
	
	public Vec2D add(Vec2D vector) {
		this.x += vector.getX();
		this.y += vector.getY();
		return this;
	}
	
	public Vec2D multiply(double multiplier) {
		this.x *= multiplier;
		this.y *= multiplier;
		return this;
	}
	
	public static Vec2D randomVector(double magnitude) {
		double x = Math.random();
		double y = 1 - x;
		x *= magnitude;
		y *= magnitude;
		return new Vec2D(x, y);
	}
	
	public double length() {
		return Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()));
	}
	
	public Vec2D setLength(double length) {
		double currentLength = this.length();
		this.multiply(length/currentLength);
		return this;
	}

	public Vec2D clone() {
		return new Vec2D(x, y);
	}
	
	public Vec2D normalize() {
		double length = this.length();
		this.multiply(1/length);
		return this;
	}
	
	public static Vec2D random() {
		double x = random.nextDouble();
		double y = 1 - x;
		if (random.nextBoolean()) {
			x += -1;
		}
		if (random.nextBoolean()) {
			y *= -1;
		}
		return new Vec2D(x, y);
	}
}
