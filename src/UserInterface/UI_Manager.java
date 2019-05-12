package UserInterface;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UI_Manager {

    private ArrayList<UI_Object> m_UI_Objects;

    public UI_Manager()
    {
        m_UI_Objects = new ArrayList<UI_Object>();
    }

    public void Update()
    {
        for(UI_Object o : m_UI_Objects)
        {
            o.Update();
        }
    }

    public void Render(Graphics m_RealScreenObject)
    {
        for(UI_Object o : m_UI_Objects)
        {
            o.Render(m_RealScreenObject);
        }
    }

    public void on_MouseMoving(MouseEvent e)         //MOUSE EVENT we'll will take it from the MOUSE MANAGER
                                                     //cux that is where MOUSE EVENT works
    {
        for(UI_Object o : m_UI_Objects)
        {
            o.on_MouseMoving(e);
        }
    }

    public void on_MouseReleased(MouseEvent e)
    {
        for(UI_Object o : m_UI_Objects)
        {
            o.on_MouseReleased(e);
        }
    }

    public void AddUIObject(UI_Object m_UI_Object)        //this is why we use ArrayList
    {
        m_UI_Objects.add(m_UI_Object);
    }

    public void RemoveUIObject(UI_Object m_UI_Object)
    {
        m_UI_Objects.remove(m_UI_Object);
    }
}
