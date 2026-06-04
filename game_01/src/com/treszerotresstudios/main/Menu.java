package com.treszerotresstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.treszerotresstudios.world.World;

public class Menu {
	
	public String[] options = {"Novo Jogo","Carregar Jogo","Sair"};
	
	public int currentOption = 0;
	public int maxOption = options.length -1;
	public boolean up,down,enter;
	public static boolean pause = false, saveExists = false, saveGame=false;
	
	
	private BufferedImage menuBackground;
	
	public Menu(int x, int y, int width, int height, BufferedImage sprite) {
	    try {
	        menuBackground = ImageIO.read(getClass().getResource("/Menu.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void tick() {
		File file = new File("save.txt");
		if(file.exists()) {
			saveExists = true;
			
		}else {
			saveExists = false;
		}
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
				 file = new File("save.txt");
				file.delete();
				
			}  else if(options[currentOption] == "Carregar Jogo"){
				file = new File("save.txt");
				if(file.exists()) {
					String saver = loadGame(10);
					applySave(saver);
				}
			}
			
			else if(options[currentOption]== "Sair") {
				System.exit(1);
			}
		}
	}
	
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "level":
				World.restartGame("level"+spl2[1]+".png");
				Game.gameState= "NORMAL";
				pause = false;
				break;
			case "vida":
				Game.player.life = Integer.parseInt(spl2[1]);
				break;
			}
			
		}
	}
	
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader =new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode;
							trans[1]+=val[i];
						}
						line+=trans[0];
						line+=":";
						line+=trans[1];
						line+="/";
					}
				}catch(IOException e) {}
			} catch(FileNotFoundException e) {}
		}
		
		return line;
	}
	
	
	public static void saveGame(String[] val1,int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int n = 0; n< value.length;n++) {
				value[n]+=encode;
				current+=value[n];				
			}
			try {
				write.write(current);
				if(i < val1.length - 1)
					write.newLine();
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		} catch(IOException e) {
			
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
