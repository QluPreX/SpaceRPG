package entities.npcs;

import entities.Creature;
import main.Handler;

import java.lang.annotation.Native;
import java.util.Random;

/**
 * The Creature has default health and speed, this can be changed when creating a creature object.
 * In the constructor it has a also health and speed, which are both initialized as a default final static
 * The NPC has extra movements and interaction with the player.
 * @author karel
 * @since 2018-11-15
 */
public abstract class NPC extends Creature {

    private long lastMoveTimer, moveCooldown = 50/*ms*/, moveTimer = moveCooldown;
    private boolean goUpAndDown = false;
    private boolean goLeftAndRight = false;
    private boolean turnAround = false;
    private int movement;
    private int maxWalkingWidth = 10;
    private int maxWalkingHeight = 10;
    private int currentX = 0, currentY = 0;
    private Random rand;

    @Native public final int MOVEMENT_RANDOM_UPDOWN = 0;
    @Native public final int MOVEMENT_RANDOM_LEFTRIGHT = 1;
    @Native public final int MOVEMENT_RANDOM = 2;
    @Native public final int MOVEMENT_PATROL_UPDOWN = 3;
    @Native final int MOVEMENT_PATROL_LEFTRIGHT = 4;
    /**
     * the constructor of the NPC class
     *
     * @param handler the handler method from the superClass Entity
     * @param x       the x position of the creature
     * @param y       the y position of the creature
     * @param width   the width in px
     * @param height  the height in px
     */
    public NPC(Handler handler, float x, float y, int width, int height, final int movement_id) {
        super(handler, x, y, width, height);
        this.rand = new Random();
    }

    /**
     * This method will do move the NPC
     * uning set goUpAndDown or goLeftAndRight you can restric its movement
     * @param movement_id the distance for each move
     */
    public void doMovement(final int movement_id){
        //movement index 0 = random movement
        //movement index 1 = lines, cannot be done when goUpAndDown and goLeftAndRight are both true
        setxMove(0);
        setyMove(0);
        int randNr;
        if(movement_id == MOVEMENT_RANDOM) {
            if (currentX < -maxWalkingWidth) { //cant go any more left!
                System.out.println("cant go any more left");
                randNr = rand.nextInt(3);
                if (randNr == 0) {//UP
                    goUp();
                } else if (randNr == 1) { //DOWN
                    goDown();
                } else if (randNr == 2) { //RIGHT
                    goRight();
                }
            } else if (currentX > maxWalkingWidth) { //cant go any more right!
                randNr = rand.nextInt(3);
                if (randNr == 0) {//UP
                    goUp();
                } else if (randNr == 1) { //DOWN
                    goDown();
                } else if (randNr == 2) { //LEFT
                    goLeft();
                }
            } else if (currentY < -maxWalkingHeight) { //cant go any lower!
                randNr = rand.nextInt(3);
                if (randNr == 0) {//UP
                    goUp();
                } else if (randNr == 1) { //LEFT
                    goLeft();
                } else if (randNr == 2) { //RIGHT
                    goRight();
                }
            } else if (currentY > maxWalkingHeight) { //cant go any higher!
                randNr = rand.nextInt(3);
                if (randNr == 0) {//DOWN
                    goDown();
                } else if (randNr == 1) { //LEFT
                    goLeft();
                } else if (randNr == 2) { //RIGHT
                    goRight();
                }
            } else {
                randNr = rand.nextInt(4);
                if (randNr == 0) {//UP
                    goUp();
                } else if (randNr == 1) { //DOWN
                    goDown();
                } else if (randNr == 2) { //LEFT
                    goLeft();
                } else if (randNr == 3) { //RIGHT
                    goRight();
                }
            }
        }else if(movement_id == MOVEMENT_RANDOM_UPDOWN) {
            if (currentY < -maxWalkingHeight) { //cant go any more up
                //go down
                goDown();
            } else if (currentY > maxWalkingHeight) { //cant go any higher!
                //go up
                goUp();

            } else {
                randNr = rand.nextInt(3);
                if (randNr == 0) {//UP
                    goUp();
                } else if (randNr == 1) { //DOWN
                    goDown();
                }
            }
        }else if(movement_id == MOVEMENT_RANDOM_LEFTRIGHT){
            if(currentX < -maxWalkingWidth){//cant go more left
                //go right
                goRight();
            }else if(currentX > maxWalkingWidth) {//cant go more right
                //go left
                goLeft();
            }else {
                randNr = rand.nextInt(3);
                if (randNr == 0) {//UP
                    goLeft();
                } else if (randNr == 1) { //DOWN
                    goRight();
                }
            }
        }else if(movement_id == MOVEMENT_PATROL_LEFTRIGHT){
            if(turnAround){ //GOING LEFT
                if(currentX < -maxWalkingWidth){
                    turnAround = false;
                    return;
                }
                goLeft();
            }else {
                if (turnAround) { //GOING RIGHT
                    if (currentX > maxWalkingWidth) {
                        turnAround = false;
                        return;
                    }
                    goRight();
                }
            }
        }else if(movement_id == MOVEMENT_PATROL_UPDOWN) {
            if (turnAround) { //GOING UP
                if (currentY > maxWalkingHeight) {
                    turnAround = false;
                }else {
                    goUp();
                }
            } else {
                if (currentY < -maxWalkingHeight) {
                    turnAround = true;
                }else {
                    goDown();
                }
            }
        }
    }

