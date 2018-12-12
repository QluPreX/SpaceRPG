package Graphics.Worlds;

import entities.npcs.Human;
import entities.npcs.NPC;
import entities.staticEntities.*;
import main.Handler;
import java.awt.*;
import java.io.File;
/**
 * This class is One of the (many) world classes.
 * The moon class is currently the starting area/world
 */
public class Moon extends World{
    /**
     * In the constructor of the Moon class every entity is added specificly
     *
     * @param handler
     * @param path
     */
    public Moon(Handler handler, File path){
        super(handler);
        entityManager.addEntity(new Tree(handler, 32, 100));
        entityManager.addEntity(new TreeLog(handler, 100, 100));
        entityManager.addEntity(new Tree(handler, 170, 100));
        entityManager.addEntity(new MoonRock(handler,100,300));
        entityManager.addEntity(new MoonRock(handler,164,300));
        entityManager.addEntity(new MoonRock(handler, 250, 300));
        entityManager.addEntity(new MoonRock(handler, 700, 600));
        entityManager.addEntity(new MoonRock(handler, 250, 1200));
        entityManager.addEntity(new MoonRock(handler, 1200, 900));
        entityManager.addEntity(new Human(handler,100,130, getEntityManager().getEntityClass().getNPC().MOVEMENT_PATROL_UPDOWN, "steve"));
        entityManager.addEntity(new MoonOre(handler, 600, 664));
        entityManager.addEntity(new MoonOre(handler, 600, 732));
        entityManager.addEntity(new MoonOre(handler, 600, 796));
        entityManager.addEntity(new MoonOre(handler, 800, 600));
        entityManager.addEntity(new Door(handler, 100, 400,0,0,1));
        super.loadWorld(path);

    }

    /**
     * This class's tick method
     */
    @Override
    public void tick(){
        super.tick();
    }

    /**
     * This class's render method
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d){
        super.render(g2d);
    }

}
