package me.october.quickgame.physics.collision;

public class Collision {
	
	private Collidable primary, secondary;
	
	public Collision(Collidable primary, Collidable secondary) {
		this.primary = primary;
		this.secondary = secondary;
	}
	
	public Collidable getPrimary() {
		return primary;
	}
	
	public Collidable getSecondary() {
		return secondary;
	}

}
