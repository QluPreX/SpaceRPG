package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import entities.npcs.Human;
import ui.Equipmenu;
import ui.InteractScreen;
import ui.Inventory;
import items.Item;
import entities.unusedEntities.Love;
import entities.unusedEntities.Milk;
import entities.unusedEntities.Weapon;
import main.Handler;
import Graphics.Animation;
import Graphics.Assets;

/**
 * The player class is an extended class of the abstract class Entity.
 * @author Karel De Smet
 * @version 1.0
 */
public class Player extends Creature {
    //Animations
    private Animation animIdle;
    private Animation animWalkFront;
    private Animation animWalkBack;
    private Animation animWalkLeft;
    private Animation animWalkRight;
    private Animation animAttackFront;
    private Animation animAttackBack;
    private Animation animAttackLeft;
    private Animation animAttackRight;
    //Instance variables
    protected String name;
    public Level level;
    public Milk milk;
    public Love love;
    public static final int MILKPLAYER = 100;
    public static final int LOVEPLAYER = 100;
    public int damage;
    public ArrayList<Human> humansKidnapped = new ArrayList<Human>();
    public ArrayList<Item> bag = new ArrayList<Item>();
    private int width = 32;
    private int height = 32;
    private int currentPos = 0;
    private long lastAttackTimer, attackCooldown = 800/*ms*/, attackTimer = attackCooldown;
    private boolean isAttacking = false;
    private Inventory inventory;
    private InteractScreen interactScreen;
    private int energy;
    public static final int STARTENERGY = 0;
    private static int attackBuff = 0;
    private Equipmenu equipmenu;
    //Constructor Declaration of Player
    /**
     * the bounds( for collision detection) are changed in the constructor
     * @param x first value for coordination
     * @param y second value for coordination
     */

    public Player(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.width = width;
        this.height = height;
        bounds.x = 20;
        bounds.y = 35;
        bounds.width = 24;
        bounds.height = 28;
        this.setSpeed(6.0f);
        this.setDestructable(true);
        //Animations
        animIdle = new Animation(250, Assets.playerIdle);
        animWalkFront = new Animation(300, Assets.playerWalkFront);
        animWalkBack = new Animation(300, Assets.playerWalkBack);
        animWalkLeft = new Animation(300, Assets.playerWalkLeft);
        animWalkRight = new Animation(300, Assets.playerWalkRight);
        animAttackFront = new Animation(200, Assets.playerAttackFront);
        animAttackBack = new Animation(200, Assets.playerAttackBack);
        animAttackLeft = new Animation(200, Assets.playerAttackLeft);
        animAttackRight = new Animation(200, Assets.playerAttackRight);

        setEnergy(STARTENERGY);
        level = new Level(1);
        milk = new Milk(MILKPLAYER);
        love = new Love(LOVEPLAYER);
        inventory = new Inventory(handler);
        interactScreen = new InteractScreen(handler);
        equipmenu = new Equipmenu(getHandler());
    }

    public void die() {

    }

    @Override
    public void interact(){}

    @Override
    public void setDestructable(boolean destruct) {
        this.destructable = destruct;
    }

    /**
     * To add a human entity that is kidnapped by this player
     *
     * @param human the human that is kidnapped
     */
    public void addHuman(Human human) {

        humansKidnapped.add(human);
    }

    /**
     * the getter for the amount of humans kidnapped
     *
     * @return size of the dynamic variable humansKidnapped
     */
    public int getAmountHumandsKidnapped() {

        return humansKidnapped.size();
    }

    /**
     * To equip a weapon to the player this will increase the amount of damage the player can give
     *
     * @param weapon the given weapon
     */
    public void equipWeapon(Weapon weapon) {
        //testing only with weapons for the moment
        this.setDamage(weapon.getDamage());
    }

    /**
     * the getter for the name of the player
     *
     * @return name of the player in String
     */
    public String getName() {

        return name;
    }

    @Override
    public BufferedImage getTexture() {
        return null;
    }

    /**
     * the setter of the name that is given to the player in the main contructor of the class
     *
     * @param _name the string cannot be empty or full of whitespace
     */
    private void setName(String _name) {
        if (_name.trim().length() > 0) {
            this.name = _name;
        }
    }

    /**
     * the setter of the damage of the cat
     *
     * @param _damage the damage what cat can give
     */
    private void setDamage(int _damage) {
        this.damage = _damage;
    }

    /**
     * the getter of the damage of the cat
     *
     * @return the amount of damage of the cat
     */
    public int getDamage() {
        return this.damage;
    }

