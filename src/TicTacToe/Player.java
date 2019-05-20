package TicTacToe;

import java.awt.*;

public class Player {

    public static void Update()
    {
        for (int Yindex = 0; Yindex < basicSystem.BoardSize; Yindex++) {
            for (int Xindex = 0; Xindex < basicSystem.BoardSize; Xindex++) {
                Cell currentCell = basicSystem.Board[Yindex][Xindex];
                currentCell.Update();  //CELL update is us tick X
            }
        }

    }

    public static void Render(Graphics m_RealScreenObject)
    {
        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < basicSystem.BoardSize;Xindex++)
            {
                Cell currentCell = basicSystem.Board[Yindex][Xindex];
                currentCell.Render(m_RealScreenObject);
            }
        }
    }
}
