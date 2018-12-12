package items;

import items.Tools.Pickaxe;
import items.Tools.Tool;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Assets;
import ui.ClickListener;

public class Item {
	/***
	 * the item class: It get's it's DataInformation from a saved XML file (Not implemented yet)
     *
	 * @author karel
	 * @version 1.0
     * @since 2018-10-17
	 */
	//Handler
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.woodLog, "wooden log", 0);
    public static Item moonDust = new Item(Assets.moonDust, "moon dust", 1);
	public static Item moonOre = new Item(Assets.moonOre, "moon ore", 2);
	public static Pickaxe pickaxe = new Pickaxe(Assets.unknownTile, "pickaxe", 3);
	public ClickListener clickListener;
    //class
	public static final int ITEMWIDTH = 32,  ITEMHEIGHT = 32;
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected int x,y, count;
	protected Rectangle bounds;
	protected boolean pickedUp = false;
	/**
	 * the main constructor of the Class Item
	 * 
	 */
	public Item(BufferedImage texture, String name, int id) {
		setName(name);
		setTexture(texture);
		this.id = id;
		setCount(1);
        bounds = new Rectangle(x,y, ITEMWIDTH, ITEMHEIGHT);
		items[id] = this;
	}
    /*public Item createNew(int count){ //for testing and debugging purposes
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }*/
	public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x,y);
        return i;
    }

	public void setPosition(int x, int y){
	    setX(x);
	    setY(y);
	    bounds.x = x;
	    bounds.y = y ;
    }

	public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0).intersects(bounds)){
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics2D g2d){
	    if(handler == null){
	        return;
        }
	    render(g2d, (int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics2D g2d, int x, int y){
        g2d.drawImage(texture, x,y,ITEMWIDTH, ITEMHEIGHT, null);
    }




    /*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/
    //getters and setter below only
	/**
	 * The getter from the class Item
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * The setter from the class item
	 * @param name
	 */
	private void setName(String name) {
		this.name = name;
	}

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}