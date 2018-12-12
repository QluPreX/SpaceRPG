package ui;

import items.Item;
import main.Handler;
import Graphics.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import Graphics.Worlds.TestWorld;

/**
 *Inventory calss manages everything regards the inventoryscreen:
 *
 * @Since 2018-11-2
 */
public class Inventory {

    private Handler handler;
    private boolean active = false;//--------skips render() when false, continues when true
    private boolean pressedItem = false;//---true when an item is being pressed or dragged
    private ArrayList<Item> inventoryItems;//the arraylist of items
    private int Xpos, yPos;//----------------X and Y position of the inventory asset
    private int width, height;//-------------size of the inventory asset
    private int XoffsetGrid, YoffsetGrid;//--X and Y offset of the grid from the inventory asset.
    private int widthGrid,heightGrid;//------size of a grid display
    private int countX,countY;//-------------X and Y position of the count number for the item
    private int xTextOffset, yTextOffset;//--X and Y offset on the tooltip text
    private int textBgWidth, textBgHeight;//-size of the tooltip
    private int xSpacing, ySpacing;//--------X and Y space between each grid
    private String currentTooltip;//---------the name of the item currently hovering
    private boolean drawTooltip = false;//---no tooltip rendered if false
    private int counterInvItems = 0;//-------grid and item render checker
    private int rowTiles = 5;//--------------
    private int collumnTiles = 5;//----------
    private Item[] gridSlots;//--------------
    private Item itemPressing;//-------------
    private int indexItemPressing =-1;//---------
    private Item waitToRemove;//-------------
    private boolean justReleased = false;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        setHeight(Assets.inventoryScreen.getHeight());
        setWidth(Assets.inventoryScreen.getWidth());
        setXpos(handler.getGame().getWidth()-getWidth());
        setyPos(handler.getGame().getHeight()-getHeight());
        setSpacing(55);
        setOffsetGrid(13);
        setGrid(50);
        setCount(36,45);
        setBgCount(5,17,20,20);
        gridSlots = new Item[rowTiles*collumnTiles];
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }

        if(getItemToRemove() !=null){
            inventoryItems.remove(getItemToRemove());
            setItemToRemove(null);
        }
        if(!active){
            return;
        }
        if(handler.getMouseManager().isleftJustReleased()){
            justReleased = true;
        }else{
            justReleased = false;
        }
    }

    public void render(Graphics2D g2d){
        if(!active){
            return;
        }
        g2d.drawImage(Assets.inventoryScreenEmpty,getXpos(),getyPos(), getWidth(),getHeight(),null );
        //Draw Grid on inventory
        for(int y = 0; y < rowTiles;y++ ){
            for(int x = 0; x < collumnTiles; x++){
                if(getHoverSlotNr()==x+(y*collumnTiles)){
                    g2d.setPaint(Color.BLUE);
                }else{
                    g2d.setPaint(Color.BLACK);
                }
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(getXpos()+getXoffsetGrid()+(x*getxSpacing()), getyPos()+getYoffsetGrid()+(y*getySpacing()),50,50,5,5);
                g2d.setStroke(new BasicStroke(0));
            }
        }
        //Draw items
        setCounterInvItems(0);
        for(Item e : gridSlots){
            boolean isDrawn = false;
            for(int y = 0; y < rowTiles; y++){
                for(int x = 0; x < collumnTiles; x++){
                    if(((x+(y*collumnTiles) == counterInvItems)&& !isDrawn)){
                        //int _in = x+(y*collumnTiles);
                        //System.out.println("Drawing slot nr "+_in);
                        isDrawn = true;
                        int xText = getXpos()+getXoffsetGrid()+(x*getxSpacing())+getCountX();
                        int yText = getyPos()+getYoffsetGrid()+(y*getySpacing())+getCountY();
                        int xImg = getXpos()+getXoffsetGrid()+(x*getxSpacing());
                        int yImg = getyPos()+getYoffsetGrid()+(y*getySpacing());
                        int mouseX = handler.getMouseManager().getMouseX();
                        int mouseY = handler.getMouseManager().getMouseY();
                        if((mouseX > xImg)&&(mouseY > yImg)&&(mouseX < xImg+getWidthGrid())&&(mouseY <yImg+getHeightGrid())){
                            if(handler.getMouseManager().isLeftPressed() && !pressedItem && e!=null){
                                itemPressing = e;
                                pressedItem = true;
                                setItemToRemove(e);
                                indexItemPressing = x+(y*collumnTiles);
                                //System.out.println("indexItem is: "+indexItemPressing);
                                break;
                            }else if (e!=null){
                                drawTooltip= true;
                                currentTooltip=e.getName();
                            }
                            if(justReleased) {
                                //System.out.print("triggered Just Released in Render() ");
                                if(itemPressing !=null){
                                    //System.out.print("\nchanging \""+itemPressing.getName()+"\" (slot nr: "+indexItemPressing+")");
                                    changeSlotItem(itemPressing,indexItemPressing);
                                }else{
                                    //System.out.print(" but had no item. interrupted\n");
                                }
                            }
                        }

                        if(e != null && !(itemPressing==e)){
                            g2d.drawImage(e.getTexture(), xImg, yImg,getWidthGrid(), getHeightGrid(),null);
                            g2d.setColor(Color.WHITE);
                            g2d.fillOval(xText-getxTextOffset(),yText-getyTextOffset(),getTextBgWidth(),getTextBgHeight());
                            g2d.setColor(Color.BLACK);
                            g2d.drawOval(xText-getxTextOffset(),yText-getyTextOffset(), getTextBgWidth(),getTextBgHeight());
                            Text.drawString(g2d, Integer.toString(e.getCount()), xText, yText,false,Color.BLACK,Assets.getFont(getTextBgHeight()));
                        }
                    }
                }
            }
            counterInvItems++;
        }
        if(pressedItem) {
            g2d.drawImage(itemPressing.getTexture(), handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(),getWidthGrid(), getHeightGrid(),null);
            if(!handler.getMouseManager().isLeftButtonBeingPressed()){
                inventoryItems.add(itemPressing);
                itemPressing = null;
                pressedItem = false;
            }
        }
        ///HAVE TO FIX GLITCH,use handler.getMouseManager().isLeftReleasedAfterMovement() !not working!
        if(drawTooltip){
            if(!handler.getMouseManager().isLeftPressed()){
                renderTooltip(g2d);
            }
            drawTooltip=false;
        }
    }
    public void renderTooltip(Graphics2D g2d){
        g2d.drawImage(Assets.inventoryTooltip,handler.getMouseManager().getMouseX(),handler.getMouseManager().getMouseY(),Assets.inventoryTooltip.getWidth(),Assets.inventoryTooltip.getHeight(),null);
        Text.drawString(g2d,currentTooltip,handler.getMouseManager().getMouseX()+50,handler.getMouseManager().getMouseY()+15,true, Color.BLACK,Assets.getFont(15));
    }

    public void addItem(Item item){
       for(Item i: inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
       }
       inventoryItems.add(item);
       giveItemSlot(item);
    }

    private void giveItemSlot(Item i){
        for(int e = 0; e < gridSlots.length; e++ ){
            if(gridSlots[e] == null){
                gridSlots[e] = i;
                return;
            }
        }
    }
    //useful and handy methods
    public void setBgCount(int xOffset, int yoffset, int width, int height){
        setxTextOffset(xOffset);
        setyTextOffset(yoffset);
        setTextBgHeight(height);
        setTextBgWidth(width);
    }
    public void setSpacing(int x,int y){
        setxSpacing(x);
        setySpacing(y);
    }
    public void setSpacing(int amount){
        setxSpacing(amount);
        setySpacing(amount);
    }
    public void setOffsetGrid(int x, int y){
        setXoffsetGrid(x);
        setYoffsetGrid(y);
    }
    public void setOffsetGrid(int amount){
        setXoffsetGrid(amount);
        setYoffsetGrid(amount);
    }
    public void setGrid(int amount){
        setWidthGrid(amount);
        setHeightGrid(amount);
    }
    public void setGrid(int width, int height){
        setWidthGrid(width);
        setHeightGrid(height);
    }
    public void setCount(int x, int y){
        setCountX(x);
        setCountY(y);
    }
    public void setCount(int amount){
        setCountX(amount);
        setCountY(amount);
    }

    private void changeSlotItem(Item i, int oldSlot){
        int newSlot = getHoverSlotNr();
        if(newSlot != -1){
            if(gridSlots[newSlot] !=null){
                gridSlots[oldSlot] = gridSlots[newSlot];
            }else{
                gridSlots[oldSlot] = null;
            }
            gridSlots[newSlot] = i;
            //System.out.println("\nusing Item \""+i.getName()+"\" setting this item into slot nr:" + newSlot);
            //System.out.println("\nnew slot: "+gridSlots[newSlot].getName()+", old slot: "+gridSlots[oldSlot]);
        }
    }

    private int getHoverSlotNr(){
        int gxStart,gyStart;
        int mx = handler.getMouseManager().getMouseX();
        int my = handler.getMouseManager().getMouseY();
        int cx = mx-getXpos()-getXoffsetGrid();
        int cy = my-getyPos()-getYoffsetGrid();
        int[] gridsize = {Assets.inventoryGrid.getWidth(),Assets.inventoryGrid.getHeight()};
        if(cx > 0 && cy > 0){//Mouse is inside Grid
            for(int y = 0; y < rowTiles; y++){
                for(int x = 0; x < collumnTiles; x++) {
                    gxStart = (x*getxSpacing());
                    gyStart = (y*getySpacing());

                    if(cx >= gxStart && cy >= gyStart   &&   cx <= gxStart+(getxSpacing()-(getxSpacing()-gridsize[0]))
                            && cy <= gyStart+(getySpacing()-(getySpacing()-gridsize[1])) ){
                        return x+(y*collumnTiles);
                    }
                }
            }
        }
        return -1;
    }

    //getters and setters
    public Handler getHandler() {
        return handler;
    }

    public int getXpos() {
        return Xpos;
    }

    public void setXpos(int xpos) {
        Xpos = xpos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getxSpacing() {
        return xSpacing;
    }

    public void setxSpacing(int xSpacing) {
        this.xSpacing = xSpacing;
    }

    public int getySpacing() {
        return ySpacing;
    }

    public void setySpacing(int ySpacing) {
        this.ySpacing = ySpacing;
    }

    public int getXoffsetGrid() {
        return XoffsetGrid;
    }

    public void setXoffsetGrid(int xoffsetGrid) {
        XoffsetGrid = xoffsetGrid;
    }

    public int getYoffsetGrid() {
        return YoffsetGrid;
    }

    public void setYoffsetGrid(int yoffsetGrid) {
        YoffsetGrid = yoffsetGrid;
    }

    public int getWidthGrid() {
        return widthGrid;
    }

    public void setWidthGrid(int widthGrid) {
        this.widthGrid = widthGrid;
    }

    public int getHeightGrid() {
        return heightGrid;
    }

    public void setHeightGrid(int heightGrid) {
        this.heightGrid = heightGrid;
    }

    public int getCountX() {
        return countX;
    }

    public void setCountX(int countX) {
        this.countX = countX;
    }

    public int getCountY() {
        return countY;
    }

    public void setCountY(int countY) {
        this.countY = countY;
    }

    public int getxTextOffset() {
        return xTextOffset;
    }

    public void setxTextOffset(int xTextOffset) {
        this.xTextOffset = xTextOffset;
    }

    public int getyTextOffset() {
        return yTextOffset;
    }

    public void setyTextOffset(int yTextOffset) {
        this.yTextOffset = yTextOffset;
    }

    public int getTextBgWidth() {
        return textBgWidth;
    }

    public void setTextBgWidth(int textBgWidth) {
        this.textBgWidth = textBgWidth;
    }

    public int getTextBgHeight() {
        return textBgHeight;
    }

    public void setTextBgHeight(int textBgHeight) {
        this.textBgHeight = textBgHeight;
    }

    public int getCounterInvItems() {
        return counterInvItems;
    }

    public void setCounterInvItems(int counterInvItems) {
        this.counterInvItems = counterInvItems;
    }


    public void setItemToRemove(Item waitToRemove) {
         this.waitToRemove = waitToRemove;
    }

    public Item getItemToRemove(){
        return waitToRemove;
    }
}
