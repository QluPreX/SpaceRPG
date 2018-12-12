package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Keymanager handles everything with input of the keyboard
 * Input, output, pressTimes, ...
 * @Since 2018-10-17
 */
public class KeyManager implements KeyListener{

	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, right, left;
	public boolean actionButton, interact;
	//Constructor Declaration of Keymanager

    /**
     * The Keymanager constructor has 256 different typs of keysstrokes that we can fill in.
     * in the tick method we initialize them
     */
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}

    /**
     * The tick method ticks every frame, like every other tick method.
     * In here 4 types of keyStrokes are defined: Up, Down, Right and Left
     */
	public void tick() {
        for(int i =0; i < keys.length; i++){
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            }else if(justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }
        interact = keys[KeyEvent.VK_I];
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		right = keys[KeyEvent.VK_RIGHT];
		left = keys[KeyEvent.VK_LEFT];

		actionButton = keys[KeyEvent.VK_A];
		interact = keys[KeyEvent.VK_I];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >=keys.length){
            return;
        }
        keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >=keys.length){
            return;
        }
        keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
    public boolean keyJustPressed(int keyCode){
	    if(keyCode < 0 || keyCode >= keys.length){
            return false;
        }
        return justPressed[keyCode];
	}
}
