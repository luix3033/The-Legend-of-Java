package com.treszerotresstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.treszerotresstudios.main.Game;

public class Player extends Entity {
	
	
	public boolean right,up,left,down;
	public int right_dir = 0,left_dir = 1, up_dir = 2, down_dir=3;
	public int dir = down_dir;
	public double speed =  1.4;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex=3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		
		for(int i =0;i < 4; i++) {
		try {
			rightPlayer[0] = Game.spritesheet.getSprite(308, 54, 16, 16);
			rightPlayer[1] = Game.spritesheet.getSprite(356, 54, 16, 16);
			rightPlayer[2] = Game.spritesheet.getSprite(308, 54, 16, 16);
			rightPlayer[3] = Game.spritesheet.getSprite(404, 54, 16, 16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		for(int i =0;i < 4; i++) {
			try {
				leftPlayer[0] = Game.spritesheet.getSprite(404, 71, 16, 16);
				leftPlayer[1] = Game.spritesheet.getSprite(356, 71, 16, 16);
				leftPlayer[2] = Game.spritesheet.getSprite(404, 71, 16, 16);
				leftPlayer[3] = Game.spritesheet.getSprite(308, 71, 16, 16);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		for(int i =0;i < 4; i++) {
			try {
				upPlayer[0] = Game.spritesheet.getSprite(323, 54, 16, 16);
				upPlayer[1] = Game.spritesheet.getSprite(372, 54, 16, 16);
				upPlayer[2] = Game.spritesheet.getSprite(323, 54, 16, 16);
				upPlayer[3] = Game.spritesheet.getSprite(420, 54, 16, 16);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0;i < 4; i++) {
			try {
				downPlayer[0] = Game.spritesheet.getSprite(291, 54, 16, 16);
				downPlayer[1] = Game.spritesheet.getSprite(340, 54, 16, 16);
				downPlayer[2] = Game.spritesheet.getSprite(291, 54, 16, 16);
				downPlayer[3] = Game.spritesheet.getSprite(387, 54, 16, 16);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}else if(left) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}else if(down) {
			moved = true;
			dir = down_dir;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		}
		
		if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
			}else if(dir == down_dir) {
				g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
			}
			
	}
	

}
