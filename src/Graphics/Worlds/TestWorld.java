package Graphics.Worlds;

import entities.Entity;
import main.Handler;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class TestWorld extends World {
    public TestWorld(Handler handler, File path) {
        super(handler);
        //insert entinties here

        super.loadWorld(path);

    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public void render(Graphics2D g2d){
        super.render(g2d);
    }
}
