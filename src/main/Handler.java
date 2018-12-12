package main;

import input.KeyManager;
import input.MouseManager;
import Graphics.GameCamera;
import Graphics.Worlds.World;

/**
 * The Handler class is a library containing Game
 * This is for easier access.
 * @Since 2018-10-18
 **/
public class Handler {

    private Game game;
    private World world;
    public Handler(Game game){
        this.game = game;
    }

    //getter and setters below
    public int getWidth(){
        return game.getWidth();
    }
    public int getHeight(){
        return game.getHeight();
    }
    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
}
