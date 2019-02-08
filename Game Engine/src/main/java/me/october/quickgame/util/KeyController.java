package me.october.quickgame.util;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentHashMap;

public class KeyController implements KeyListener, FocusListener {
	
	private volatile boolean left, right, up, down, spacebar, w, a, s, d;
	
	private boolean focus = false;
	
	private ConcurrentHashMap<Integer, Boolean> map = new ConcurrentHashMap<>();
	
	public KeyController() {}
	public KeyController(boolean focus) {
		this.focus = focus;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case 37: left = true; break;
		case 38: up = true; break;
		case 39: right = true; break;
		case 40: down = true; break;
		case 32: spacebar = true; break;
		case 87: w = true; break;
		case 65: a = true; break;
		case 83: s = true; break;
		case 68: d = true; break;
		}
		map.put(event.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case 37: left = false; break;
		case 38: up = false; break;
		case 39: right = false; break;
		case 40: down = false; break;
		case 32: spacebar = false; break;
		case 87: w = false; break;
		case 65: a = false; break;
		case 83: s = false; break;
		case 68: d = false; break;
		}
		map.remove(event.getKeyCode());
	}
	
	public boolean keyDown(int id) {
		return map.containsKey(id);
	}
	
	public boolean left() {
		return left;
	}
	
	public boolean right() {
		return right;
	}
	
	public boolean up() {
		return up;
	}
	
	public boolean down() {
		return down;
	}
	
	public boolean w() {
		return w;
	}
	
	public boolean a() {
		return a;
	}
	
	public boolean s() {
		return s;
	}
	
	public boolean d() {
		return d;
	}
	
	public boolean spacebar() {
		return spacebar;
	}
	
	public void listen(final Component component) {
		final KeyController controller = this;
		
		new Thread() {
			public void run() {
				component.addKeyListener(controller);
				component.addFocusListener(controller);
			}
		}.start();
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (!focus) return;
		map.clear();
		left = false;
		right = false;
		up = false;
		down = false;
		spacebar = false;
		w = false;
		a = false;
		s = false;
		d = false;
	}
	
	public void setLoseFocus(boolean focus) {
		this.focus = focus;
	}

}
