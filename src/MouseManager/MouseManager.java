package MouseManager;

import UserInterface.UI_Manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean isLeftMouse_Pressed, isRightMouse_Pressed;
    private int Mouse_x,Mouse_y;
    private UI_Manager currentFocusingUIManager = null;

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            isLeftMouse_Pressed = true;
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            isRightMouse_Pressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            isLeftMouse_Pressed = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            isRightMouse_Pressed = false;
        }

        if(currentFocusingUIManager != null)    //we don't want our game to crash
        {
            currentFocusingUIManager.on_MouseReleased(e);   //pass the WORKING MOUSE EVENT into our UIManager
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Mouse_x = e.getX();
        Mouse_y = e.getY();

        if(currentFocusingUIManager != null)
        {
            currentFocusingUIManager.on_MouseMoving(e);  //pass the WORKING MOUSE EVENT into our UIManager
        }
    }

    public boolean get_isLeftMouse_Pressed()
    {
        return isLeftMouse_Pressed;
    }

    public boolean get_isRightMouse_Pressed()
    {
        return isRightMouse_Pressed;
    }

    public int getMouse_x()
    {
        return Mouse_x;
    }

    public int getMouse_y()
    {
        return Mouse_y;
    }

    public void set_FocusingOnUIManager(UI_Manager currentFocusingUIManager)
    {
        this.currentFocusingUIManager = currentFocusingUIManager;
    }

    //no need the below functions yet
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
