package Graphics;

import java.awt.*;

public class Text {

    public static void drawString(Graphics2D g2d, String text, int xPos, int yPos, boolean center, Color color, Font font){
        g2d.setColor(color );
        g2d.setFont(font);
        int y = yPos;
        int x = xPos;
        if(center){
            FontMetrics fm = g2d.getFontMetrics(font);
            x = xPos - fm.stringWidth(text)/2;
            y = (yPos - fm.getHeight() /2) + fm.getAscent();
        }
        g2d.drawString(text, x,y);
    }

}
