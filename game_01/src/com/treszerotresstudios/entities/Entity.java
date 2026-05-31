package com.treszerotresstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(448, 15, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(448, 31, 16, 16);
	public static BufferedImage ARROW_EN = Game.spritesheet.getSprite(448, 47, 16, 16);
	public static BufferedImage GOBLIN_EN = Game.spritesheet.getSprite(639, 205, 16, 17);
	
	protected double x,y;
	protected int width,height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}

	public int getY(){
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick(){
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(), null);
		
	}
}
