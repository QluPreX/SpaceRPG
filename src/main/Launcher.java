package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @Since 2018-10-17
 */
public class Launcher {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try {
            fh = new FileHandler("MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info("START-LOG");
        } catch (SecurityException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        Game game = new Game("test", 800, 600);
        game.giveLogger(logger);
        game.start();
        
    }
}