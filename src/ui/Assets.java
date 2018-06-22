package ui;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
	private static final int WIDTH = 40, HEIGHT = 68;
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/spriteSheets/astronaut_test_spritesheet.png"));
		player = sheet.crop(0, 0, WIDTH, HEIGHT);
	}

}
