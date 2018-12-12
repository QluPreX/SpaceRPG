package state;

import main.Handler;
import ui.Background;
import ui.InteractScreen;
import ui.UIManager;
import Graphics.Assets;

import java.awt.*;

public class EpilogueState extends State{

    private UIManager uiManager;
    private InteractScreen interactScreen;
    private Background background;
    public EpilogueState(Handler handler) {
        super(handler);
        background = new Background(handler);
        uiManager  = new UIManager(handler);
        interactScreen = new InteractScreen(handler);
        handler.getMouseManager().setUiManager(uiManager);
        startSeq();
    }

    public void startSeq(){
        interactScreen.setActive(true);
        setSeq();
    }

    public void setSeq(){
        background.setFade(true);
        background.setBackground(Assets.cat, 0,0, handler.getWidth(), handler.getHeight());
        background.setFadedBackground(Assets.menuBg,0,0,handler.getWidth(),handler.getHeight());
        interactScreen.addMessage(new String[]{"Welcome","\n","To the game!"});
    }

    @Override
    public void tick() {
        uiManager.tick();
        interactScreen.tick();
        background.tick();
    }

    @Override
    public void render(Graphics2D g2d) {
        background.render(g2d);
        uiManager.render(g2d);
        interactScreen.render(g2d);
    }
}
