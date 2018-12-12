package entities.npcs;

import java.awt.*;
import java.awt.image.BufferedImage;

import Graphics.Assets;
import Graphics.Tiles.Tile;
import main.Handler;
/**
 * The human class extends the abstract creature class.
 * It can only render and tick for the moment
 * @author karel
 * @version 1.0
 * @since 2018-11-15
 */
public class Human extends NPC {
    private String name;
    private float boundsX = 0.1f;
    private float boundsY = 0.50f;
    private float boundsWidth = 0.20f;
    private float boundsHeight = 0.50f;
    private int health = 10;
    private float speed = 2.f;
    private int maxWalkingHeight = 100;
    /**
     * Constructor to create a Human entity,
     * Only this entity can interact
     * Width = Tile.TIlEWIDTH - 20
     * height = Tile.TILEHEIGHT
     * @param handler given handler
     * @param x x starting position of the entity
     * @param y x starting position of the entity
     * @param name its name, cannot be empty
     */
	public Human(Handler handler, float x, float y, final int movement_id, String name) {
		super(handler,x, y, Tile.TILEWIDTH-20, Tile.TILEHEIGHT, movement_id);
		setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
		this.setHealth(health);
        this.setSpeed(speed);
        this.setMaxWalkingHeight(maxWalkingHeight);
        setName(name);
	}

    /**
     * Constructor to create a Human entity
     * this human does not have a name, and cannot attack
     * @param handler its given hander
     * @param x x starting position of the entity
     * @param y y starting position of the entity
     */
    public Human(Handler handler, float x, float y, final int movement_id) {
        super(handler,x, y, Tile.TILEWIDTH-20, Tile.TILEHEIGHT,movement_id);
        setBoundsOffset(boundsX,boundsY,boundsWidth,boundsHeight);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setMaxWalkingHeight(maxWalkingHeight);
        this.setMovement(movement_id);
    }

    /**
     * gets called when this entity is removed from the game.
     */
	@Override
	public void die(){

	}

    /**
     * gets called when player interacts with this entity
     */
    @Override
    public void interact() {
        System.out.println(this+"HUMAN");
 	    if(!getName().isEmpty()) {
            handler.getWorld().getEntityManager().getPlayer().getInteractScreen().addMessage(
                    new String[]{"I am a human", "\n","\n","\n","\n","\n","\n","nice to meet you!"}, this);
	    }
	}
    /**
     * Override abstract method from class Entity to set this class to be destructable by the player.
     * @param destruct
     */
    @Override
    public void setDestructable(boolean destruct) {
        this.destructable = destruct;
    }

    /**
     * this class's tick method
     */
    @Override
	public void tick() {
		super.tick();
	}

    /**
     * this class's render method
     * @param g2d the Graphics class in order to draw anything
     */
	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(getTexture(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()), width, height,null);
        if(getBoundVisibility()){
            renderCollisionBound(g2d, this);
        }
    }

    /**
     * getter of this class's name
     * @return name of this class
     */
    public String getName() {
        return name;
    }

    /**
     * setter of this class's name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of this class's BufferedImage
     * to give this class a new Asset, change it here
     * @return the bufferedImage bound to this class
     */
    public BufferedImage getTexture(){
        return Assets.human;
    }
}
