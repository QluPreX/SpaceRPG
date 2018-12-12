package entities;
import main.Handler;
import Graphics.Tiles.Tile;

/**
 * Creature is the abstract class that is an extended class of the abstract class Entity.
 * The creature class is used for collision detections with Tiles.
 * @author Karel De Smet
 * @version 1.0
 */
public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;
    //Constuctor Declaration of Creature

    /**
     * The Creature has default health and speed, this can be changed when creating a creature object.
     * In the constructor it has a also health and speed, which are both initialized as a default final static
     * @param handler the handler method from the superClass Entity
     * @param x the x position of the creature
     * @param y the y position of the creature
     * @param width the width in px
     * @param height the height in px
     */
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler,x, y, width, height);

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
		}

    /**
     * The move method of the player class
     * this method runs 2 methods moveX() and moveY()
     */
    public void move(){
        if(!checkEntityCollisions(xMove,0f)){
            moveX();
        }
        if(!checkEntityCollisions(0f, yMove)){
            moveY();
        }

    }

    /**
     * The moveX method of the player checks the collision detection with the creature and a solid Tile
     * A tile can be solid in the it's own Tile class.
     * it uses the bounds Rectangle variable of the Entity superclass as collision box
     * In this method we only check moving left and right.
     */
    public void moveX(){
        if(xMove > 0){//Moving right

            int tempX = (int) (x + xMove + bounds.x + bounds.width )/ Tile.TILEWIDTH;
            if(!collisionWithTile(tempX,(int) (y + bounds.y)/Tile.TILEHEIGHT)
                && !collisionWithTile(tempX, (int)(y+ bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){//Moving left
            int tempX = (int) (x + xMove + bounds.x )/ Tile.TILEWIDTH;
            if(!collisionWithTile(tempX,(int) (y + bounds.y)/Tile.TILEHEIGHT)
                    && !collisionWithTile(tempX, (int)(y+ bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    /**
     * The moveY method of the player checks the collision detection with the creature and a solid Tile
     * A tile can be solid in the it's own Tile class.
     * it uses the bounds Rectangle variable of the Entity superclass as collision box
     * In this method we only check moving up and down.
     */
    public void moveY(){
        if(yMove < 0){//Up
            int tempY = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY)
                && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)){
                y += yMove;
        }else{
                y = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        }else if (yMove > 0){//Down
            int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)) {
                y += yMove;
            }else{
                y = tempY * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    /**
     * the collisionWithTile is now only used in the moveX and moveY method.
     * this method goes to the specific Tile that's on X and Y and checks if it's Solid or not.
     * isSolid() = True // collision
     * isSolid() = False // no collision
     * @param x the x position of the tile, which is most likely an entities x position, to collision
     * @param y the y position of the tile, which is most likely an entities y position, to collision
     * @return a boolean, the same boolean as isSolid()
     * @see Tile isSolid() method, this can be overwritten in the subclasses for specified tiles
     */
    protected  boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }

	// Getters and setters only from here!

    public float getxMove(){
	    return xMove;
    }
    public void setxMove(float xMove){
	    this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
