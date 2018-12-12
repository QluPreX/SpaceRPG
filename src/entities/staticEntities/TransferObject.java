package entities.staticEntities;

import Graphics.Worlds.World;
import main.Handler;
import state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class TransferObject extends StaticEntity{
    /**
     * The static constructor passes everything from its super class Entity to its subclass
     *
     * @param handler the handler class
     * @param x       its x position
     * @param y       its y position
     * @param width   its width
     * @param height  its height
     */
    private int spawnX, spawny;
    private World world;
    protected final int WORLD_ID;
    public TransferObject(Handler handler, float x, float y, int width, int height, int spawnX, int spawnY, final int WORLD_ID) {
        super(handler, x, y, width, height);
        this.spawnX = spawnX;
        this.spawny = spawnY;
        this.WORLD_ID = WORLD_ID;
    }
    public abstract void interact();
}
