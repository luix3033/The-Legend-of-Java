package com.treszerotresstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.treszerotresstudios.graficos.Spritesheet;
import com.treszerotresstudios.main.Game;
import com.treszerotresstudios.world.Camera;
import com.treszerotresstudios.world.World;

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
	
	private BufferedImage playerDamageDown; 
	private BufferedImage playerDamageUp; 
	private BufferedImage playerDamageLeft; 
	private BufferedImage playerDamageRight; 
	
	public int arrow = 0;
	
	private boolean hasBow = false;
	
	public  double life = 100, maxLife = 100;
	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public boolean shoot = false,mouseShoot = false;
	
	public int mx,my;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		playerDamageDown = Game.spritesheet.getSprite(291, 71, 16, 16);
		playerDamageRight = Game.spritesheet.getSprite(291, 71+17, 16, 16);
		playerDamageLeft = Game.spritesheet.getSprite(291, 71+17+17, 16, 16);
		playerDamageUp = Game.spritesheet.getSprite(323, 71, 16, 16);
		
		
		
		
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
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}else if(left  && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}else if(down && World.isFree(this.getX(),(int)(y+speed))) {
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
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
		this.checkCollisionLifePack();
		this.checkCollisionArrow();
		this.checkCollisionBow();
		
		if(isDamaged) {
			this.damageFrames++;
			if(this.damageFrames==10) {
				this.damageFrames=0;
				isDamaged=false;
			}
		}
		
		if(shoot) {
			shoot = false;
			if(hasBow && arrow >0) {
				shoot = false;
				arrow--;
			shoot = false;
			int dx = 0;
			int dy = 0;

			int px = 0;
			int py = 0;

			if(dir == right_dir) {
			    dx = 1;
			    px = 6;
			    py = 5;
			}
			else if(dir == left_dir) {
			    dx = -1;
			    px = -6;
			    py = 5;
			}
			else if(dir == up_dir) {
			    dy = -1;
			    px = -3;
			    py = -2;
			}
			else if(dir == down_dir) {
			    dy = 1;
			    px = 1;
			    py = 4;
			}
			
			ArrowShoot arrowshoot =new ArrowShoot(this.getX() + px,this.getY() + py,16,16,null,dx,dy );

				Game.arrowshoot.add(arrowshoot);
			
		
			}
		}
		
		if(mouseShoot) {
			mouseShoot = false;
			
			if(hasBow && arrow >0) {
				shoot = false;
				arrow--;
				

			int px = 0;
			int py = 0;
			
			double angle = 0;
			
			if(dir == right_dir) {
				
			   
			    px = 6;
			    //py = 5;
			    angle = Math.atan2( my -(this.getY()+py - Camera.y), mx- (this.getX()+px - Camera.x) );
			}
			else if(dir == left_dir) {
			    
			    px = -6;
			    //py = 5;
			    angle = Math.atan2( my -(this.getY()+py - Camera.y), mx- (this.getX()+px - Camera.x) );
			}
			else if(dir == up_dir) {
			    
			    px = -3;
			   // py = -2;
			    angle = Math.atan2( my -(this.getY()+py - Camera.y), mx- (this.getX()+ px- Camera.x) );
			}
			else if(dir == down_dir) {
			    
			    px = 1;
			    //py = 4;
				angle = Math.atan2( my -(this.getY()+py - Camera.y), mx- (this.getX()+px - Camera.x) );

			}
			double dx = Math.cos(angle);
			double dy = Math.sin(angle);

			
			
			ArrowShoot arrowshoot =new ArrowShoot(this.getX() + px,this.getY() + py,16,16,null,dx,dy );

			Game.arrowshoot.add(arrowshoot);
		}
		
		
		
		}
		if(life <=0) {
			life = 0;
			Game.gameState = "GAME_OVER";
			//gameover
			
			} 
	}
	
	public void checkCollisionArrow() {
		for(int i = 0; i < Game.entities.size();i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Arrow) {
				if(Entity.isColliding(this, atual)) {
					arrow+=10;
					Game.entities.remove(atual);
				}
			}
;		}
	}
	
	public void checkCollisionLifePack() {		
		for(int i = 0; i < Game.entities.size();i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Lifepack) {
				if(Entity.isColliding(this, atual)) {
					life+=10;
					if(life >100)
						life = 100;
					Game.entities.remove(atual);
				}
			}
;		}
		
		
	}
	
	public void checkCollisionBow() {		
		for(int i = 0; i < Game.entities.size();i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon) {
				if(Entity.isColliding(this, atual)) {
					hasBow = true;
					Game.entities.remove(atual);
				}
			}
;		}
		
		
	}
	
	public void render(Graphics g) {
		if(!isDamaged) {
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		if(hasBow) {
			g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x+7, this.getY()-Camera.y+6,12,12, null);
		}
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			if(hasBow) {
				g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x-4, this.getY()-Camera.y+6,12,12, null);
			}
		}
		
		if(dir == up_dir) {
			if(hasBow) {
				g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x-3, this.getY()-Camera.y+6,12,12, null);
			}
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == down_dir) {
					g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(hasBow) {
					g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x+7, this.getY()-Camera.y+6,12,12, null);
				}
			}
			//fazer dano
		}else {
			if(dir == right_dir) {
				g.drawImage(playerDamageRight, this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(hasBow) {
					g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x+7, this.getY()-Camera.y+6,12,12, null);
				}
			}else if(dir == left_dir) {
					g.drawImage(playerDamageLeft, this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(hasBow) {
						g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x-4, this.getY()-Camera.y+6,12,12, null);
					}
			}
				
				if(dir == up_dir) {
					if(hasBow) {
						g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x-3, this.getY()-Camera.y+6,12,12, null);
					}
					g.drawImage(playerDamageUp, this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(dir == down_dir) {
						g.drawImage(playerDamageDown, this.getX()-Camera.x, this.getY()-Camera.y, null);
						if(hasBow) {
							g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x+7, this.getY()-Camera.y+6,12,12, null);
						}
					}
			
		}
		}
	

}
