package entities;
import entities.npcs.Human;
import entities.npcs.NPC;
import main.Handler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Year;

/**
 * 2/11/2018 -
 * The entity class is the top abstract class in its hierarchy.
 * Which allows the program to create enitities.
 * An enitity is everything that moves on it's own.
 * @author	Karel De Smet
 * @version    %I%, %G%
 * @since	2018-11-04
 */
public abstract class Entity {

	public static final int DEFAULT_HEALTH = 100;
	protected int health;
	protected float x,y;
	protected int width ,height;
	protected boolean active = true;
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean destructable = true;
	private boolean boundVisibility = false;
	private NPC _testNPC;
    //Constructor Declaration of Entity

    /**
     * The Entity constructor will never be created on it's own, therefore being abstract
     * The Entity constructor will hold mostly the handler, positions and size
     * In the constructor is also a default bounds Rectangle created, this is the collision bound box of the entity, which can be edited when creating a subclass object
     * Its health is set to the static final variable DEFAULT_HEALTH, but this can changed when creating this object using the setHealth() method
     * @param handler the Handler class to have a link between the Entity class and the most used game classes
     * @param x the x position of the entity in px
     * @param y the y position of the entity in px
     * @param width the width for the size of the entity in px
     * @param height the height for the size of the entity in px
     */
	public Entity(Handler handler, float x, float y, int width, int height) {
		health = DEFAULT_HEALTH;
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0, width, height);



	}

    /**
     * the abstract die method is called in the hurt method when an object loses all its health, not when it is removed manually
     */
	public abstract void die();

    /**
     * this method is called when a player interacts with this entity, excluding himself
     */
    public abstract void interact();
    /**
     * the hurt method is called when a player attacks this Entity.
     * so meaning the player's collision boundary intersect the collision boundary of this Entity and the player uses the action button in the direction of this Entity.
     * this is the ONLY method that uses the setActive method, setting the active boolean of this Entity false, the Entity manager deletes only the false-active Entities.
     * @see EntityManager
     * @param amount the amount of hitpoints this Entity takes
     */
	public void hurt(int amount){
        health -=amount;
        if(health <=0){
            setActive(false);
            die();
        }
    }

    protected void setBoundsOffset(float x, float y, float width, float height){
        bounds.x = (int)(this.width*x);
        bounds.y = (int) (this.height*y);
        bounds.width = (int) (this.width - this.width*width);
        bounds.height = (int) (this.height - this.height *height);
    }

	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset, yOffset))){
				return true;
			}
		}
		return false;
	}

    /**
     * 2/11/2018 -
     * the default rick method
     * this method will be overridden by any of its initiliazed subclasses
     */
	public abstract void tick();

    /**
     * 2/11/2018 -
     * the default render method
     * @param g2d the Graphics2D class in order to draw anything
     */
	public abstract void render(Graphics2D g2d);

    /**
     * This method is used for debugging purposes, it draw's the collision-box of this entity.
     * this method has to be created in a render method.
     * @param g2d the given Graphics2D class from the render class
     * @param e the collision of box this given entity
     */
    public void renderCollisionBound(Graphics2D g2d, Entity e){
        g2d.setColor(Color.YELLOW);
        g2d.drawRect((int)(e.bounds.x - handler.getGameCamera().getxOffset() + e.x),(int)(e.bounds.y - handler.getGameCamera().getyOffset() + e.y), e.bounds.width , e.bounds.height);
    }

    //Getters and Setters below
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
	}

	public abstract String getName();

    public abstract BufferedImage getTexture();

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x+bounds.x + xOffset), (int) (y +bounds.y + yOffset), bounds.width, bounds.height);
    }

    public boolean getDestructable(){
        return destructable;
    }

    public void setDestructable(boolean destruct){
        this.destructable = destruct;
    }

    public void setBoundVisibility(boolean bool){
        this.boundVisibility = bool;
    }

    public boolean getBoundVisibility(){
        return boundVisibility;
    }
    public NPC getNPC() {
        return  _testNPC = new Human(handler,0,0,0);
    }
}
