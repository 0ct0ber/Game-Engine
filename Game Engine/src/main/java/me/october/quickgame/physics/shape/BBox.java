package me.october.quickgame.physics.shape;

import me.october.quickgame.physics.Position;

public class BBox implements Box {
	
	private Position center;
	private double width, height;
	
	public BBox(double x, double y, double width, double height) {
		this.center = new Position(x, y);
		this.width = width;
		this.height = height;
	}
	
	public BBox(Position center, double width, double height) {
		this(center.getX(), center.getY(), width, height);
	}

	@Override
	public Position center() {
		return center;
	}

	@Override
	public double width() {
		return width;
	}

	@Override
	public double height() {
		return height;
	}

}
