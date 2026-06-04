package com.treszerotresstudios.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.treszerotresstudios.entities.Player;
import com.treszerotresstudios.main.Game;

public class UI {
	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect(8, 3, 70, 8);
		g.setColor(Color.green);
		g.fillRect(8, 3, (int)((Game.player.life/Game.player.maxLife)*70), 8);
		g.setColor(Color.white);
	
	}

}
