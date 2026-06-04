package com.treszerotresstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_FLOOR3 = Game.spritesheet.getSprite(32, 0, 16, 16);
	public static BufferedImage TILE_GRASS1 = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_GRASS2 = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_GRASS3 = Game.spritesheet.getSprite(32, 16, 16, 16);
	public static BufferedImage TILE_ROCK = Game.spritesheet.getSprite(32, 32, 16, 16);
	
	public static BufferedImage DIRT_HORIZ = Game.spritesheet.getSprite(128, 144, 16, 16);
	public static BufferedImage DIRT_VERT = Game.spritesheet.getSprite(112, 160, 16, 16);
	public static BufferedImage DIRT_UP_LEFT = Game.spritesheet.getSprite(128, 160, 16, 16);
	public static BufferedImage DIRT_UP_RIGHT = Game.spritesheet.getSprite(96, 160, 16, 16);
	public static BufferedImage DIRT_DOWN_RIGHT = Game.spritesheet.getSprite(96, 128, 16, 16);
	public static BufferedImage DIRT_DOWN_LEFT = Game.spritesheet.getSprite(128, 128, 16, 16);
	
	public static BufferedImage RIVERSIDE = Game.spritesheet.getSprite(64, 96, 16, 16);
	public static BufferedImage RIVERSIDE_CORNER_LEFT = Game.spritesheet.getSprite(80, 96, 16, 16);
	public static BufferedImage RIVERSIDE_CORNER_RIGHT = Game.spritesheet.getSprite(48, 96, 16, 16);
	public static BufferedImage RIVERSIDE_RIGHT = Game.spritesheet.getSprite(80, 112, 16, 16);
	public static BufferedImage RIVERSIDE_LEFT = Game.spritesheet.getSprite(48, 112, 16, 16);
	public static BufferedImage RIVERSIDE_UP = Game.spritesheet.getSprite(64, 128, 16, 16);
	public static BufferedImage RIVERSIDE_CORNER_RIGHT_UP = Game.spritesheet.getSprite(48, 128, 16, 16);
	public static BufferedImage RIVERSIDE_CORNER_LEFT_UP = Game.spritesheet.getSprite(80, 128, 16, 16);
	public static BufferedImage RIVERSIDE_ANGLE_LEFT = Game.spritesheet.getSprite(160, 160, 16, 16);
	public static BufferedImage RIVERSIDE_ANGLE_RIGHT = Game.spritesheet.getSprite(176, 160, 16, 16);
	
	public static BufferedImage WATTER = Game.spritesheet.getSprite(64, 112, 16, 16);
	
	public static BufferedImage BRIDGE_UP = Game.spritesheet.getSprite(64, 32, 16, 16);
	public static BufferedImage BRIDGE = Game.spritesheet.getSprite(64, 32+16, 16, 16);
	public static BufferedImage BRIDGE_DOWN = Game.spritesheet.getSprite(64, 32+16+16, 16, 16);
	
	
	
	
	
	

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
