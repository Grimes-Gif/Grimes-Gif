import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.Runnable;
import java.lang.Thread;
public class Panel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static int height = 720;
	public static int width = 1280;
	private Thread thread;
	private BufferedImage img;
	private Graphics2D g;
	private boolean running = false;
	private GameStateManager gsm;
	public MouseHandler mouse;
	public KeyHandler key;
	
	public Panel(int width , int height) {
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		
	}
	

	
	public void addNotify() {
		super.addNotify();
		
		if(thread == null) {
			thread = new Thread((Runnable)this, "GameThread");
			thread.start();
		}
	}
	
	public void runitGraphics() {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
	}
	
	public void runit() {
		running = true;
		
		runitGraphics();
		
		mouse = new MouseHandler(this);
		key = new KeyHandler(this);
		
		gsm = new GameStateManager(g);
		
	}
	
	public void run() {
		runit();
		final long millis = 1;
		final double GAME_HERTZ = 60.0; // number of times the screen updates per second
		final double TBU = 1000000000 / GAME_HERTZ; // time before update
		final int MUBR = 5; // Amount of /Updates before render
		
		double lastUpdateTime = System.nanoTime(); // takes current time 
		double lastRenderTime;
		
		final double TARGET_FPS = 60;
		final double TTBR = 1000000000 / TARGET_FPS; //Total time before render
		
		
		int framecount = 0;
		int lastSecondTime = (int)(lastUpdateTime / 1000000000);
		int oldFrameCount = 0;
		
		while(running) {
			double TimeNow = System.nanoTime();
			int UpdateCount = 0;
			while(((TimeNow - lastUpdateTime) > TBU) && (UpdateCount < MUBR)) {
				input(mouse, key);
				update();
				lastUpdateTime += TBU;
				UpdateCount++;
			}
			
			if(TimeNow - lastUpdateTime > TBU) {
				lastUpdateTime = TimeNow - TBU;
			}
			
			render();
			draw();
			input(mouse, key);
			lastRenderTime = TimeNow;
			framecount++;
			
			int thisSecondTime = (int) (lastUpdateTime/1000000000);
			if(thisSecondTime > lastSecondTime) {
				if(framecount != oldFrameCount) {
					System.out.println("New second: " + thisSecondTime + " " + framecount);
					oldFrameCount = framecount;
				}
				lastSecondTime = thisSecondTime;
				framecount = 0;
			}
			
			while(TimeNow - lastUpdateTime < TBU && TimeNow - lastRenderTime < TTBR) {
				Thread.yield();
				try {
					Thread.sleep(millis);
					System.out.println(millis);
				} catch(Exception e) { 
					System.out.println("ERROR: yeilding thread");
				}
				TimeNow = System.nanoTime();
			}	
		}			
	}

	public void update() {
		gsm.update();
	}
	public void render() {
		if(g != null) {
			g.setColor(new Color(35, 20, 135));
			g.fillRect(0, 0, width, height);
			gsm.render(g);
		}
	}
	public void draw() {
		Graphics g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();
		
		
	}
	public void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}
	
	
}
