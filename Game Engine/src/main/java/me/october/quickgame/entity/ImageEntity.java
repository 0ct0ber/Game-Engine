package me.october.quickgame.entity;

import java.awt.Image;

import me.october.quickgame.Painter;

public abstract class ImageEntity extends BaseEntity {

	protected Image image;
	public ImageEntity(String name, double width, double height, Image image) {
		super(name, width, height);
		this.image = image;
	}
	
	@Override
	public void draw(Painter painter) {
		painter.drawImage(this.position.getX(), this.position.getY(), width, height, image);
	}
	
	public boolean isVisible() {
		return true;
	}

}
