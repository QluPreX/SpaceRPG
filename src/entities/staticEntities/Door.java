package entities.staticEntities;

import main.Handler;
import Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Door is the first class to be a TransferObject.
 * this object tranfsers the player to another world/area when interacted
 */
public class Door extends TransferObject {

    private String name = "Door";
    private BufferedImage doorTexture = Assets.unknownTile;
    private boolean destructable = false;

    /**
     * The constructor currently has not unique setters.
     * Only give X and Y for it's render position and X and Y for the player's transfer position in the new World
     * @param handler It's given handler
     * @param x render position on the X axis
     * @param y render position on the Y axis
     * @param spawnX spawn position for the player
     * @param spawnY spawn position for the player
     * @param WORLD_ID the worlds ID, use the ID's defined in the GameState class
     */
    public Door(Handler handler, float x, float y, int spawnX, int spawnY, final int WORLD_ID) {
        super(handler, x, y, Assets.unknownTile.getWidth(),Assets.unknownTile.getHeight(), spawnX, spawnY, WORLD_ID);
        super.setDestructable(destructable);
    }

    /**
     * The second constructor is the same as the first one, only it has the ability to manipulate the width and the height of this entity
     * @param handler It's given handler
     * @param x render position of the X axis
     * @param y render position of the Y axis
     * @param spawnX spawn position for the player
     * @param spawnY spawn position for the player
     * @param WORLD_ID the worlds ID, use the ID's defined in the GameState class
     * @param width it's width when rendered
     * @param height it's height when rendered
     */
    public Door(Handler handler, float x, float y, int spawnX, int spawnY, final int WORLD_ID, int width, int height) {
        super(handler, x, y, width,height, spawnX, spawnY, WORLD_ID);
        super.setDestructable(destructable);
    }

    /**
     * This's classes interact method, gets called when player interacts with it.
     * Here it transfers to the WORLD_ID that is given in the constructor when created.
     * using the GameState.getWorld(int) method, we get the pre-defined world the user wants to transfer to.
     */
    @Override
    public void interact(){
        //transfer
        handler.getGame().getGameState().changeWorld(handler.getGame().getGameState().getWorld(WORLD_ID));
        //System.out.println("WORLD_ID: "+WORLD_ID+" => "+handler.getGame().getGameState().getWorld(WORLD_ID).getClass().getName());
    }

    /**
     * This class's die method, gets called when it's killed by the player
     */
    @Override
    public void die() {

    }

    /**
     * This class's tick method
     */
    @Override
    public void tick(){

    }

    /**
     * This class's render method
     * @param g2d the Graphics2D class in order to draw anything
     */
    @Override
    public void render(Graphics2D g2d){
        g2d.drawImage(doorTexture, (int) (x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }

    //getters and setters below
    @Override
    public String getName() {
        return name;
    }

    @Override
    public BufferedImage getTexture() {
        return doorTexture;
    }

    @Override
    public boolean getDestructable(){
        return destructable;
    }

}
