package state;

import java.awt.*;
import main.Handler;
import ui.Background;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import Graphics.Assets;

/**
 * @Since 2018-10-17
 */
public class MenuState extends State{
    private Background background;
    private UIManager uiManager;
	public MenuState(Handler handler){
		super(handler);
        uiManager = new UIManager(handler, 0);
        handler.getMouseManager().setUiManager(uiManager);
        background = new Background(handler);
        background.setBackground(Assets.cat,0,0,handler.getWidth(),handler.getHeight());
		uiManager.addObject(new UIImageButton(50, 50, 225, 37, Assets.buttonStartGame, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().getGameState());
            }
        }, 0));
		uiManager.addObject(new UIImageButton(50, 100, 225, 37, Assets.buttonInventory, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().getEpilogueState());
            }
        }, 0));
	}
	
	@Override
	public void tick() {
	    uiManager.tick();
        background.tick();
	}
	
	@Override
	public void render(Graphics2D g2d) {
        background.render(g2d);
	    uiManager.render(g2d);

	}

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }
}
