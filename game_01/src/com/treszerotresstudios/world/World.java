package com.treszerotresstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.treszerotresstudios.entities.Arrow;
import com.treszerotresstudios.entities.Entity;
import com.treszerotresstudios.entities.Goblin;
import com.treszerotresstudios.entities.Lifepack;
import com.treszerotresstudios.entities.Weapon;
import com.treszerotresstudios.main.Game;

public class World {
	
	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
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
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_ROCK);
						//PAREDE
					} else if(pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
						//player
					} else if(pixelAtual == 0xFFFF0000) {
						//goblin
							Game.entities.add(new Goblin(xx*16,yy*16, 16,16, Entity.GOBLIN_EN));			
					} else if(pixelAtual == 0xFFFF3700) {
						//weapon
						Game.entities.add(new Weapon(xx*16,yy*16, 16,16, Entity.WEAPON_EN));
					} else if(pixelAtual == 0xFF00FF21) {
						//lifepack
						Game.entities.add(new Lifepack(xx*16,yy*16, 16,16, Entity.LIFEPACK_EN));
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
	
	public void render(Graphics g) {
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
		
	}
}
