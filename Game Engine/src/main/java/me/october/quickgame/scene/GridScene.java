package me.october.quickgame.scene;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import me.october.quickgame.Entity;
import me.october.quickgame.physics.Position;
import me.october.quickgame.physics.shape.Boundary;
import me.october.quickgame.physics.shape.Bounds;
import me.october.quickgame.physics.shape.Box;
import me.october.quickgame.physics.shape.Shape;

public class GridScene {
	
	private Cell[][] cells;
	
	private int cellsX, cellsY;
	private double cellWidth, cellHeight;
	private double width, height;
	
	private Bounds bounds;
	
	private ConcurrentHashMap<Entity, Collection<Cell>> track = new ConcurrentHashMap<>();
	
	public GridScene(double width, double height, int cellsX, int cellsY) {
		this.width = width;
		this.height = height;
		this.cellsX = cellsX;
		this.cellsY = cellsY;
		this.cellWidth = width/cellsX;
		this.cellHeight = height/cellsY;
		this.cells = new Cell[cellsX][cellsY];
		this.bounds = new Bounds(width, height);
	}
	
	public Collection<Entity> getAllEntities() {
		return Collections.unmodifiableCollection(track.keySet());
	}
	
	public boolean move(Entity entity) {
		Shape future = entity.getShape().offset(entity.getMotion());
		if (entity.isCollidable()) {
			Collection<Entity> entities = getEntities(entity.getShape().getBoundingBox());
			for (Entity ent : entities) {
				if (ent == entity) continue;
				if (!ent.isCollidable()) continue;
				if (!entity.canCollide(ent) || !ent.canCollide(entity)) continue;
				if ((entity.implementsCollision() && entity.collides(ent)) ||
					(ent.implementsCollision() && ent.collides(entity)) ||
					(future.intersects(ent.getShape()))) {
					entity.onCollide(ent, true);
					ent.onCollide(entity, false);
					return false;
				}
			}
		}
		
		if (entity.getScene() != this) return false;
		Boundary boundary = checkBoundary(entity.getShape().offset(entity.getMotion()));
		if (boundary != null) {
			entity.onBoundary(boundary);
			return false;
		}
		if (entity.getScene() != this) return false;
		entity.setPosition(entity.getPosition().add(entity.getMotion()));
		update(entity);
		return true;
	}
	
	public Boundary checkBoundary(Shape shape) {
		return bounds.checkBoundary(shape);
	}
	
	public Shape adjustBounds(Shape shape) {
		return bounds.adjustBounds(shape);
	}
	
	public boolean add(Entity entity, double posX, double posY) {
		if (track.containsKey(entity)) return false;
		entity.setPosition(new Position(posX, posY));
		entity.setScene(this);
		update(entity);
		return true;
	}
	
	public boolean remove(Entity entity) {
		Collection<Cell> entityCells = track.remove(entity);
		if (entityCells == null) return false;
		for (Cell cell : entityCells) {
			removeFromCell(entity, cell);
		}
		return true;
	}
	
	public void update(Entity entity) {
		Collection<Cell> entityCells = track.get(entity);
		if (entityCells == null) {
			entityCells = new HashSet<>(2);
			track.put(entity, entityCells);
		}
		Box box = entity.getShape().getBoundingBox();
		Collection<Cell> newCells = getCells(box, true);
		Iterator<Cell> iterator = entityCells.iterator();
		while (iterator.hasNext()) {
			Cell next = iterator.next();
			if (newCells.remove(next)) continue;
			else {
				removeFromCell(entity, next);
				iterator.remove();
			}
		}
		for (Cell newCell : newCells) {
			entityCells.add(newCell);
			newCell.entities.add(entity);
		}
	}
	
	private int w(double x) {
		int intX = (int)Math.floor(x/cellWidth);
		if (intX < 0) intX = 0;
		if (intX >= cellsX) intX = cellsX - 1;
		return intX;
	}
	
	private int h(double y) {
		int intY = (int)Math.floor(y/cellHeight);
		if (intY < 0) intY = 0;
		if (intY >= cellsY) intY = cellsY - 1;
		return intY;
	}
	
	public Collection<Cell> getCells(Box area, boolean create) {
		Collection<Cell> collection;
		int minX = w(area.minX());
		int minY = h(area.minY());
		int maxX = w(area.maxX());
		int maxY = h(area.maxY());
		int size = (1 + maxX - minX) * (1 + maxY - minY);
		collection = new HashSet<>(size);
		for (int x = minX; x <= maxX; x++) {
		for (int y = minY; y <= maxY; y++) {
			Cell cell = cells[x][y];
			if (cell == null) {
				if (create) {
					cell = new Cell(x, y);
					cells[x][y] = cell;
				} else {
					continue;
				}
			}
			
			collection.add(cell);
		}
		}
		return collection;
	}
	
	public Collection<Entity> getEntities(Box area) {
		Collection<Cell> cells = getCells(area, false);
		HashSet<Entity> entities = new HashSet<>(cells.size());
		for (Cell cell : cells) {
			for (Entity entity : cell.entities) {
				if (entity.getScene() != this) System.out.println("Old unremoved entity: " + entity);
			}
			entities.addAll(cell.entities);
		}
		return entities;
	}
	
	private void removeFromCell(Entity entity, Cell cell) {
		cell.entities.remove(entity);
		if (cell.entities.isEmpty()) {
			cells[cell.getX()][cell.getY()] = null;
		}
	}
	
	public double width() {
		return width;
	}
	
	public double height() {
		return height;
	}

}
