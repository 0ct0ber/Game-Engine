package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Position;
import me.october.quickgame.physics.Vec2D;

/*Represents objects used to check for collision intersections*/
public interface Shape {
	
	public Position center();
	public boolean intersects(Shape shape);
	public Shape offset(double offsetX, double offsetY);
	public default Shape offset(Vec2D offset) {
		return this.offset(offset.getX(), offset.getY());
	}
	
	public Box getBoundingBox();

}
