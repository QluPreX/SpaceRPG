package ui;

import items.Tools.Tool;
import main.Handler;
import java.awt.*;

/**
 * The Equipmenu is used like an Inventory but for Tools
 * @Since 2018-11-18
 */
public class Equipmenu {

    private Handler handler;
    public Equipmenu (Handler handler){
        this.handler = handler;
    }

    public void equipWeapon(Tool weapon){
        handler.getWorld().getEntityManager().getPlayer().setAttackBuff(weapon.getAttackBuff());
        //handler nullpointer?
    }

    public void tick(){

    }

    public void render(Graphics2D g2d){

    }

}
