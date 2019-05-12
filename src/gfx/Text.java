package gfx;

import java.awt.*;

public class Text {

    public static void DrawText(Graphics m_RealScreenObject,String _text,int x,int y,Color _color,Font _font)
    {
        m_RealScreenObject.setColor(_color);
        m_RealScreenObject.setFont(_font);

        m_RealScreenObject.drawString(_text,x,y);
    }
}
