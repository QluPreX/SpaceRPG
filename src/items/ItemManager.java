package items;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Item Manager is an overhead for all the Items in the game
 * so Tools are a part of Items too, since they can be dropped.
 *
 * the Equipmenu is attached to the player. but the tool manager is attached to the ItemManager, which is attached to the player.
 *
 * @since 2018-11-02
 */
public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;
    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick(){
        //ticking Items
        Iterator<Item> _items = items.iterator();
        while(_items.hasNext()){
            Item i = _items.next();
            i.tick();
            if(i.isPickedUp()){
                //add item to inventory
                _items.remove();
            }
        }
    }
    public void render(Graphics2D g2d){
        for(Item i : items){
            i.render(g2d);
        }
    }
    //Getters and setters

    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
