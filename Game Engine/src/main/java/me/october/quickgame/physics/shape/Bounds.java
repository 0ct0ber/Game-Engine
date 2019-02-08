package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.collision.Collidable;

public class Bounds implements Collidable {
	
	protected double width, height;
	
	public Bounds(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public Boundary checkBoundary(Shape shape) {
		Box bounding = shape.getBoundingBox();
		boolean horizontal = false;
		boolean vertical = false;
		if (bounding.minX() < 0 || bounding.maxX() > width) vertical = true;
		if (bounding.minY() < 0 || bounding.maxY() > height) horizontal = true;
		if (!horizontal && !vertical) return null;
		return new Boundary(this, horizontal, vertical);
	}
	
	public Shape adjustBounds(Shape shape) {
		Box bounding = shape.getBoundingBox();
		double x = bounding.center().getX();
		double y = bounding.center().getY();
		boolean adjusted = false;
		if (bounding.minX() < 0) {
			adjusted = true;
			x = bounding.width()/2;
		} else if (bounding.maxX() > width) {
			adjusted = true;
			x = width - bounding.width()/2;
		}
		if (bounding.minY() < 0) {
			adjusted = true;
			y = bounding.height()/2;
		} else if (bounding.maxY() > height) {
			adjusted = true;
			y = height - bounding.height()/2;
		}
		if (!adjusted) return shape;
		return shape.offset(x - bounding.center().getX(), y - bounding.center().getY());
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean canCollide(Collidable c) {
		return !(c instanceof Bounds);
	}

	@Override
	public Shape getShape() {
		return null;
	}

	@Override
	public boolean implementsCollision() {
		return true;
	}

	@Override
	public boolean collides(Collidable c) {
		return checkBoundary(c.getShape()) != null;
	}

	@Override
	public boolean isMovable() {
		return false;
	}

	@Override
	public Vec2D getMotion() {
		return null;
	}

	@Override
	public void setMotion(Vec2D motion) {
		throw new UnsupportedOperationException("setMotion");
	}

	@Override
	public String collisionName() {
		return "Bounds";
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}

}
