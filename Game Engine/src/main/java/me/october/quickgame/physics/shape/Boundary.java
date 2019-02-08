package me.october.quickgame.physics.shape;

public class Boundary {
	
	public boolean horizontal, vertical;
	public Bounds bounds;
	
	public Boundary(Bounds bounds, boolean horizontal, boolean vertical) {
		this.bounds = bounds;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

}
