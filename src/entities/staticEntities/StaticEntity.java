package entities.staticEntities;

import entities.Entity;
import main.Handler;

import java.awt.*;

/**
 * The StaticEntity holds as an abstract form of an static, unmovable Entity.
 * Entities that belong here, can and mostly drop items, also can mostly be destroyed
 */
public abstract class StaticEntity extends Entity {
    /**
     * The static constructor passes everything from its super class Entity to its subclass
     * @param handler the handler class
     * @param x its x position
     * @param y its y position
     * @param width its width
     * @param height its height
     */
    public StaticEntity (Handler handler, float x, float y, int width, int height ){
        super(handler, x,y, width, height);
    }
}
