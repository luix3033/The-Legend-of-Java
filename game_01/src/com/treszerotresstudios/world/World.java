package com.treszerotresstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.treszerotresstudios.entities.Arrow;
import com.treszerotresstudios.entities.Entity;
import com.treszerotresstudios.entities.Goblin;
import com.treszerotresstudios.entities.Lifepack;
import com.treszerotresstudios.entities.Player;
import com.treszerotresstudios.entities.Weapon;
import com.treszerotresstudios.graficos.Spritesheet;
import com.treszerotresstudios.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()* map.getHeight()];
			WIDTH= map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth()* map.getHeight()];
			map.getRGB(0, 0,map.getWidth(),map.getHeight(),pixels,0,map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight();yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					if(pixelAtual == 0xFF000000) {
						//CHAOOOOOOO
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					} else if (pixelAtual == 0xFFFFFFFF) {
						tiles[xx+(yy*WIDTH)] = new RockTile(xx*16,yy*16,Tile.TILE_ROCK);
						//PAREDE
					} else if(pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
						//player
					} else if(pixelAtual == 0xFFFF0000) {
						//goblin
							Goblin gobl = new Goblin(xx*16,yy*16, 16,16, Entity.GOBLIN_EN);
							Game.entities.add(gobl);
							Game.goblins.add(gobl);
							
					} else if(pixelAtual == 0xFFFF3700) {
						//weapon
						Game.entities.add(new Weapon(xx*16,yy*16, 16,16, Entity.WEAPON_EN));
					} else if(pixelAtual == 0xFF00FF21) {
						//lifepack
						Lifepack pack = new Lifepack(xx*16,yy*16, 16,16, Entity.LIFEPACK_EN);
						Game.entities.add(pack);
					} else if(pixelAtual == 0xFFFFD800) {
						//arrow
						Game.entities.add(new Arrow(xx*16,yy*16, 16,16, Entity.ARROW_EN));
					}
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof RockTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof RockTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof RockTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof RockTile));
	}
	
	public static void restartGame(String level) {
		Game.entities = new ArrayList<Entity>();
		Game.goblins = new ArrayList<Goblin>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(292, 54, 16, 16));
		Game.entities.add(Game.player);
		Game.world = new World("/"+level);
		return;
	}
	
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH / 16);
		int yfinal = ystart + (Game.HEIGHT / 16);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
		
	}
}
