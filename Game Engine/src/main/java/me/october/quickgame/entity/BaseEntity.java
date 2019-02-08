package me.october.quickgame.entity;

import me.october.quickgame.Entity;
import me.october.quickgame.physics.Position;
import me.october.quickgame.physics.Vec2D;
import me.october.quickgame.physics.shape.BBox;
import me.october.quickgame.physics.shape.Shape;
import me.october.quickgame.scene.GridScene;

public abstract class BaseEntity implements Entity {
	
	private boolean removed = false;
	private GridScene scene;
	protected String name;
	protected double width, height;
	protected Position position;
	protected Vec2D motion = new Vec2D(0, 0);
	public BaseEntity(String name, double width, double height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	
	public synchronized boolean isValid() {
		return scene != null;
	}
	
	public synchronized void remove() {
		if (removed) return;
		if (scene != null) {
			scene.remove(this);
		}
		scene = null;
	}
	
	public synchronized void setScene(GridScene scene) {
		if (this.scene != null) {
			this.scene.remove(this);
		}
		this.scene = scene;
	}
	
	public synchronized GridScene getScene() {
		return scene;
	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}
	@Override
	public Shape getShape() {
		return new BBox(position, width, height);
	}
	@Override
	public boolean implementsCollision() {
		return false;
	}
	@Override
	public Vec2D getMotion() {
		return motion.clone();
	}
	@Override
	public void setMotion(Vec2D motion) {
		this.motion = motion.clone();
	}
	@Override
	public String collisionName() {
		return name;
	}
	@Override
	public Position getPosition() {
		return position;
	}
	@Override
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public final boolean equals(Object obj) {
		return this == obj;
	}
	
	@Override
	public final int hashCode() {
		return super.hashCode();
	}

}
