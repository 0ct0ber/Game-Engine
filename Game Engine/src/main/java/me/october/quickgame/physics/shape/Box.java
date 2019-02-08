package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Position;

public interface Box extends Shape {
	
	public Position center();
	public double width();
	public double height();
	
	public default Box getBoundingBox() {
		return this;
	}
	
	public default double minX() {
		return center().getX() - width()/2;
	}
	
	public default double maxX() {
		return center().getX() + width()/2;
	}
	
	public default double minY() {
		return center().getY() - height()/2;
	}
	
	public default double maxY() {
		return center().getY() + height()/2;
	}
	
	public default boolean intersects(Shape shape) {
		if (shape instanceof Box) return intersects((Box)shape);
		if (shape instanceof Circle) return intersects((Circle)shape);
		return false;
	}
	
	public default boolean intersects(Box box) {
		Position center1 = this.center();
		Position center2 = box.center();
		return Math.abs(center1.getX() - center2.getX()) <= (this.width() + box.width())/2 &&
			   Math.abs(center1.getY() - center2.getY()) <= (this.height() + box.height())/2;
	}
	
	public default boolean contains(Position position) {
		Position center = this.center();
		return Math.abs(center.getX() - position.getX()) <= this.width()/2 &&
			   Math.abs(center.getY() - position.getY()) <= this.height()/2;
	}
	
	public default double distance(Position position) {
		return Math.sqrt(distanceSquared(position));
	}
	
	public default double distanceSquared(Position position) {
		Position center = this.center();
		double dx = Math.abs(center.getX() - position.getX()) - this.width()/2;
		double dy = Math.abs(center.getY() - position.getY()) - this.height()/2;
		if (dx < 0) dx = 0;
		if (dy < 0) dy = 0;
		return (dx * dx) + (dy * dy);
	}
	
	public default boolean intersects(Circle circle) {
		return distanceSquared(circle.center()) <= circle.radiusSquared();
	}
	
	public default Box offset(double offsetX, double offsetY) {
		return new BBox(this.center().add(offsetX, offsetY), this.width(), this.height());
	}

}
