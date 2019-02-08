package me.october.quickgame.physics.shape.polygon;

import me.october.quickgame.physics.Position;

public class Line {
	
	private double slope;
	private double intercept;
	
	public Line(Position pos1, Position pos2) {
		this.slope = (pos2.getY() - pos1.getY())/(pos2.getX() - pos1.getX());
		double y = pos1.getX() * this.slope;
		this.intercept = pos1.getY() - y;
	}
	
	public int orientation(Position point) {
		double lineY = (point.getX() * slope) + intercept;
		if (point.getY() > lineY) return -1;
		if (point.getY() == lineY) return 0;
		return 1;
	}

}
