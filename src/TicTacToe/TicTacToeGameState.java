package TicTacToe;

import BaseMainGame.Game;
import BaseMainGame.GameHandler;
import States.State;
import TicTacToe.AI.AI_Player;
import UserInterface.UI_ImageButton;
import UserInterface.UI_Manager;
import gfx.Assets;

import java.awt.*;

public class TicTacToeGameState extends State {

    private int BoardSize = basicSystem.BoardSize;
    private UI_Manager m_UIManager = new UI_Manager();

    public TicTacToeGameState()
    {
        GameHandler.GetInstance().get_m_MouseManager().set_FocusingOnUIManager(m_UIManager);

        for(int y = 0; y < BoardSize;y++)
        {
            for(int x = 0; x < BoardSize; x++)
            {
                basicSystem.Board[y][x] = new Cell();
                Cell currentCell = basicSystem.Board[y][x];
                currentCell.posX = x * Cell.CellSize;
                currentCell.posY = y * Cell.CellSize;
                currentCell.Symbol = ' ';   // 'X'  or 'O'  or  ' '
                currentCell.Cell_ImageButton = new UI_ImageButton(x * Cell.CellSize,y * Cell.CellSize,
                                                                    Cell.CellSize,Cell.CellSize,
                                        Assets.GetInstance().UI_TicTacToeCellButtonImages,null);

                currentCell.BoundingBox.x = x * Cell.CellSize;
                currentCell.BoundingBox.y = y * Cell.CellSize;
                currentCell.BoundingBox.width = Cell.CellSize;
                currentCell.BoundingBox.height = Cell.CellSize;

                m_UIManager.AddUIObject(currentCell.Cell_ImageButton);
            }
        }
    }

    @Override
    public void Update() {
        if(basicSystem.isX_Win || basicSystem.isO_Win)
        {
            return;
        }
        else if(basicSystem.isNoMoreSpace && !basicSystem.isX_Win && !basicSystem.isO_Win)
        {
            return;
        }

        m_UIManager.Update();

        if(basicSystem.Xturn && isThisFrameForPlayerUpdate)   //our Turn (X turn)
        {
            Player.Update();
            //after we tick X :
            basicSystem.check_for_Winning();
        }

        if(!basicSystem.Xturn && !isThisFrameForPlayerUpdate)  ////AI-PLAYER turn (O turn)
        {
            AI_Player.Update(); //remember to use Symbol = 'O' for render
            //After AI tick O :
            basicSystem.check_for_Winning();
        }

        isThisFrameForPlayerUpdate = !isThisFrameForPlayerUpdate;
    }

    //we'll only use Player.Update() per frame and
    //AI_Player.Update() per frame
    //not gonna use both Update() in one Frame  --> for smooth X Update and Render
    boolean isThisFrameForPlayerUpdate = true;  //initial condition since we tick X first at the beginning

    @Override
    public void Render(Graphics m_RealScreenObject) {

        m_UIManager.Render(m_RealScreenObject);

        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < basicSystem.BoardSize;Xindex++)
            {
                Cell currentCell = basicSystem.Board[Yindex][Xindex];
                currentCell.Render(m_RealScreenObject);
            }
        }

        if(basicSystem.isX_Win || basicSystem.isO_Win)
        {
            basicSystem.DrawWinningLine(m_RealScreenObject);
        }

        Credit.Render(m_RealScreenObject);
    }

    @Override
    public void SetGameObject(Game _GameObject) {

    }
}
