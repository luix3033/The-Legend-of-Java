package com.treszerotresstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;
import com.treszerotresstudios.world.Camera;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(448, 15, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(448, 31, 16, 16);
	public static BufferedImage ARROW_EN = Game.spritesheet.getSprite(448, 47, 16, 16);
	public static BufferedImage GOBLIN_EN = Game.spritesheet.getSprite(639, 205, 16, 17);
	public static BufferedImage GOBLIN_FEED = Game.spritesheet.getSprite(640, 334, 16, 16);
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(448, 63, 16, 16);
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(467, 63, 16, 16);
	
	
	protected double x,y;
	protected int width,height;
	protected int z;
	
	private BufferedImage sprite;
	
	public int maskx,masky,maskw,maskh;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.maskw = width;
		this.maskh = height;
	}
	
	public void setMask(int maskx,int masky, int maskw, int maskh) {
		this.maskx = maskx;
		this.masky = masky;
		this.maskw = maskw;
		this.maskh = maskh;
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
	
	public static boolean isColliding(Entity e1, Entity e2) {
	    Rectangle e1Mask = new Rectangle(
	        e1.getX() + e1.maskx,
	        e1.getY() + e1.masky,
	        e1.maskw,
	        e1.maskh
	    );

	    Rectangle e2Mask = new Rectangle(
	        e2.getX() + e2.maskx,
	        e2.getY() + e2.masky,
	        e2.maskw,
	        e2.maskh
	    );
	    if(e1Mask.intersects(e2Mask) && e1.z==e2.z) {
	    	return true;
	    }
	    return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y, null);
		
	}
}
