package com.treszerotresstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.treszerotresstudios.main.Game;
import com.treszerotresstudios.main.Sound;
import com.treszerotresstudios.world.AStar;
import com.treszerotresstudios.world.Camera;
import com.treszerotresstudios.world.Vector2i;
import com.treszerotresstudios.world.World;

public class Goblin extends Entity{
	
	private double speed = 0.6;
	
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex=3;
	
	private int right_dir = 0,left_dir = 1, up_dir = 2, down_dir=3;
	private int dir = down_dir;
	
	private BufferedImage[] downGoblin;
	private BufferedImage[] rightGoblin;
	private BufferedImage[] leftGoblin;
	private BufferedImage[] upGoblin;
	
	private int life = 10;
	
	private boolean isDamaged = false;
	private int damagedFrames = 10, damageCurrent = 0;

	public Goblin(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		downGoblin = new BufferedImage[4];
		rightGoblin = new BufferedImage[4];
		leftGoblin = new BufferedImage[4];
		upGoblin = new BufferedImage[4];
		
		

		for(int i =0;i < 4; i++) {
		try {
			rightGoblin[0] = Game.spritesheet.getSprite(639, 269, 16, 16);
			rightGoblin[1] = Game.spritesheet.getSprite(639+16, 269, 16, 16);
			rightGoblin[2] = Game.spritesheet.getSprite(639, 269, 16, 16);
			rightGoblin[3] = Game.spritesheet.getSprite(639+16+16, 269, 16, 16);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		for(int i =0;i < 4; i++) {
			try {
				leftGoblin[0] = Game.spritesheet.getSprite(639, 301, 16, 16);
				leftGoblin[1] = Game.spritesheet.getSprite(639+16, 301, 16, 16);
				leftGoblin[2] = Game.spritesheet.getSprite(639, 301, 16, 16);
				leftGoblin[3] = Game.spritesheet.getSprite(639+16+16, 301, 16, 16);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		for(int i =0;i < 4; i++) {
			try {
				upGoblin[0] = Game.spritesheet.getSprite(639, 237, 16, 16);
				upGoblin[1] = Game.spritesheet.getSprite(639+16, 237, 16, 16);
				upGoblin[2] = Game.spritesheet.getSprite(639, 237, 16, 16);
				upGoblin[3] = Game.spritesheet.getSprite(639+16+16, 237, 16, 16);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i =0;i < 4; i++) {
			try {
				downGoblin[0] = Game.spritesheet.getSprite(639, 205, 16, 16);
				downGoblin[1] = Game.spritesheet.getSprite(655, 205, 16, 16);
				downGoblin[2] = Game.spritesheet.getSprite(639, 205, 16, 16);
				downGoblin[3] = Game.spritesheet.getSprite(671, 205, 16, 16);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void tick() {
		depth = 0;
		
		maskx = 5;
		masky = 5;
		maskw = 8;
		maskh = 8;
		
		
		if(!isCollidingWithPlayer()) {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
				Vector2i end = new Vector2i((int)(Game.player.x/16),(int)(Game.player.y/16));
				path = AStar.findPath(Game.world, start, end);
			}
		}else {
			if(new Random().nextInt(100) < 5) {
				Sound.hurtEffect.play();
				Game.player.life-=Game.rand.nextInt(3);
				Game.player.isDamaged = true;
			}
		}
		
		/*
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 100) {
		if(this.isCollidingWithPlayer()==false) {
		if((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY(), z) &&!isColliding((int)(x+speed), this.getY())) {
				    x += speed;
				    dir = right_dir;
				}
				else if((int)x > Game.player.getX() &&
				        World.isFree((int)(x-speed), this.getY(),z) &&
				        !isColliding((int)(x-speed), this.getY())) {
				    x -= speed;
				    dir = left_dir;
				}

				if((int)y < Game.player.getY() &&  World.isFree(this.getX(), (int)(y+speed),z) &&  !isColliding(this.getX(), (int)(y+speed))) {
				    y += speed;
				    dir = down_dir;
				}
				else if((int)y > Game.player.getY() &&  World.isFree(this.getX(), (int)(y-speed),z) &&!isColliding(this.getX(), (int)(y-speed))) {
				    y -= speed;
				    dir = up_dir;
				}
		} else {
			//estamos colidindo
			if(Game.rand.nextInt(100)<10) {
				Sound.hurtEffect.play();
			Game.player.life-=Game.rand.nextInt(3);
			Game.player.isDamaged = true;
			
			
			//System.out.println("vida: "+Game.player.life);
			}
			
		}
	}
	*/
		if(new Random().nextInt(100) < 90) {
			followPath(path);
		}
		
		if(new Random().nextInt(100) < 5) {
			Vector2i start = new Vector2i((int)(x/16),(int)(y/16));
			Vector2i end = new Vector2i((int)(Game.player.x/16),(int)(Game.player.y/16));
			path = AStar.findPath(Game.world, start, end);
		}
					frames++;
					if(frames == maxFrames) {
						frames = 0;
						index++;
						if(index > maxIndex) {
							index = 0;
						}
					}
		collidingArrow();
		
		if(life <=0) {
			Sound.goblinDeath.play();
			destroySelf();
			return;
		}
		
		if(isDamaged) {
			this.damageCurrent++;
			if(this.damageCurrent == this.damagedFrames) {
				this.damageCurrent = 0;
				this.isDamaged = false;
			}
		}
	}
	
	public void destroySelf() {
		Game.goblins.remove(this);
		Game.entities.remove(this);
	}
	
	public void collidingArrow() {

	    for(int i = 0; i < Game.arrowshoot.size(); i++) {
	    	Entity e = Game.arrowshoot.get(i);
	    	if(e instanceof ArrowShoot) {
	    		if(Entity.isColliding(this, e)) {
	    			isDamaged = true;;
	    			life--;
	    			Sound.goblinHit.play();
	    			Game.arrowshoot.remove(i);
	    			return;
	    		}
	    	}
	    }
	    
	}
	
	public boolean isCollidingWithPlayer() {
		Rectangle goblinCurrent = new Rectangle( this.getX() + maskx,this.getY() + masky,maskw,maskh );
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return goblinCurrent.intersects(player);
	}
	
	
	
	public void render(Graphics g) {
		if(!isDamaged) {
		if(dir == right_dir) {
			g.drawImage(rightGoblin[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}else if(dir == left_dir) {
				g.drawImage(leftGoblin[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			
			if(dir == up_dir) {
				g.drawImage(upGoblin[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}else if(dir == down_dir) {
					g.drawImage(downGoblin[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
		
		}else {
			g.drawImage(Entity.GOBLIN_FEED, this.getX() - Camera.x, this.getY() - Camera.y, null);
			
		}
	
	}}
