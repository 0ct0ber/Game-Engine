package me.october.quickgame.play.asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SpaceController implements KeyListener {
	
	private volatile boolean left, right, spacebar;
	public static volatile boolean k_key;
	
	public boolean left() {
		return left;
	}
	
	public boolean right() {
		return right;
	}
	
	public boolean spacebar() {
		return spacebar;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37: left = true; return;
		case 39: right = true; return;
		case 32: spacebar = true; return;
		case 75: k_key = true; return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37: left = false; return;
		case 39: right = false; return;
		case 32: spacebar = false; return;
		case 75: k_key = false; return;
		}
	}

}
