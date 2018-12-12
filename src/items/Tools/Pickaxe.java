package items.Tools;

import java.awt.image.BufferedImage;

/**
 *
 * @Since 2018-11-18
 */
public class Pickaxe extends Tool {
    /**
     * the main constructor of the Pickaxe class
     *
     * @param texture
     * @param name
     * @param id
     *
     */
    public Pickaxe(BufferedImage texture, String name, int id) {
        super(texture, name, id);
        setAttackBuff(6);
    }
}
