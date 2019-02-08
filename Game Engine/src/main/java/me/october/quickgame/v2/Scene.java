package me.october.quickgame.v2;

import java.util.Collection;
import me.october.quickgame.physics.Position;
import me.october.quickgame.physics.shape.BBox;
import me.october.quickgame.physics.shape.Box;

public interface Scene {
	
	public Collection<GameObject> visible();
	public Collection<GameObject> visible(Box area);
	
	public Chunk getChunk(int x, int y);
	
	public Collection<Entity> getEntitiesInArea(Box area);
	
	public default Collection<Entity> getEntitiesInArea(Position center, double width, double height) {
		return getEntitiesInArea(new BBox(center, width, height));
	}
	
	public void add(Entity entity);
	
	public void remove(Entity entity);
}