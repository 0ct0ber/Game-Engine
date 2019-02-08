package me.october.quickgame.test;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestController implements KeyListener {
	
	public static void main(String[] args) {
		Frame frame = new Frame("Key Tester");
		frame.setSize(200, 200);
		frame.setResizable(false);
		frame.addKeyListener(new TestController());
		frame.setVisible(true);
		frame.requestFocus();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + "(" + e.getKeyCode() + ")");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released: " + "(" + e.getKeyCode() + ")");
	}

}
