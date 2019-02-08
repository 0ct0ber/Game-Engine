package me.october.quickgame.physics;

public class Position {
	
	protected double x, y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Vec2D toVector() {
		return new Vec2D(x, y);
	}
	
	public Position add(Vec2D vector) {
		return new Position(this.getX() + vector.getX(), this.getY() + vector.getY());
	}
	
	public Position add(double x, double y) {
		return new Position(this.x + x, this.y + y);
	}

}
