package items.Tools;

import items.Item;
import ui.ClickListener;

import java.awt.image.BufferedImage;

/**
 * @since 2018-11-17
 */
public abstract class Tool extends Item {

    /**
     * the main constructor of the Class Tool
     *
     * @param texture
     * @param name
     * @param id
     */
    private int attackBuff = 0;

    public Tool(BufferedImage texture, String name, int id) {
        super(texture, name, id);
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public void setAttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }
}
