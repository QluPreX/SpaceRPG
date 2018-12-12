package state;

import java.awt.*;

import main.Handler;

/**
 * this class is more of template class for the states that will be made.
 * only 1 state can be run at the same time by the Game class using Canvas.
 * @Since 2018-10-17
 */
public abstract class State {

    //default this is set to "null"
	private static State currentState  = null;

    //setter
	public static void setState(State state) {
			currentState = state;
	}
	//getter
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	protected Handler handler;

    /**
     * the constructor is nothing but the handler
     * @param handler given handler
     */
	public State(Handler handler) {
		this.handler = handler;
	}

    /**
     * This class's tick method
     */
	public abstract void tick();

    /**
     * this class's render method
     * @param g2d given Graphics2D class
     */
	public abstract void render(Graphics2D g2d);
	
}
