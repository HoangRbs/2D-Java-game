package TicTacToe;

import BaseMainGame.Game;
import BaseMainGame.GameHandler;
import States.State;
import UserInterface.UI_ImageButton;
import UserInterface.UI_Manager;
import gfx.Assets;

import java.awt.*;

public class TicTacToeGameState extends State {

    private int BoardSize = 3;
    private Cell[][] Board = new Cell[BoardSize][BoardSize];
    private UI_Manager m_UIManager = new UI_Manager();

    public TicTacToeGameState()
    {
        GameHandler.GetInstance().get_m_MouseManager().set_FocusingOnUIManager(m_UIManager);

        for(int y = 0; y < BoardSize;y++)
        {
            for(int x = 0; x < BoardSize; x++)
            {
                Board[y][x] = new Cell();
                Cell currentCell = Board[y][x];
                currentCell.posX = x;
                currentCell.posY = y;
                currentCell.Symbol = ' ';   // 'X'  or 'O'  or  ' '
                currentCell.Cell_ImageButton = new UI_ImageButton(x * 64,y * 64,64,64,
                                        Assets.GetInstance().UI_TicTacToeCellButtonImages,null);

                m_UIManager.AddUIObject(currentCell.Cell_ImageButton);
            }
        }
    }

    @Override
    public void Update() {

        m_UIManager.Update();

        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < BoardSize;Xindex++)
            {
                Cell currentCell = Board[Yindex][Xindex];
                currentCell.Update();
            }
        }
    }

    @Override
    public void Render(Graphics m_RealScreenObject) {

        m_UIManager.Render(m_RealScreenObject);

        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < BoardSize;Xindex++)
            {
                Cell currentCell = Board[Yindex][Xindex];
                currentCell.Render(m_RealScreenObject);
            }
        }
    }

    @Override
    public void SetGameObject(Game _GameObject) {

    }
}
