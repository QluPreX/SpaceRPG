package ui;

import main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import Graphics.Assets;

/**
 * This handles the PauseMenu rendering.
 * Note: Not the PauzeMenu button or setting it active/not-active
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
        }));

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
        uiManager.render(g2d);
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

}
