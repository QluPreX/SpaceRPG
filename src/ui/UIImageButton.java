package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.annotation.Native;
import java.nio.Buffer;


/**
 *
 * @Since 2018-10-30
 */
public class UIImageButton extends UIObject{

    private BufferedImage[] images;
    private ClickListener clicker;
    private Class _class;
    @Native  public final int ID_PAUSEMENU = 1;
    @Native public final int ID_BASE = 0;
    private int id;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker, final int SPECIAL_ID){
        super(x,y,width, height);
        setClicker(clicker);
        setImages(images);
        this.id = SPECIAL_ID;
    }
    @Override
    public void tick(){
        if(this.id == ID_PAUSEMENU ){
            handler.getGame().getGameState().getUiManager().getPauseMenu().tick();
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if(this.id == ID_PAUSEMENU){
            if(!handler.getGame().getGameState().getUiManager().getPauseMenu().getActive()){
                return;
            }

            System.out.println(this.getX()+" "+getY());
        }

        if(this.isHovering()){
            g2d.drawImage(images[1], (int) x, (int) y, width, height, null);
        }else{
            g2d.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public ClickListener getClicker() {
        return clicker;
    }

    public void setClicker(ClickListener clicker) {
        this.clicker = clicker;
    }

    public int getId() {
        return id;
    }
}
