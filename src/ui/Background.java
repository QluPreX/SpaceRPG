package ui;

import Graphics.Tiles.Tile;
import main.Handler;
import Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Text;

/**
 *
 * @Since 2018-10-30
 */
public class Background {
    int width, height,x,y;
    private BufferedImage img;
    private Handler handler;
    private BufferedImage nextBackground;
    private float transitionSpeed;
    private BufferedImage imgMask,faded;
    private Graphics2D g2d;
    private int _x = 1;
    private int opacity = 255;
    private long lastTimer, Cooldown = 800/*ms*/, Timer = Cooldown;
    private boolean fade = false;
    public Background(Handler handler){
        this.width = handler.getGame().getWidth();
        this.handler = handler;
        this.height = handler.getGame().getHeight();
        this.g2d = handler.getGame().getG2d();
    }
    public void setBackground(BufferedImage img, int x, int y, int width, int height){
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        faded = null;
    }
    public void setFadedBackground(BufferedImage img, int x, int y, int width, int height){
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        imgMask = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        opacity = 255;
    }
    public void tick() {
        if(fade){
            if(imgMask != null){
                if(opacity >= 150){
                    Timer += System.currentTimeMillis() - lastTimer +opacity;
                    lastTimer = System.currentTimeMillis();
                    if (Timer < Cooldown) {
                        return;
                    }else{
                        Timer = 0;
                        opacity--;
                    }
                }
                transistion();
            }
        }

    }

    public void render(Graphics2D g2d) {
        if(fade){
            g2d.drawImage(faded,x,y,width,height,null);
        }else{
            g2d.drawImage(img, x,y, width, height, null);
        }
    }
    public void transistion(){
        setTransition();
        faded = applyMask(img, imgMask, AlphaComposite.DST_IN);
    }

    public void setTransition(){
        g2d = imgMask.createGraphics();
        LinearGradientPaint lgp  =  new LinearGradientPaint(
                new Point(-200,0),
                new Point(imgMask.getWidth(),0),
                new float[]{0,1},
                new Color[]{new Color(0,0,0,0), new Color(0,0,0,opacity)}
        );
        g2d.setPaint(lgp);
        g2d.fillRect(0,0,imgMask.getWidth(),imgMask.getHeight());
        g2d.dispose();
    }


    public static BufferedImage applyMask(BufferedImage sourceImage, BufferedImage maskImage, int method) {

        BufferedImage maskedImage = null;
        if (sourceImage != null) {

            int width = maskImage.getWidth();
            int height = maskImage.getHeight();

            maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D mg = maskedImage.createGraphics();
            int x = (width - sourceImage.getWidth()) / 2;
            int y = (height - sourceImage.getHeight()) / 2;

            mg.drawImage(sourceImage, x, y, null);
            mg.setComposite(AlphaComposite.getInstance(method));
            mg.drawImage(maskImage, 0, 0, null);

            mg.dispose();
        }

        return maskedImage;

    }
    public void setFade(boolean fade){
        this.fade = fade;
    }
}
