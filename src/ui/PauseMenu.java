package ui;

import main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import Graphics.Assets;

/**
 * This handles the behavior of the pause menu
 * The rendering is done by the uimanager
 *
 */
public class PauseMenu {

    private Handler handler;
    private boolean active= false;
    private UIManager uiManager;
    public PauseMenu(Handler handler, UIManager uiManager){
        this.handler = handler;
        this.uiManager = uiManager;
        System.out.println(uiManager);
        uiManager.addObject(new UIImageButton(50,50,Assets.exitButton[0].getWidth(), Assets.exitButton[0].getHeight(), Assets.exitButton,new ClickListener(){
            @Override
            public void onClick(){
                System.exit(0);
            }
        },0));

    }

    public void tick(){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                active = !active;
            }

    }

    public void render(Graphics2D g2d){
        if(!active){
            return;
        }
        //Render below here

    }

    //Getters and Setters below
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean getActive(){
        return active;
    }
}
