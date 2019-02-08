package me.october.quickgame.physics.collision;

import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.shape.Shape;

public interface Collidable {
	
	public boolean isCollidable();
	public boolean canCollide(Collidable c);
	public default void onCollide(Collidable c, boolean primary) {}
	public Shape getShape();
	public boolean implementsCollision();
	
	public boolean isMovable();
	public Vec2D getMotion();
	public void setMotion(Vec2D motion);
	public default boolean collides(Collidable c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public String collisionName();

}
