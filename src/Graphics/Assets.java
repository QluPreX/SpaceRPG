package Graphics;

import javafx.scene.image.Image;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    private static Handler handler;
    //Fonts
    private static Font[] font;
	//animations
	private static BufferedImage[] playerAnimations;
	public static BufferedImage[] playerIdle ;
	public static BufferedImage[] playerWalkFront;
	public static BufferedImage[] playerWalkBack;
	public static BufferedImage[] playerWalkLeft;
	public static BufferedImage[] playerWalkRight;
	public static BufferedImage[] playerAttackFront;
	public static BufferedImage[] playerAttackBack;
	public static BufferedImage[] playerAttackLeft;
	public static BufferedImage[] playerAttackRight;
	//objects
	public static BufferedImage tree;
    public static BufferedImage rock;
    public static BufferedImage treeLog;
    public static BufferedImage woodLog;
    public static BufferedImage moonRock;
    public static BufferedImage moonDust;
    public static BufferedImage human;
    public static BufferedImage moonOre;
    //tiles
	public static BufferedImage[] moonTileBig;
	public static BufferedImage unknownTile;
	public static BufferedImage cat;
    public static SpriteSheet playerSheet;

    //UI
    public static BufferedImage[] buttonStartGame;
    public static BufferedImage[] heart;
    public static BufferedImage menuBg;
    public static BufferedImage inventoryScreen;
    public static BufferedImage interactScreen;
    public static BufferedImage[] buttonInventory;
    public static BufferedImage inventoryTooltip;
    public static BufferedImage interactScreenStory;
    public static BufferedImage inventoryGrid;
    public static BufferedImage inventoryGridSelected;
    public static BufferedImage inventoryScreenEmpty;

	public static void init() {
	    font = new Font[30+1];
        initAnimationEntities();
        initTiles();
        initStaticEntities();
        initUI();
        initBg();
        initFont();

    }
    public static void initFont(){
	    for(int i = 0; i < font.length; i++){
	        font[i] = FontLoader.loadFont("Fonts/arial.ttf", i);
        }
    }
	public static void initAnimationEntities(){
		playerSheet = new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainChar.png"));
		playerAnimations = cropFrames(playerSheet,6,4,194,129,true);
		//down, left right up
		playerIdle = new BufferedImage[] {playerAnimations[1], playerAnimations[7], playerAnimations[13], playerAnimations[19]};
        playerWalkFront = new BufferedImage[] {playerAnimations[0], playerAnimations[1], playerAnimations[2],
                        playerAnimations[3], playerAnimations[4], playerAnimations[5]};
        playerWalkLeft = new BufferedImage[] {playerAnimations[6], playerAnimations[7], playerAnimations[8],
                        playerAnimations[9], playerAnimations[10], playerAnimations[11]};
        playerWalkRight = new BufferedImage[] {playerAnimations[12], playerAnimations[13], playerAnimations[14],
                        playerAnimations[15], playerAnimations[16], playerAnimations[17]};
        playerWalkBack = new BufferedImage[] {playerAnimations[18], playerAnimations[19], playerAnimations[20],
                        playerAnimations[21], playerAnimations[22], playerAnimations[23]};
        playerAttackBack = new BufferedImage[] {new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(0,96,32,32),
                        new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(32,96,32,32)};
        playerAttackFront = new BufferedImage[] {new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(0,0,32,32),
                new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(32,0,32,32)};
        playerAttackLeft = new BufferedImage[] {new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(0,32,32,32),
                new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(32,32,32,32)};
        playerAttackRight = new BufferedImage[] {new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(0,64,32,32),
                new SpriteSheet(ImageLoader.loadImage("spriteSheets/mainCharAttack.png")).crop(32,64,32,32)};
    }

	public static void initTiles(){
		moonTileBig = new BufferedImage[]{ new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(0, 21, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(65, 21, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(130, 21, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(195, 21, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(0, 86, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(65, 86, 64, 64),
				new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(130, 86, 64, 64)};
		unknownTile = new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(195, 86, 64, 64);
		cat = new SpriteSheet(ImageLoader.loadImage("spriteSheets/moonSpriteSheet.png")).crop(0,151,100,100);
	}

	public static void initStaticEntities(){
		treeLog = new SpriteSheet(ImageLoader.loadImage("spriteSheets/ground02.png")).crop(32,0,32,32);
		rock = new SpriteSheet(ImageLoader.loadImage("spriteSheets/ground02.png")).crop(64,0,32,32);
		tree = new SpriteSheet(ImageLoader.loadImage("spriteSheets/ground02.png")).crop(0,32,32,64);
		woodLog = ImageLoader.loadImage("spriteSheets/woodlog.png");
		moonRock = ImageLoader.loadImage("spriteSheets/moonRock.png");
		moonDust = ImageLoader.loadImage("spriteSheets/moonDust.png");
		human = new SpriteSheet(ImageLoader.loadImage("spriteSheets/human.png")).crop(0,0,32 ,64);
	    moonOre = ImageLoader.loadImage("spriteSheets/moonOre.png");
	}

	public static void initUI(){
        buttonStartGame = new BufferedImage[]{new SpriteSheet(ImageLoader.loadImage("spriteSheets/GUI.png")).crop(460,200,450,74),
                                new SpriteSheet(ImageLoader.loadImage("spriteSheets/GUI2.png")).crop(460,200,450,74)};
	    heart = new BufferedImage[]{new SpriteSheet(ImageLoader.loadImage("spriteSheets/heartSpritesheet.png")).crop(0,0,64,64),
                                new SpriteSheet(ImageLoader.loadImage("spriteSheets/heartSpritesheet.png")).crop(64,0,64,64),
                                new SpriteSheet(ImageLoader.loadImage("spriteSheets/heartSpritesheet.png")).crop(128,0,64,64)};
	    inventoryScreen = ImageLoader.loadImage("spritesheets/inventory2.png");
	    interactScreen = ImageLoader.loadImage("spriteSheets/interactScreen.png");
	    buttonInventory = new BufferedImage[] {new SpriteSheet(ImageLoader.loadImage("spriteSheets/GUI2.png")).crop(562,381,255,73),
                            new SpriteSheet(ImageLoader.loadImage("spriteSheets/GUI2.png")).crop(96,476,255,73)};
        inventoryTooltip = ImageLoader.loadImage("spriteSheets/inventoryTooltip.png");
        interactScreenStory = ImageLoader.loadImage("spriteSheets/interactScreenStory.png");
        inventoryGrid = ImageLoader.loadImage("spriteSheets/inventoryGrid.png");
        inventoryGridSelected = ImageLoader.loadImage("spriteSheets/inventoryGridSelected.png");
        inventoryScreenEmpty = ImageLoader.loadImage("spriteSheets/inventory2Empty.png");
	}

	public static void initBg(){
	    menuBg = ImageLoader.loadImage("bg/currentBg.png");
    }

	public static BufferedImage[] cropFrames(SpriteSheet sheet, int collumns, int rows, int width, int height, boolean isEven) {
	    int _width = (width/collumns);
	    int _height = (height/rows);
	    int total = 0;
	    int __temp = 0;
	    int id = 0;
		if (!isEven){
			total = (collumns*rows)-1;
		}
		if (isEven) {
			total = collumns*rows;
		}
		BufferedImage[] sprites = new BufferedImage[total];
		for (int y = 0; y < rows+1; y++) {
			if (!isEven && y == rows-1) {
				for(int x = 0; x < collumns-1;x++) {
					id = x+__temp;
					sprites[id] = sheet.crop(x*_width,y*_height,_width,_height);
				}
				break;
			}else {
				for (int x = 0; x < collumns; x++) {
					id = x+__temp;
					if(y != rows) {
						sprites[id] = sheet.crop(x*_width,y*_height,_width,_height);
					}
				}
				__temp = id+1;
			}
		}
		return sprites;
	}
	public static Font getFont(int size){
	    return font[size+1];
    }

    public static void giveHandler(Handler _handler){
	    handler = _handler;
    }
}