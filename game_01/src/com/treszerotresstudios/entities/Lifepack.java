package com.treszerotresstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;
import com.treszerotresstudios.world.Camera;

public class Lifepack extends Entity {
	private int maskx =8, masky = 8, maskw = 10, maskh = 10;
	private int frames = 0, maxFrames = 8, index = 0, maxIndex=7;
	
	private BufferedImage[] lifeAnima;

	public Lifepack(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		lifeAnima = new BufferedImage[8];
		
		for(int i =0;i < 4; i++) {
			try {
				lifeAnima[0] = Game.spritesheet.getSprite(447, 15, 16, 16);
				lifeAnima[1] = Game.spritesheet.getSprite(447+16, 15, 16, 16);
				lifeAnima[2] = Game.spritesheet.getSprite(447+32, 15, 16, 16);
				lifeAnima[3] = Game.spritesheet.getSprite(447+48, 15, 16, 16);
				lifeAnima[4] = Game.spritesheet.getSprite(447+64, 15, 16, 16);
				lifeAnima[5] = Game.spritesheet.getSprite(447+80, 15, 16, 16);
				lifeAnima[6] = Game.spritesheet.getSprite(447+96, 15, 16, 16);
				lifeAnima[7] = Game.spritesheet.getSprite(447+112, 15, 16, 16);
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
	}
	
	public void tick() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(lifeAnima[index], this.getX() - Camera.x,this.getY()- Camera.y,null);
	}

}
