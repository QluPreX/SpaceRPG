package entities.staticEntities;

import items.Item;
import main.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;

/**
 * The Moonrock class is a defined staticEntity that can be added in worlds
 */
public class MoonRock extends StaticEntity {

    private String name = "a moonrock";
    private float boundsX = 0.10f;
    private float boundsY = 0.40f;
    private float boundsWidth = 0.20f;
    private float boundsHeight = 0.50f;
    private int health = 3;
    /**
     * It's constructor uses the X and Y for location drawing
     *
     * @param handler It's given Handler class
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public MoonRock (Handler handler, int x, int y){
        super(handler,x, y, Assets.moonRock.getWidth(), Assets.moonRock.getHeight());
        super.setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        super.setHealth(health);
        super.setDestructable(destructable);
    }

    /**
     * It's second constructor uses the X and Y for location drawing.
     * Also adds a posibility to manipulate its size (width and height)
     * @param handler It's given Handler class
     * @param x position on the x axis
     * @param y position on the y axis
     * @param width width of the entity
     * @param height height of the entity
     */
    public MoonRock(Handler handler, int x, int y, int width, int height){
        super(handler, x, y, width, height);
        setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        super.setHealth(health);
        super.setDestructable(destructable);
    }

    /**
     * It's die method, gets called when it is removed for the world
     */
    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.moonDust.createNew((int) (x+width/4), (int) (y+height*0.8)));
    }

    /**
     * its interact method, gets called when the player interacts with it.
     */
    @Override
    public void interact() {
        handler.getWorld().getEntityManager().getPlayer().getInteractScreen().addMessage(new String[] {"I am a moon rock"}, this);
    }

    /**
     * this class's tick method
     */
    @Override
    public void tick() {

    }

    /**
     * This class"s tick method
     * @param g2d the Graphics2D class in order to draw anything
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(getTexture(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()),width, height, null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public BufferedImage getTexture(){
        return Assets.moonRock;
    }
}
