package entities.staticEntities;

import items.Item;
import main.Handler;
import Graphics.Assets;
import Graphics.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The tree class is an static Entity which basically means it's unmovable
 *
 */
public class Tree extends StaticEntity {

    private String name = "a tree";
    private float boundsX = 0.25f;
    private float boundsY = 0.5f;
    private float boundsWidth = 0.50f;
    private float boundsHeight = 0.5f;
    private int health = 6;
    /**
     * 2/11/2018 -
     * the constructor of the Tree has a tailor-made collision boundary box made.
     * <em>If the asset of this class is ever changed, the collision boundary must be re-tested</em>
     * the width and height are sets to Tiles, so it's easier to calculate with.
     * @param handler the handler Class
     * @param x its x position
     * @param y its y position
     */
    public Tree(Handler handler, float x, float y){
        super(handler,x,y, Tile.TILEWIDTH,Tile.TILEHEIGHT*2);
        setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        this.setHealth(health);
    }

    /**
     * this method will be called when this class is going to be removed.
     * the only possibility to remove an Entity is to empty it's hitpoints or health, which means the player must hurt this class
     * right now this class creates an wooden log Item, which is dropped
     */
    @Override
    public void die(){
        //handler.getWorld().getEntityManager().addEntity( new TreeLog(handler, this.x, this.y+Tile.TILEHEIGHT+5));
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (x+width/4), (int) (y+height*0.8)));
    }

    /**
     * This class's interact method, gets called when player interacts with this entity
     */
    @Override
    public void interact() {
        handler.getWorld().getEntityManager().getPlayer().getInteractScreen().addMessage(new String[]{"I am a tree, leave be alone."}, this);
    }

    /**
     * This class's tick method
     */
    @Override
    public void tick() {

    }

    /**
     * This class's render method
     * @param g2d the given graphics runner
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(getTexture(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width, height, null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }

    //getters and setters below
    public String getName() {
        return name;
    }
    public BufferedImage getTexture() {
        return Assets.tree;
    }
}
