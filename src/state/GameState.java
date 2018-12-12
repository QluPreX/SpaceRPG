package state;

import java.awt.*;
import java.io.File;
import java.lang.annotation.Native;
import java.net.URL;
import Graphics.Worlds.Moon;
import Graphics.Worlds.TestWorld;
import main.Handler;
import Graphics.Worlds.World;
import ui.PauseMenu;
import ui.UIManager;


/**
 * The gameState class has contains the game-elements/playable elements of this project
 * You can change the current world to a pre-defined world in this class.
 *
 * @Since 2018-10-17
 */

public class GameState extends State{
    private World activeWorld; // the current World the gameState class is rendering and ticking
    private UIManager uiManager;
    private PauseMenu pauseMenu;
    //Predefine here ALL worlds;
    private URL moonUrl = GameState.class.getClassLoader().getResource("worlds/moonWorld.txt");
    private Moon moon = new Moon(handler, new File(moonUrl.getPath()));
    private URL testUrl = GameState.class.getClassLoader().getResource("worlds/world2.txt");
    private TestWorld testWorld = new TestWorld(handler, new File(testUrl.getPath()));

    @Native public final static int MOON_WORLD = 0;
    @Native public final static int TEST_WORLD = 1;

    /**
     * the constructor is ONLY used inside the Game class
     * Default starting world is the Moon class
     * the UImanager is also set here, but is not used (yet)
     * @param handler given Handler
     */
	public GameState(Handler handler) {
		super(handler);
		activeWorld = moon;
        handler.setWorld(activeWorld);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        pauseMenu = new PauseMenu(handler, uiManager);
	}

    /**
     * This class's tick method
     * currently ticks only the active world
     */
	@Override
	public void tick() {
		activeWorld.tick();
		pauseMenu.tick();
	}

    /**
     * This class's render method
     * currently renders only the active world
     * @param g2d
     */
	@Override
	public void render(Graphics2D g2d) {
		activeWorld.render(g2d);
	    pauseMenu.render(g2d);
	}

    /**
     * this method is used mostly by transfer-StaticObjects-Enitities such as doors.
     *  It calls the cleaerEntities method in order to make the next world with no entitities.
     * @param nextWorld
     */
    public void changeWorld(World nextWorld){
        if(nextWorld != null){
            activeWorld = nextWorld; //change active world
            handler.setWorld(nextWorld);
            handler.getGame().getEntityManager().clearEntities();
        }
    }

    /**
     * A simple getter using an final Integer ID as world
     * @param ID final int of the pre-defined world
     * @return a world, when ID is not found, null
     */
	public World getWorld(final int ID){
        switch (ID) {
            case 0:
                return this.moon;
            case 1:
                return this.testWorld;
            default:
                return null;
        }
    }


    //getters and setters below


    public UIManager getUiManager() {
        return uiManager;
    }

    public World getActiveWorld() {
        return activeWorld;
    }

}
