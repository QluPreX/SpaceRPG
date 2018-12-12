package input;

import ui.UIManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @Since 2018-10-30
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean leftDragging;
    private boolean triggerMove=false;
    private boolean triggerDrag=false;
    private boolean leftReleasedAfterMovement;
    private boolean leftJustReleased=false;
    private boolean _lrah =true;
    private boolean firstPress = false;
    private boolean[] pressing = {false,false}; //0: left, 1:right;
    private int mouseX;
    private int mouseY;
    private UIManager uiManager;
    public MouseManager(){

    }
    public boolean isleftJustReleased(){
        if(leftJustReleased && _lrah){
            _lrah = false;
            return leftJustReleased;
        }else{
            return false;
        }

    }
    public boolean isleftReleasedAfterMovement(){
        return leftReleasedAfterMovement;
    }
    public boolean isLeftButtonBeingPressed(){

        return pressing[0];
    }
    public boolean isRightButtonBeingPressed(){
        return pressing[1];
    }
    public boolean isMouseMoved(){
        if(triggerMove){
            triggerMove = false;
            return true;
        }
        return false;
    }
    public boolean isLeftDragging(){
        return leftDragging;
    }

    public boolean isLeftPressed(){
        return leftPressed;
    }
    public boolean isRightPressed(){
        return rightPressed;
    }
    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }
    //Implemeted methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("PRESS");
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
            pressing[0] = leftPressed;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
            pressing[1] = rightPressed;
        }
        if(isMouseMoved()) {
            triggerDrag = true;
        }
        leftJustReleased = false;
        _lrah = false;
        firstPress = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
            pressing[0] = false;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
            pressing[1] = false;
        }
        if(triggerDrag){
            leftReleasedAfterMovement = true;
            triggerDrag= false;
        }else{
            leftReleasedAfterMovement = false;
        }

        if(uiManager != null){
            uiManager.onMouseRelease(e);
        }
        if(firstPress){
            leftJustReleased = true;
            _lrah = true;
        }else{
            leftJustReleased = false;
            _lrah = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        leftDragging = e.getButton() == MouseEvent.BUTTON1;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        triggerMove = true;
        mouseX = e.getX();
        mouseY = e.getY();
        if(uiManager != null){
            uiManager.onMouseMove(e);
        }
    }
    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

}
