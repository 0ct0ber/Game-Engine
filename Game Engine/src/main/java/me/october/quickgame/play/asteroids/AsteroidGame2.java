package me.october.quickgame.play.asteroids;

import me.october.quickgame.Game;
import me.october.quickgame.GameCenter;
import me.october.quickgame.Painter;
import me.october.quickgame.scene.Camera;
import me.october.quickgame.scene.GridScene;

public class AsteroidGame2 extends Game {
	
	public GridScene scene;
	public Camera camera = new Camera();

	public AsteroidGame2(int width, int height, String title, GameCenter center) {
		super(width, height, title, center);
		scene = new GridScene(width, height, Math.max(width/75, 1), Math.max(height/75, 1));
	}
	
	@Override
	public void setup() {
		
	}

	@Override
	public void onStart() {
		
	}
	
	@Override
	public void logic() {
		
	}

	@Override
	public void draw(Painter painter) {
		
	}

}
