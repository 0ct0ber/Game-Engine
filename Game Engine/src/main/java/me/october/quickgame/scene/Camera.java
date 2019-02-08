package me.october.quickgame.scene;

import me.october.quickgame.Entity;
import me.october.quickgame.Painter;

public class Camera {
	
	public void snap(Painter painter, GridScene scene) {
		painter.clearScreen();
		for (Entity entity : scene.getAllEntities()) {
			if (!entity.isVisible()) continue;
			entity.draw(painter);
		}
	}

}
