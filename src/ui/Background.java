package ui;

import Graphics.Tiles.Tile;
import main.Handler;
import Graphics.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;
import Graphics.Text;
import utilities.Utils;

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
    public int _x =255;
    private BufferedImage imgMask,faded;
    private Graphics2D g2d;
    private float alpha = 0.0f;
    private boolean fade = false; //true when a faded bg is set
    private boolean fadeDraw = false; //true when a timer sets to true and draw next fade alpha
    private Utils utils;
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
        utils = new Utils();
        utils.setTimer(800);
    }
    public void tick() {
        //_x--;
        //System.out.println(_x);
        if(fade){
            fadeDraw = utils.getTimer();
        }
    }

    public void render(Graphics2D g2d) {
        if(fade){
            doFade(g2d);
        }else{
            g2d.drawImage(img, x,y, width, height, null);
        }
    }


        private void doFade(Graphics2D g2d){


            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(img,x,y,width,height,null);
            if(fadeDraw){
                alpha += 0.01f;
            }
            System.out.println(alpha);
            if (alpha >= 1.0f) {
                alpha = 1.0f;
            }

            }



    public void drawFade(Graphics2D g2d,int _x){
        LinearGradientPaint lgp  =  new LinearGradientPaint(
                new Point(0,0),
                new Point(img.getWidth(),0),
                new float[]{0,1},
                new Color[]{new Color(0,255,0,0), new Color(255,255,255,255)}
        );
        g2d.setPaint(lgp);
        g2d.fillRect(0,0,img.getWidth(),img.getHeight());
    }

    public static BufferedImage applyMask(BufferedImage sourceImage, BufferedImage maskImage, int method) {
        BufferedImage maskedImage = null;
        if (sourceImage != null) {
            int width = maskImage.getWidth();
            int height = maskImage.getHeight();
            maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D mg = maskedImage.createGraphics();
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
