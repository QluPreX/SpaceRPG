package ui;
import main.Handler;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Graphics.Text;
import Graphics.Assets;
/**
 *
 * @Since 2018-10-30
 */
public class UIManager {
    private Handler handler;
    private ArrayList<UIObject> objects;
    private boolean setAllHandlers = false;
    private PauseMenu pauseMenu;

    public UIManager(Handler handler){
        this.handler = handler;
        objects  = new ArrayList<UIObject>();

    }
    public void tick(){
        pauseMenu.tick();
        for(UIObject o : objects){
            if(!setAllHandlers){
                o.setHandler(handler);
            }
            o.tick();
        }
        setAllHandlers = true;
    }
    public void render(Graphics2D g2d){
        pauseMenu.render(g2d);
        for(UIObject o : objects) {
            o.render(g2d);
        }

        //DEBUGGING
        if(true){
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(0,0,180,25);
            Text.drawString(g2d,"X: "+Integer.toString(handler.getMouseManager().getMouseX()),10,20,false,Color.BLACK, Assets.getFont(20));
            Text.drawString(g2d,"Y: "+Integer.toString(handler.getMouseManager().getMouseY()),90,20,false,Color.BLACK, Assets.getFont(20));
        }
    }
    public void onMouseMove(MouseEvent e){
        if(handler.getGame().getMenuState().getUiManager().getObjects().isEmpty()){
            return;
        }
        for(UIObject o : handler.getGame().getMenuState().getUiManager().getObjects()){
            o.onMouseMove(e);
        }
    }
    public void onMouseRelease(MouseEvent e){
        if(handler.getGame().getMenuState().getUiManager().getObjects().isEmpty()){
            return;
        }
        for(UIObject o : handler.getGame().getMenuState().getUiManager().getObjects()){
            o.onMouseRelease(e);
        }
    }
    public void addObject(UIObject o){
        objects.add(o);
    }
    public void removeObject(UIObject o){
        objects.remove(o);
    }
    public ArrayList<UIObject> getObjects(){
        return objects;
    }


    public void setPauseMenu(PauseMenu pm){
        pauseMenu = pm;
    }

    public PauseMenu getPauseMenu(){
        return pauseMenu;
    }
}