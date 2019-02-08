package me.october.quickgame.scene;

import java.util.Collection;
import java.util.HashSet;

import me.october.quickgame.Entity;

public class Cell {
	
	Collection<Entity> entities;
	private int x;
	private int y;
	
	public Cell(Collection<Entity> entities, int x, int y) {
		this.entities = entities;
		this.x = x;
		this.y = y;
	}
	
	public Cell(int x, int y) {
		this(new HashSet<Entity>(), x, y);
	}
	
	public Collection<Entity> getEntities() {
		return entities;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
