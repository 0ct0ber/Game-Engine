package me.october.quickgame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Painter {
	
	private Component component;
	private Graphics g;
	private int width;
	private int height;
	
	public Painter(Component component, Graphics g, int width, int height) {
		this.component = component;
		this.g = g;
		this.width = width;
		this.height = height;
	}
	
	public Graphics getGraphics() {
		return g;
	}
	
	public void setFont(Font font) {
		g.setFont(font);
	}
	
	public void setColor(Color color) {
		g.setColor(color);
	}
	
	public void clearScreen() {
		g.clearRect(0, 0, width, height);
	}
	
	public void drawRect(double x, double y, double width, double height, Color color) {
		if (color != null) g.setColor(color);
		g.drawRect((int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height);
	}
	
	public void fillRect(double x, double y, double width, double height, Color color) {
		if (color != null) g.setColor(color);
		g.fillRect((int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height);
	}
	
	public void drawOval(double x, double y, double width, double height, Color color) {
		if (color != null) g.setColor(color);
		g.drawOval((int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height);
	}
	
	public void fillOval(double x, double y, double width, double height, Color color) {
		if (color != null) g.setColor(color);
		g.fillOval((int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height);
	}
	
	public void drawRoundRect(double x, double y, double width, double height, double arcWidth, double arcHeight, Color color) {
		if (color != null) g.setColor(color);
		g.drawRoundRect((int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height, (int)arcWidth, (int)arcHeight);
	}
	
	public void drawString(String string, double x, double y, Color color) {
		if (color != null) g.setColor(color);
		g.drawString(string, (int)x, this.height - (int)y);
	}
	
	public void drawLine(double x1, double x2, double y1, double y2, Color color) {
		if (color != null) g.setColor(color);
		g.drawLine((int)x1, this.height - (int)y1, (int)x2, this.height - (int)y2);
	}
	
	public void drawImage(double x, double y, double width, double height, Image image) {
		g.drawImage(image, (int)(x - width/2), this.height - (int)(y + height/2), (int)width, (int)height, null);
	}
	
	public Component getComponent() {
		return component;
	}

}
