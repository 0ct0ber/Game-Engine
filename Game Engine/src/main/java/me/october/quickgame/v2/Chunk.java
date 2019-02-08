package me.october.quickgame.v2;

import java.util.Collection;
import java.util.Collections;

public class Chunk {
	
	protected int x, y;
	protected Collection<Entity> entities;
	
	public Chunk(int x, int y, Collection<Entity> collection) {
		this.x = x;
		this.y = y;
		this.entities = collection;
	}
	
	public Collection<Entity> entities() {
		return entities;
	}
	
	public Chunk immutable() {
		return new Chunk(x, y, Collections.unmodifiableCollection(entities));
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	

}
