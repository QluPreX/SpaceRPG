package entities.staticEntities;

import Graphics.Tiles.Tile;
import main.Handler;
import Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TreeLog extends StaticEntity {
    private String name = "A treelog";
    private float boundsX = 0.25f;
    private float boundsY = 0.5f;
    private float boundsWidth = 0.5f;
    private float boundsHeight = 0.66f;
    private boolean destructable = false;

    /**
     * the Constructor for treelog, creating this only requires an X and Y position
     * @param handler It's given handler
     * @param x position to render on X axis
     * @param y position to render on Y axis
     */
    public TreeLog(Handler handler, int x, int y){
            super(handler,x,y, Tile.TILEWIDTH,Tile.TILEHEIGHT);
            setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
            setHealth(health);
            setDestructable(this.destructable);
    }


    /**
     * the Constructor for treelog, creating this only requires an X and Y position
     * @param handler It's given handler
     * @param x position to render on X axis
     * @param y position to render on Y axis
     * @param width size to render this entity
     * @param height size to render this entity
     */
    public TreeLog(Handler handler, int x, int y, int width, int height){
        super(handler,x,y, width,height);
        setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        setDestructable(this.destructable);
    }

    /**
     * This class's die method, gets called when this entity gets killed by the player
     */
    @Override
    public void die() {

    }

    /**
     * This class's interact method, gets called when this entity gets interacted by the player
     */
    @Override
    public void interact() {
        String[] messages = new String[] {"I am treelog!"
                , ""
                , "You can't kill me!"
                ,"XD"};
        handler.getWorld().getEntityManager().getPlayer().getInteractScreen().addMessage(messages, this);
    }

    /**
     * This class's tick method
     */
    @Override
    public void tick() {

    }

    /**
     * this class's tick method
     * @param g2d the Graphics2D class in order to draw anything
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(getTexture(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width, height, null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }


    public String getName() {
        return name;
    }
    public BufferedImage getTexture(){
        return Assets.treeLog;
    }
    @Override
    public void setDestructable(boolean destruct) {
        this.destructable = destruct;
    }

}
