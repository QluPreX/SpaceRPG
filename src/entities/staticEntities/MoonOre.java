package entities.staticEntities;

import Graphics.Worlds.Moon;
import Graphics.Worlds.TestWorld;
import items.Item;
import main.Handler;
import Graphics.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * MoonOre class is an StaticEntity class
 * It holds the basic overriden method of its superclass
 *
 * Story:
 * currently the moonOre story drops only an Item.moonOre
 *
 * @author karel
 * @since 2018-11-17
 */
public class MoonOre extends StaticEntity{
    /**
     */
    private String name = "a moonOre";
    private float boundsX = 0.10f;
    private float boundsY = 0.40f;
    private float boundsWidth = 0.20f;
    private float boundsHeight = 0.50f;
    private int health = 6;

    /**
     * the constuctor of this class is a basic subclass Constuctor of an staticEntity
     * bounds of this class is tailored, have to check and change if the asset is changed
     * it's default health is 6
     * @param handler the given Handler
     * @param x the x position where it is rendered
     * @param y the y position where it is rendered
     */
    public MoonOre (Handler handler, int x, int y){
        super(handler, x, y, Assets.moonOre.getWidth(), Assets.moonRock.getHeight());
        setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        this.setHealth(health);
        this.setDestructable(destructable);
    }

    /**
     * It's die method, gets called when the player kills this entity
     */
    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.moonOre.createNew((int) (x+width/4), (int) (y+height*0.8)));
    }

    /**
     * It's interact method, gets called when the player interacts with this entity
     */
    @Override
    public void interact() {
        handler.getWorld().getEntityManager().getPlayer().getInteractScreen().addMessage(new String[] {"I am a moon ore"}, this);
    }

    /**
     * This class's tick method
     */
    @Override
    public void tick() {

    }

    /**
     * This class's render method
     * @param g2d the Graphics2D class in order to draw anything
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(getTexture(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }

    //Getters and setters below
    public String getName() {
        return name;
    }
    public BufferedImage getTexture(){
        return Assets.moonOre;
    }
}
