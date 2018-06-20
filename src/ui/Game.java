package ui;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	private String title;
	private int width,height;
	private BufferStrategy bs;
	private Graphics g;
	private BufferedImage test;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void init() {
		this.display = new Display(title,width,height);
		test = ImageLoader.loadImage("/textures/astronaut_test.png");
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0,0,width,height);
		//drawing start
		
		g.drawImage(test, 20, 20, null);
		
		//drawing  end
		bs.show();
		g.dispose();
	}
	

	
	public void run() {
		init();
		while(running) {
			tick();
			render();
		}
	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this); 
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