    @Override
    /**
     * The tick method of the player class
     */
    public void tick() {
        //Animations
        animIdle.tick();
        animWalkFront.tick();
        animWalkRight.tick();
        animWalkBack.tick();
        animWalkLeft.tick();
        animAttackBack.tick();
        animAttackFront.tick();
        animAttackLeft.tick();
        animAttackRight.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //interactions
        checkInteract(handler.getKeyManager().actionButton, 0);
        checkInteract(handler.getKeyManager().interact, 1);
        //inventory
        inventory.tick();
        interactScreen.tick();
        setAttackBuff(10);
    }

    private void checkInteract(boolean key, int interactionId) {
        //interactions: 0 == ATTACK, 1 == INTERACT
        if(interactionId == 0){
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
            if (!handler.getKeyManager().actionButton) {
                isAttacking = false;
            } else {
                isAttacking = true;
            }
            if (attackTimer < attackCooldown) {

                return;

            }
        }
        if(inventory.isActive()){
            return;
        }
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;
        ar.y = -arSize - 1;
        ar.x = -arSize - 1;
        if (key) {
            if (getCurrentPos() == 0) {
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
            } else if (getCurrentPos() == 1) {
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else if (getCurrentPos() == 2) {
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else if (getCurrentPos() == 3) {
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
            } else {
                return;
            }
            if(interactionId == 0){
                attackTimer = 0;
            }
            for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.equals(this)) {
                    continue;
                }
                if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                    if(interactionId == 0){
                        if(e.getDestructable()){
                            e.hurt(1+getAttackBuff());
                        }
                    }else if(interactionId == 1){
                        //INTERACT HERE
                        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)){
                            System.out.println(e.getName());
                            e.interact();
                        }
                    }
                    return;
                }
            }
        }
    }

    /**
     * the getInput method of the player class.
     * this method handles the input uses the keyManagers pre-defined movements
     *
     * @see input.KeyManager
     */
    private void getInput() {
        xMove = 0;
        yMove = 0;
        if(inventory.isActive()){
            return;
        }
        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    /**
     * the rendering method of the player class
     * this will render the sprite (playerCat) given from the Assets Class
     */
    public void render(Graphics2D g2d) {
        g2d.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    public void postRender(Graphics2D g2d){
        inventory.render(g2d);
        interactScreen.render(g2d);
    }
    /**
     * the getCurrentAnimationFrame of the player class
     * this method will choose a frame based on the input of the user
     *
     * @return returns a BufferedImage frame gives this to the render method of the player to draw it
     */
    private BufferedImage getCurrentAnimationFrame() {
        if (yMove > 0) {
            currentPos = 0;
            if (isAttacking) {
                return animAttackFront.getCurrentFrame();
            } else {
                return animWalkFront.getCurrentFrame();
            }
        }
        if (yMove < 0) {
            currentPos = 3;
            if (isAttacking) {
                return animAttackBack.getCurrentFrame();
            } else {
                return animWalkBack.getCurrentFrame();
            }
        }
        if (xMove < 0) {
            currentPos = 1;
            if (isAttacking) {
                return animAttackLeft.getCurrentFrame();
            } else {
                return animWalkLeft.getCurrentFrame();
            }
        }
        if (xMove > 0) {
            currentPos = 2;
            if (isAttacking) {
                return animAttackRight.getCurrentFrame();
            } else {
                return animWalkRight.getCurrentFrame();
            }
        }
        if (isAttacking) {
            if (currentPos == 0) {
                return animAttackFront.getCurrentFrame();
            }
            if (currentPos == 3) {
                return animAttackBack.getCurrentFrame();
            }
            if (currentPos == 1) {
                return animAttackLeft.getCurrentFrame();
            }
            if (currentPos == 2) {
                return animAttackRight.getCurrentFrame();
            }
        }
        return Assets.playerIdle[currentPos];
    }
    public int getCurrentPos(){
	    //0 DOWN
        //1 LEFT
        //2 RIGHT
        //3 UP
	    return currentPos;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public Handler getHandler(){
        return handler;
    }

    public InteractScreen getInteractScreen() {
        return this.interactScreen;
    }
    public void setEnergy(int energy){
        this.energy = energy;
    }

    public static int getAttackBuff() {
        return attackBuff;
    }

    public void setAttackBuff(int attackBuff) {
        Player.attackBuff = attackBuff;
    }

    public Equipmenu getEquipmenu() {
        return equipmenu;
    }
}
