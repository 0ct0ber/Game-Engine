package me.october.quickgame.util;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.october.quickgame.physics.Position;

public class Mouse implements MouseListener, MouseMotionListener, FocusListener {

	
	private volatile boolean down = false;
	
	private boolean focus = false;
	
	private volatile int x, y;
	
	private int height;
	
	public Mouse(int height) {
		this.height = height;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		down = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		down = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		down = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = height - e.getY();
	}
	
	public void listen(final Component component) {
		final Mouse mouse = this;
		
		new Thread() {
			public void run() {
				component.addMouseListener(mouse);
				component.addMouseMotionListener(mouse);
				component.addFocusListener(mouse);
			}
		}.start();
	}
	
	public void setLoseFocus(boolean focus) {
		this.focus = focus;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (!focus) return;
		
		down = false;
	}

	
	public Position getPosition() {
		return new Position(x, y);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean down() {
		return down;
	}
}
