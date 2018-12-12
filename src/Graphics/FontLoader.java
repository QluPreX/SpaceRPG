package Graphics;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FontLoader {
    public static Font loadFont(String path, float size){
        File _path = new File(FontLoader.class.getClassLoader().getResource(path).getPath());
        try{
            return Font.createFont(Font.TRUETYPE_FONT, _path).deriveFont(Font.PLAIN, size);

        }catch(FontFormatException | IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
