package UserInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI_ImageButton extends UI_Object{
    private BufferedImage[] images;     //START BUTTON , EXIT BUTTON
    private UI_clickFunction m_UIClickFunctionObject;   //create a FunctionObject whenever this UI is clicked

    public UI_ImageButton(int x, int y, int width, int height, BufferedImage[] images,
                                                UI_clickFunction UIClickFunction_Object)
    {
        super(x,y,width,height);
        this.images = images;
        this.m_UIClickFunctionObject = UIClickFunction_Object;
    }


    @Override
    public void Update() {

    }

    @Override
    public void Render(Graphics m_RealScreenObject) {
        if(hovering)
        {
            m_RealScreenObject.drawImage(images[1],UI_box.x,UI_box.y,UI_box.width,UI_box.height,null);
        }
        else
        {
            m_RealScreenObject.drawImage(images[0],UI_box.x,UI_box.y,UI_box.width,UI_box.height,null);
        }
    }

    @Override
    protected void on_ClickToDo() {
        if(m_UIClickFunctionObject != null)
            m_UIClickFunctionObject.on_ClickToDo();
    }
}
