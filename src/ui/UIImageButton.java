package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


/**
 *
 * @Since 2018-10-30
 */
public class UIImageButton extends UIObject{

    private BufferedImage[] images;
    private ClickListener clicker;
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker){
        super(x,y,width, height);
        setClicker(clicker);
        setImages(images);
    }
    @Override
    public void tick(){}

    @Override
    public void render(Graphics2D g2d) {
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
}
