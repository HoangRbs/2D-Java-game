package TicTacToe;

import BaseMainGame.GameHandler;
import UserInterface.UI_ImageButton;
import UserInterface.UI_Object;
import gfx.Assets;

import java.awt.*;

public class Cell {
    public float posX;
    public float posY;
    public char Symbol; // '' or 'X' or 'O'
    public UI_Object Cell_ImageButton;
    public Rectangle BoundingBox;

    public Cell()
    {
        BoundingBox = new Rectangle();
    }

    public void Update()
    {
        //if Symbol is already == 'X' or 'O' --> do not change anymore
        if(Symbol != ' ')
            return;

        //is hovering on the current Cell
        if(BoundingBox.contains(GameHandler.GetInstance().get_m_MouseManager().getMouse_x(),
                                GameHandler.GetInstance().get_m_MouseManager().getMouse_y()))
        {
            //click on the cell
            if(GameHandler.GetInstance().get_m_MouseManager().get_isLeftMouse_Pressed())
            {
                Symbol = 'X';
            }
            if(GameHandler.GetInstance().get_m_MouseManager().get_isRightMouse_Pressed())
            {
                Symbol = 'O';
            }
        }
    }

    public void Render(Graphics m_RealScreenObject)
    {

        if(Symbol == 'X')
        {
            m_RealScreenObject.drawImage(Assets.GetInstance().Xsymbol,(int)(posX + 2),(int)(posY + 2),64 - 4,64 - 4,null);
        }
        else if(Symbol == 'O')
        {
            m_RealScreenObject.drawImage(Assets.GetInstance().Osymbol,(int)(posX + 2),(int)(posY + 2),64 - 4,64 - 4,null);
        }
    }
}
