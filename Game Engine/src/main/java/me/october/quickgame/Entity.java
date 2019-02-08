package me.october.quickgame;

import me.october.quickgame.physics.Position;
import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.collision.Collidable;
import me.october.quickgame.physics.shape.Boundary;
import me.october.quickgame.scene.GridScene;

public interface Entity extends Collidable {
	
	public Position getPosition();
	public void setPosition(Position position);
	
	public boolean isVisible();
	public void draw(Painter painter);
	
	public default boolean isMoving() {
		if (!isMovable()) return false;
		Vec2D motion = this.getMotion();
		return motion.getX() != 0 || motion.getY() != 0;
	}
	
	public void remove();
	public void setScene(GridScene scene);
	public GridScene getScene();
	
	public default void tick() {}
	
	public default void onBoundary(Boundary boundary) {}

}
