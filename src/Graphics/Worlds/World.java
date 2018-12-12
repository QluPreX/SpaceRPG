package Graphics.Worlds;

import entities.EntityManager;
import entities.Player;
import entities.npcs.Human;
import entities.staticEntities.MoonOre;
import entities.staticEntities.MoonRock;
import entities.staticEntities.Tree;
import entities.staticEntities.TreeLog;
import items.ItemManager;
import main.Handler;
import Graphics.Tiles.Tile;
import utilities.Utils;
import java.awt.*;
import java.io.File;

/**
 * The world class is an abstract class for every world/area subclass.
 * here it has the tiles id's stored, spawn place and render's the tiles itself.
 * Also the method loadPath(File) is created here so that every world-subclass different is.
 */
public abstract class World {

    public int width, height;
    protected int spawnX, spawnY;
    protected int[][] tiles;
    protected Handler handler;
    //entities
    protected EntityManager entityManager;
    protected ItemManager itemManager;

    /**
     * In here only the ItemManager will be initialized, not the entityManager.
     * The entityManager is created in the Game class, due to changing worlds this will be reset.
     * When changing/transferring to other worlds the items on the ground will be reset since it creates a new one for every world.
     * @param handler
     */
    public World(Handler handler){
        this.handler = handler;
        itemManager = new ItemManager(handler);
        entityManager = handler.getGame().getEntityManager();
    }

    /**
     * This class's tick method
     */
    public void tick(){
        entityManager.tick();
        itemManager.tick();
    }

    /**
     * This class render method.
     * here the tiles will be rendered as well
     * @param g2d given graphics2D
     */
    public void render(Graphics2D g2d){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH +1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);

        for (int y = yStart; y < yEnd; y++){
            for (int x = xStart; x < xEnd; x++){
                        getTile(x,y).render(g2d,(int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset())
                                     ,(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        itemManager.render(g2d);
        entityManager.render(g2d);
    }

    /**
     * This method is only used it this class's render method.
     * This method has also other uses, example to get if a tile is solid 'getTile(0,2).isSolid()"
     * @param x X position of the tile inside the array
     * @param y Y position of the tile inside the array
     * @return the tile in the X and Y position of the array
     */
    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.unknownTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null){
            return Tile.unknownTile;
        }
        return t;
    }

    /**
     * LoadWorld is only used in this class.
     * It uses the Utils.loadFileAsString to get the whole file as 1 string and cuts it in tokens[] with regex.
     * the first 4 tokens are special ones, the rest are Tiles ID's these can be found in Tiles.class
     * This method also sets the players spawn position
     * @param path
     */
    protected void loadWorld(File path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[4]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x= 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4 ]); // first 4 tokens are special
            }
        }
        System.out.println(entityManager.getPlayer());
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    //getters and setters below
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int[][] getTiles() {
        return tiles;
    }

}
