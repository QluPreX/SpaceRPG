package utilities;

import java.io.*;

/**
 * The Util class, stands for Utilities.
 * Is a class with all the easy Utilities that are used throughout the whole project
 * @Since 2018-10-17
 */
public class Utils {
    /**
     * The loadFileAsString loads a File object and reads all the lines and puts it in a StringBuilder Array.
     * This is used for reading world files.
     * @param path This a File object, this file needs to be readable by UTF-8 (or ASCII at least)
     * @return
     */
    public static String loadFileAsString(File path){

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader( new FileReader(path));
            String line;
            while((line = br.readLine())!= null) {
                builder.append(line + "\n");
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }
    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

}