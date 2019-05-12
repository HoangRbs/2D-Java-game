package UserInterface;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UI_Object {
    private int x,y;
    private int width, height;
    protected Rectangle UI_box;
    protected boolean hovering = false;

    public UI_Object(int x, int y,int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;

        UI_box = new Rectangle(this.x,this.y,this.width,this.height);
    }

    public abstract void Update();

    public abstract void Render(Graphics m_RealScreenObject);

    protected abstract void on_ClickToDo();    //the UI was clicked on to do something

    public void on_MouseMoving(MouseEvent e)     //check if Mouse moving on UI_box through MOUSE EVENT
    {
        if(UI_box.contains(e.getX(),e.getY()))
        {
            hovering = true;
        }
        else
        {
            hovering = false;
        }
    }

    public void on_MouseReleased(MouseEvent e) //if a Mouse released -->
                                               // it means that the mouse clicked on UI previously
    {
        //check this later
        if(hovering)     //released on the UI section
        {
            on_ClickToDo();
        }
    }
}
