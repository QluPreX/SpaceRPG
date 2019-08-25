package ui;

import entities.Entity;
import entities.npcs.Dog;
import main.Handler;
import Graphics.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 *
 * @Since 2018-11-4
 */
public class InteractScreen {

    private Handler handler;
    private boolean active = false;
    private ArrayList<String> messageList;
    private Entity currentEnt = null;


    private boolean MessageWait = false;

    private int spacingWait = 0;
    /////////////////////
    private int imageX, ImageY, imageWidth, imageHeight;
    private int nameX, nameY;
    private int textX, textY, spacing;
    private int screenX, screenY, screenWidth, screenHeight;
    /////////////////
    public InteractScreen(Handler handler){
        this.handler  = handler;
        messageList = new ArrayList<String>();

        //the assets size 800 x 350
        setScreenWidth(Assets.interactScreen.getWidth()/2);
        setScreenHeight(Assets.interactScreen.getHeight()/2);
        setScreenX((handler.getWidth()/2)-(getScreenWidth()/2));
        setScreenY(handler.getHeight()-getScreenHeight());

        setNameX(getScreenX()+57);
        setNameY(getScreenY()+122);

        setTextX(getScreenX()+121);
        setTextY(getScreenY()+35);

        setImageX(getScreenX()+20);
        setImageY(getScreenY()+30);

        setSpacing(20);
    }

    public void addMessage(String[] msg, Entity thisEnt){
        if(!messageList.isEmpty()){
            return;
        }else {
            for (String s : msg) {
                messageList.add(s);
            }
            setCurrentEnt(thisEnt);
        }
    }
    public void addMessage(String[] msg){
        if(!messageList.isEmpty()){
            return;
        }else {
            for (String s : msg) {
                messageList.add(s);
            }
            setCurrentEnt(null);
        }
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) {
            if(active){
                resetMessageList();
            }
            active = !active;
        }
        if(!active){
            return;
        }
    }

    public void render(Graphics2D g2d){
        if(!active){
            return;
        }
        if(messageList.isEmpty()){
            return;
        }
        //textures
        if(currentEnt != null){
            g2d.drawImage(Assets.interactScreen, getScreenX(),getScreenY(),getScreenWidth(), getScreenHeight(),null);
            if(currentEnt.getHeight() >= 87 || currentEnt.getWidth() >= 97 ){
                setImageHeight(87);
                setImageWidth(97);
            }else {
                setImageHeight(currentEnt.getHeight());
                setImageWidth(currentEnt.getWidth());
            }
            if(currentEnt instanceof Dog){
                g2d.drawImage(((Dog) currentEnt).getInteractFace(), getImageX()-20, getImageY()-20, getImageWidth()+40, getImageHeight()+40, null);
            }else {
                g2d.drawImage(currentEnt.getTexture(), getImageX(), getImageY(), getImageWidth(), getImageHeight(), null);
            }
        }else{
            g2d.drawImage(Assets.interactScreenStory,getScreenX(),getScreenY(),getScreenWidth(),getScreenHeight(),null);

        }

        //Texts
        int len = messageList.size();
        if(len > 7){ //MAX 7 LINES OF STRINGS
            len = 7 ;
        }
        for(int i = 0; i < len; i++) {
            if (g2d.getFontMetrics().stringWidth(messageList.get(i)) > 154) { //MAX 154px WIDTH
                System.out.println("error: interactive screen: text too long! ID 1");
                System.exit(-1);
            } else {
                if(currentEnt !=null){
                    Text.drawString(g2d, messageList.get(i), getTextX(), getTextY()+getSpacing()*(i-spacingWait), false, Color.BLACK, Assets.getFont(18));
                }else{
                    Text.drawString(g2d,messageList.get(i),getScreenX()+10,getScreenY()+30+getSpacing()*(i-spacingWait),false,Color.BLACK,Assets.getFont(18));
                }
            }
        }if(currentEnt !=null){
            if(g2d.getFontMetrics().stringWidth(currentEnt.getName()) > 134){
                System.out.println("error: interactive screen: text too long! ID 2");
                    System.exit(-1);
            }else{
                Text.drawString(g2d,currentEnt.getName(), getNameX(),getNameY(),true, Color.BLACK, Assets.getFont(12));
            }
        }

    }

    public void setCurrentEnt(Entity currentEnt) {
        this.currentEnt = currentEnt;
    }
    public void resetMessageList(){
        int len = messageList.size();
        if(len > 7){
            len = 7;
        }
        for (int j = len - 1; j >= 0; j--) {
            messageList.remove(j);
        }
        if(messageList.isEmpty()){
            spacingWait = 0;
        }
    }
    //getters and setters below


    public int getImageX() {
        return imageX;
    }

    public void setImageX(int imageX) {
        this.imageX = imageX;
    }

    public int getImageY() {
        return ImageY;
    }

    public void setImageY(int imageY) {
        ImageY = imageY;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getNameX() {
        return nameX;
    }

    public void setNameX(int nameX) {
        this.nameX = nameX;
    }

    public int getNameY() {
        return nameY;
    }

    public void setNameY(int nameY) {
        this.nameY = nameY;
    }

    public int getTextX() {
        return textX;
    }

    public void setTextX(int textX) {
        this.textX = textX;
    }

    public int getTextY() {
        return textY;
    }

    public void setTextY(int textY) {
        this.textY = textY;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Entity getCurrentEnt() {
        return currentEnt;
    }

    public boolean getMessageWait() {
        return MessageWait;
    }

    public void setMessageWait(boolean MessageWait) {
        this.MessageWait = MessageWait;
        if(!MessageWait){
            spacingWait = 1;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
