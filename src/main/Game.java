package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

import entities.EntityManager;
import entities.Player;
import input.KeyManager;
import input.MouseManager;
import Graphics.Assets;
import Graphics.GameCamera;
import state.EpilogueState;
import state.GameState;
import state.MenuState;
import state.State;
/**
 * The game class will handle all of this project's runnable classes.
 * Therefore this class is the only Runnable class in the project.
 * It uses Threads a bufferStrategy as well as the keyManager.
 * @author karel
 * @version 1.0
 * @Since 2018-10-17
 */
public class Game implements Runnable {
	
	private Display display;
	private Thread thread;
	private boolean running = false;
	private String title;
	private int width,height;
	private BufferStrategy bs;
	private Graphics2D g2d;
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	//Camera
	private GameCamera gameCamera;

	//States
	private GameState gameState;
	private MenuState menuState;
	private EpilogueState epilogueState;

	//Handler
    private Handler handler;
    private EntityManager entityManager;
    private Logger logger;
	/**
	 * The contructor of the game class, on calling the contructor a keyManager will be made.
	 * @param title the title that is given to the game
	 * @param width the width of the game's display
	 * @param height the height of the game's display
	 */
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.keyManager = new KeyManager();
		this.mouseManager = new MouseManager();
	}
	/**
	 * the init method will be runned onced, this method is the first noncontructor method to be called in the class (after the start method).
	 * here, the Game will create a display with the current keyManager.
	 * initialize the assets of the Assets class and create all the GameStates.
	 */
	public void init() {

		this.display = new Display(title,width,height);
		this.display.getFrame().addKeyListener(keyManager);
		this.display.getFrame().addMouseMotionListener(mouseManager);
		this.display.getFrame().addMouseListener(mouseManager);
		this.display.getCanvas().addMouseMotionListener(mouseManager);
		this.display.getCanvas().addMouseListener(mouseManager);
		Assets.giveHandler(handler);
		Assets.init();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler,0,0);
        entityManager = new EntityManager(handler);
        entityManager.addPlayer(new Player(handler, 100, 100, 64,64));
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        epilogueState = new EpilogueState(handler);

		State.setState(menuState);
	}
	/**
	 * ticks all the tick method of all the classes and 1 gameState
	 */
	public void tick() {
		keyManager.tick();
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	/**
	 * the render method uses a bufferstrategy and will set to draw from the current gamestate and clean everything afterwards
     * currently using canvas and frames to display. the buffers count for the BufferStrategy is 3 at the moment.
	 */
	public void render() {

		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g2d = (Graphics2D)bs.getDrawGraphics();
		g2d.clearRect(0,0,width,height);
		//drawing start
		if(State.getState() != null) {
			State.getState().render(g2d);
		}
		//drawing  end
		bs.show();
		g2d.dispose();
	}
	/**
	 * the run method will be called after the game's start method.
	 * the FPS is set to 30. this method will be called the tick and render method will the game is running
	 */
	public void run() {
		init();
		int FPS = 30;
		double timerPerTick = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now;
		while(running) {
			now = System.nanoTime();		
			delta += (now - lastTime) / timerPerTick;
			lastTime = now;
			
			if (delta >=  1) {
				tick();
				render();
				delta--;
			}
		}
	}
	/**
	 * this method needs to be called after creating the Game class
	 * this will start the thread
	 */
	public synchronized void start() {
		if (running)	
			return;
		running = true;
		thread = new Thread(this); 
		thread.start();
	}

	/**
	 * this Method will close the thread
	 */
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

	public void giveLogger(Logger logger){
        this.logger = logger;
    }
	//getters and setters below
	public GameCamera getGameCamera(){
	    return gameCamera;
    }

    public KeyManager getKeyManager() {

        return this.keyManager;
    }

    public MouseManager getMouseManager(){
		return mouseManager;
	}

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public EpilogueState getEpilogueState() {
        return epilogueState;
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Logger getLogger(){
	    return logger;
    }
}
