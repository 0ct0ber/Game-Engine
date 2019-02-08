package me.october.quickgame.v2;

public interface GameObject {
	
	public boolean isVisible();
	public boolean isCollidable();
	public boolean canCollide(GameObject object);
	

}
