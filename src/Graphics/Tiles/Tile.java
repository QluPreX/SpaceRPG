package Graphics.Tiles;

import Graphics.Tiles.MoonTileBig.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static Tile moonTileBig1 = new MoonTileBig1(0);
    public static Tile moonTileBig2 = new MoonTileBig3(1);
    public static Tile moonTileBig3 = new MoonTileBig4(2);
    public static Tile moonTileBig4 = new MoonTileBig5(3);
    public static Tile moonTileBig5 = new MoonTileBig6(4);
    public static Tile moonTileBig6 = new MoonTileBig7(5);
    public static Tile unknownTile = new UnknownTile(99);
    //class

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){

        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics2D g2d, int x, int y){
        g2d.drawImage(texture, x,y, TILEWIDTH, TILEHEIGHT,null);
    }
    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
