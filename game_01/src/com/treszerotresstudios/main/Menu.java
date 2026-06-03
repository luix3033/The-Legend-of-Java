package com.treszerotresstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	
	public String[] options = {"Novo Jogo","Carregar Jogo","Sair"};
	
	public int currentOption = 0;
	public int maxOption = options.length -1;
	public boolean up,down,enter;
	public boolean pause = false;
	
	
	private BufferedImage menuBackground;
	
	public Menu(int x, int y, int width, int height, BufferedImage sprite) {
	    try {
	        menuBackground = ImageIO.read(getClass().getResource("/Menu.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void tick() {
		if(up) {
			up = false;
			currentOption--;
			Sound.menuChange.play();
			if(currentOption<0) {
				currentOption = maxOption;
			}
		}
		if(down) {
			down = false;
			Sound.menuChange.play();
			currentOption++;
			if(currentOption > maxOption) {
				currentOption = 0;
			}
		}
		if(enter) {
			enter = false;
			Sound.menuSelect.play();
			if(options[currentOption] == "Novo Jogo" || options[currentOption] == "Continuar") { 			
				Game.gameState = "NORMAL";
				pause = false;
				
			} else if(options[currentOption]== "Sair") {
				System.exit(1);
			}
		}
	}
	
	public void render(Graphics g) {
		if(pause == false) {
		g.drawImage(menuBackground, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
		} else {
			g.setColor(new Color(0,0,0,100));
			g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		}
		g.setColor(Color.LIGHT_GRAY);
		

		//opcoesss
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_OFF
		);
		g2.setColor(Color.white);
		g2.setFont(new Font("arial", Font.BOLD,24));
		if(pause == false) {
			g2.drawString("Novo Jogo", (Game.WIDTH*Game.SCALE) / 2 -60, (Game.HEIGHT*Game.SCALE) / 2 +30);
		}
		else {
			g2.drawString("Resumir", (Game.WIDTH*Game.SCALE) / 2 -50, (Game.HEIGHT*Game.SCALE) / 2 +30);
		}
		g2.drawString("Carregar Jogo ", (Game.WIDTH*Game.SCALE) / 2-80, (Game.HEIGHT*Game.SCALE) / 2+60);
		g2.drawString("Sair ", (Game.WIDTH*Game.SCALE) / 2-20, (Game.HEIGHT*Game.SCALE) / 2+90);
		
		if(options[currentOption] == "Novo Jogo") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2-80, (Game.HEIGHT*Game.SCALE) / 2 +30);
			
		} else if(options[currentOption]=="Carregar Jogo") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2-100, (Game.HEIGHT*Game.SCALE) / 2 +60);
		}
		else if(options[currentOption]=="Sair") {
			g.drawString(">", (Game.WIDTH*Game.SCALE) / 2-40, (Game.HEIGHT*Game.SCALE) / 2 +90);
		}
	
	}
}
