package TicTacToe;

import BaseMainGame.GameHandler;
import TicTacToe.AI.AI_Player;
import UserInterface.UI_ImageButton;
import UserInterface.UI_Object;
import gfx.Assets;

import java.awt.*;

public class Cell {
    public static final int CellSize = 80;

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
            //we click X on the cell
            if(GameHandler.GetInstance().get_m_MouseManager().get_isLeftMouse_Pressed())
            {
                Symbol = 'X';
                basicSystem.Xturn = false;  //after we done with X

                Player.JustTickCell = this;
            }
        }
    }

    public void Render(Graphics m_RealScreenObject)
    {
        if(Symbol == 'X')
        {
            m_RealScreenObject.drawImage(Assets.GetInstance().Xsymbol,(int)(posX + 2),(int)(posY + 2),CellSize - 4,CellSize- 4,null);
        }
        else if(Symbol == 'O')
        {
            m_RealScreenObject.drawImage(Assets.GetInstance().Osymbol,(int)(posX + 2),(int)(posY + 2),CellSize - 4,CellSize - 4,null);
        }
    }
}
