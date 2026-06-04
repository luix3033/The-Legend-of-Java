package com.treszerotresstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
//import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.treszerotresstudios.entities.ArrowShoot;
import com.treszerotresstudios.entities.Entity;
import com.treszerotresstudios.entities.Goblin;
import com.treszerotresstudios.entities.Player;
import com.treszerotresstudios.graficos.Spritesheet;
import com.treszerotresstudios.graficos.UI;
import com.treszerotresstudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener,MouseListener/*,MouseMotionListener*/{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 240, HEIGHT = 160, SCALE = 3;
	
	private BufferedImage image;
	 
	
	public static List<Entity> entities;
	public static List<Goblin> goblins;
	public static List<ArrowShoot> arrowshoot;
	
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public static Player player;
	
	public static Random rand;
	
	public UI ui;
	
	public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelfont.ttf");
	public Font newfont;
	
	private int CUR_LEVEL = 1, MAX_LEVEL = 2;
	
	public int mx,my;
	
	
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private int framesGameOver= 0;
	
	private boolean restartGame = false;
	
	public boolean saveGame = false;
	
	public Menu menu;
	
	
	public Game() {
		
		Sound.musicBackground.setVolumePercent(70);
		Sound.gameoverMusic.setVolumePercent(70);
		Sound.menuBackground.setVolumePercent(70);

		Sound.hurtEffect.setVolumePercent(70);
		Sound.arrowThrow.setVolumePercent(70);

		Sound.menuChange.setVolumePercent(70);
		Sound.menuSelect.setVolumePercent(70);

		Sound.picBow.setVolumePercent(70);

		Sound.goblinHit.setVolumePercent(70);
		Sound.goblinDeath.setVolumePercent(70);

		Sound.lowLife.setVolumePercent(80);

		Sound.getLife.setVolumePercent(50);
		
		
		rand = new Random();
		
		//descomente para usar o motion track
		//addMouseMotionListener(this);
		
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		//iniciar objs
		
		ui = new UI();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		goblins = new ArrayList<Goblin>();
		arrowshoot = new ArrayList<ArrowShoot>();
		
		
		
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(292, 54, 16, 16));
		
		world = new World("/level1.png");
		
		try {
			newfont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(16f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		entities.add(player);
		menu = new Menu(0,0,16,16,spritesheet.getSprite(598, 478, 240, 160));
	}
	
	public void initFrame() {
		frame = new JFrame("The Legend of Java");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		this.requestFocus();
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning=true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(gameState.equals("NORMAL") ) {
			
			if(this.saveGame) {
				this.saveGame = false;
				String[] opt1 = {"level","vida"};
				int[] opt2 = {this.CUR_LEVEL,(int)player.life};
				menu.saveGame(opt1,opt2,10);
				System.out.println("sarvo");
			}
			Sound.menuBackground.stop();
			Sound.gameoverMusic.stop();
			Sound.musicBackground.loop();
			this.restartGame=false;
		
		for(int i = 0; i< entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		
		for(int i = 0; i < arrowshoot.size();i++) {
			arrowshoot.get(i).tick();
		}
		
		if(goblins.size() == 0) {
			CUR_LEVEL++;
			if(CUR_LEVEL > MAX_LEVEL) {
				CUR_LEVEL = 1;
			}
			String newWorld = "level"+CUR_LEVEL+".png";
			World.restartGame(newWorld);
			}
		} else if (gameState.equals("GAME_OVER")) {
			
			this.framesGameOver++;
			if(this.framesGameOver == 30) {
				this.framesGameOver = 0;
				if(this.showMessageGameOver) {
					this.showMessageGameOver = false;
				} else {
					this.showMessageGameOver = true;
				}
			}
			
			if(restartGame) {
				this.restartGame = false;
				Game.gameState = "NORMAL";
				CUR_LEVEL = 1;
				String newWorld = "level"+CUR_LEVEL+".png";
				World.restartGame(newWorld);
			}
		}else if(gameState == "MENU") {
			Sound.menuBackground.loop();
			menu.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs== null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
			
		
		//render do jogo lolllll
		
		//Graphics2D g2 = (Graphics2D) g;
		world.render(g);
		for(int i = 0; i< entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		for(int i = 0; i < arrowshoot.size();i++) {
			arrowshoot.get(i).render(g);
		}
		
		ui.render(g);
		/*********/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.setFont(new Font("arial",Font.BOLD,17));
		g.setColor(Color.white);
		
		g.setFont(newfont);
		g.drawString("FLECHAS: " + player.arrow, 23, 50);
		g.drawString((int)Game.player.life+"/"+(int)Game.player.maxLife, 90, 28);
		if(gameState.equals("GAME_OVER")) {
			
			System.out.println("oi");
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0,0,0,100));
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.setFont(new Font("arial",Font.BOLD,70));
			g.setColor(Color.white);
			g.drawString("GAME OVER ", (WIDTH*SCALE) / 2 - 200, (HEIGHT*SCALE) / 2);
			g.setFont(new Font("arial",Font.BOLD,20));
			if(showMessageGameOver)
			g.drawString(">Pressione ENTER para reiniciar<", (WIDTH*SCALE) / 2 - 150, (HEIGHT*SCALE) / 2 +100);
		} else if(gameState == "MENU") {
			menu.render(g);
		}
		
		
		//exemplo de rotação com base em mouse
		/*
		Graphics2D g2 = (Graphics2D) g;
		double angleMouse = Math.atan2(200+25-my , 200+25 -mx );
		g2.rotate(angleMouse,200+25,200+25);
		g.setColor(Color.red);
		g.fillRect(200, 200, 50, 50);
		
		*/
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer +=1000;
			}
		}
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_A|| e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
			player.up = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_X) {
			player.shoot = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.restartGame = true;
			if(gameState == "MENU") {
				menu.enter = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_A|| e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP) {
			player.up = false;
			if(gameState == "MENU") {
				menu.up = true;
			}
			
		}else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down = false;
			
			if(gameState == "MENU") {
				menu.down = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = "MENU";
			menu.pause = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_E) {
			if(gameState == "NORMAL"){
			this.saveGame = true;
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		player.mouseShoot = true;
		player.mx = (e.getX()/SCALE);
		player.my = (e.getY()/SCALE);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		
	}
	*/

}