    /**
     * This method will ensure it uses a correct speed to move.
     * It uses the System.currentTimeMillis() method to check cooldown.
     * Note: the player has a same method implemented.
     * @see entities.Player
     * @return boolean that can move or cannot move
     */
    public boolean checkCanMove(){
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if(moveTimer < moveCooldown){
            return false;
        }
        return true;
    }

    /**
     * this class's tick method
     * Uses the checkCanMove()
     */
    @Override
    public void tick(){
        if(checkCanMove()){
            moveTimer = 0;
            doMovement(getMovement());
            move();
        }
    }

    /**
     * preset method of to let this NPC go up
     */
    public void goUp(){
        yMove=-speed;
        setCurrentY(getCurrentY()+1);
    }
    /**
     * preset method of to let this NPC go down
     */
    public void goDown(){
        yMove = speed;
        setCurrentY(getCurrentY()-1);
    }
    /**
     * preset method of to let this NPC go left
     */
    public void goLeft(){
        xMove =-speed;
        setCurrentX(getCurrentX()-1);
    }
    /**
     * preset method of to let this NPC go right
     */
    public void goRight(){
        xMove = speed;
        setCurrentX(getCurrentX()+1);
    }

    //getters and setters only below

    /**
     * the getter of the private int  maxWalkingWidth
     * It holds the maximum distance that the Npc can walk in its width (left and right)
     * @return maximum distance that npc can walk
     */
    public int getMaxWalkingWidth() {
        return maxWalkingWidth;
    }

    /**
     * the setter of the private int  maxWalkingWidth
     * It holds the maximum distance that the NPC can walk in its width (left and right)
     * @param maxWalkingWidth maximum distance that NPC can walk
     */
    public void setMaxWalkingWidth(int maxWalkingWidth) {
        this.maxWalkingWidth = maxWalkingWidth;
    }

    /**
     * the getter of the private int maxWalkingHeight
     * It holds the maximum distance that the Npc can walkin in its height (up and down)
     * @return its walking distance value
     */
    public int getMaxWalkingHeight() {
        return maxWalkingHeight;
    }

    /** the setter of the private int maxWalkingHeight
     * It holds the maximum distance that the NpC can walk in its height (up and down -
     * @param maxWalkingHeight maximum distance that Npc can walk
     */
    public void setMaxWalkingHeight(int maxWalkingHeight) {
        this.maxWalkingHeight = maxWalkingHeight;
    }

    /**
     * The getter for the currentX of the NPC
     * it holds the current position of this NPC
     * @return current X position
     */
    public int getCurrentX() {
        return currentX;
    }

    /**
     * the setter for the currentX of the NPC
     * it holds the current position of this NpC
     * used by moving
     * @param currentX new X position
     */
    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    /**
     * the getter for the currentY of the Npc
     * it holds the current position of this NPC
     * @return current Y position
     */
    public int getCurrentY() {
        return currentY;
    }

    /**
     * the setter for the currentX of the Npc
     * it holds the current position of this Npc
     * @param currentY new Y position
     */
    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    /**
     * getter of the movement type of this NPC
     * movement index 0 = random movement
     * movement index 1 = lines, cannot be done when goUpAndDown and goLeftAndRight are both true
     * @return returns its current movement index
     */
    public final int getMovement() {
        return movement;
    }

    /**
     * setter for the movement type of this NPC
     * movement index 0 = random movement
     * movement index 1 = lines, cannot be done when goUpAndDown and goLeftAndRight are both true
     * @param movement gives its current movement index
     */
    public void setMovement(int movement) {
        this.movement = movement;
    }
}

