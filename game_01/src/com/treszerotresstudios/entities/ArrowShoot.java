package com.treszerotresstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;
import com.treszerotresstudios.world.Camera;

public class ArrowShoot extends Entity {
	
	private double dx;
	private double dy;
	private double spd = 4;
	
	
	private int life = 40, curLife = 0;
	
	public BufferedImage arrowRight,arrowLeft,arrowUp,arrowDown;
	
	public ArrowShoot(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx= dx;
		this.dy=dy;
		arrowRight = Game.spritesheet.getSprite(432+32, 111, 16, 16);
		arrowLeft = Game.spritesheet.getSprite(432, 111, 16, 16);
		arrowUp = Game.spritesheet.getSprite(432+16, 111, 16, 16);
		arrowDown = Game.spritesheet.getSprite(432-16, 111, 16, 16);
		// TODO Auto-generated constructor stub
	}

	
	public void tick() {
		x+=dx*spd;
		y+=dy*spd;
		curLife++;
		if(curLife == life) {
			Game.arrowshoot.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {

	    BufferedImage sprite;

	    if(Math.abs(dx) > Math.abs(dy)) {

	        if(dx > 0)
	            sprite = arrowRight;
	        else
	            sprite = arrowLeft;

	    } else {

	        if(dy > 0)
	            sprite = arrowDown;
	        else
	            sprite = arrowUp;

	    }

	    g.drawImage(sprite,
	        this.getX() - Camera.x,
	        this.getY() - Camera.y,
	        null);
	}
}
