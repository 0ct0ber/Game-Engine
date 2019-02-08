package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Position;

public interface Circle extends Shape {
	
	
	public Position center();
	public double radius();
	public double radiusSquared();
	
	public default Box getBoundingBox() {
		return new BBox(this.center(), this.radius() * 2, this.radius() * 2);
	}
	
	public default boolean intersects(Shape shape) {
		if (shape instanceof Circle) return intersects((Circle)shape);
		if (shape instanceof Box) return intersects((Box)shape);
		return false;
	}
	
	public default double distance(Position position) {
		Position center = this.center();
		double dx = Math.abs(center.getX() - position.getX());
		double dy = Math.abs(center.getY() - position.getY());
		double dist = (dx * dx) + (dy * dy);
		if (dist < radiusSquared()) return 0;
		dist = Math.sqrt(dist) - radius();
		if (dist < 0) dist = 0;
		return dist;
	}
	
	public default double distanceSquared(Position position) {
		double d = distance(position);
		return d * d;
	}
	
	public default boolean intersects(Circle circle) {
		double r = this.radius() + circle.radius();
		Position center1 = this.center();
		Position center2 = circle.center();
		double dx = Math.abs(center1.getX() - center2.getX());
		if (dx > r) return false;
		double dy = Math.abs(center1.getY() - center2.getY());
		if (dy > r) return false;
		return (dx * dx) + (dy * dy) <= r * r;
	}
	
	public default boolean intersects(Box box) {
		return box.distanceSquared(this.center()) <= this.radiusSquared();
	}
	
	public default Circle offset(double offsetX, double offsetY) {
		return new BCircle(this.center().getX() + offsetX, this.center().getY() + offsetY, this.radius());
	}

}
