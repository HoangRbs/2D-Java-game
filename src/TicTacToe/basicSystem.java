package TicTacToe;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class basicSystem {
    private basicSystem()
    {

    }

    public static boolean Xturn = true;
    public static int BoardSize = 3;
    public static Cell[][] Board = new Cell[BoardSize][BoardSize];
    public static boolean isX_Win = false;
    public static boolean isO_Win = false;

    //DRAWN
    public static boolean isXO_drawn = false;
    public static boolean isNoMoreSpace = false;
    public static int NoMoreSpaceCounter = 0;

    //to draw the path when X or O wins
    public static int XwinStartIndex = -1;
    public static int XwinEndIndex = -1;
    public static int YwinStartIndex = -1;
    public static int YwinEndIndex = -1;


    private static int MaxToWin = 3;

    public static void check_for_Winning()
    {
        char currentCheckingSymbol;
        if(basicSystem.Xturn)
            currentCheckingSymbol = 'O';  //since it's X turn and we still not tick X yet -->
                                          // we'll have to check O (which ticked previously)
        else
            currentCheckingSymbol = 'X';

        //check if there is any space in the board
        boolean DetectStillEmpty = false;
        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for (int Xindex = 0; Xindex < BoardSize; Xindex++)
            {
                if(Board[Yindex][Xindex].Symbol != ' ')
                    NoMoreSpaceCounter++;
                else //Symbol == ' ' --> empty
                {
                    DetectStillEmpty = true;
                    break;
                }
            }
            if(DetectStillEmpty) {
                NoMoreSpaceCounter = 0;
                break;
            }
        }
        if(NoMoreSpaceCounter == BoardSize * BoardSize)
        {
            NoMoreSpaceCounter = 0;
            isNoMoreSpace = true;
        }


        //check for winning
        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < BoardSize; Xindex++)
            {
                Cell currentCell = Board[Yindex][Xindex];

                if(currentCell.Symbol != currentCheckingSymbol)
                    continue;

                //check horizontal - vertical
                int number_of_currentSymbol = 1;

                if(Xindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i <= (MaxToWin - 1);i++)
                    {
                        if(Board[Yindex][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex + (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex;

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Xindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= (MaxToWin - 1);i++)
                    {
                        if(Board[Yindex][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex - (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex;

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i <= (MaxToWin - 1);i++)
                    {
                        if(Board[Yindex + i][Xindex].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex;
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= (MaxToWin - 1);i++)
                    {
                        if(Board[Yindex - i][Xindex].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex;
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }

                //check cross
                if(Xindex + (MaxToWin - 1) <= BoardSize - 1 && Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= MaxToWin - 1;i++)
                    {
                        if(Board[Yindex - i][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex + (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex + (MaxToWin - 1) <= BoardSize - 1 && Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i <= MaxToWin - 1;i++)
                    {
                        if(Board[Yindex + i][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex + (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (MaxToWin - 1) >= 0 && Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i <= MaxToWin - 1;i++)
                    {
                        if(Board[Yindex + i][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex - (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (MaxToWin - 1) >= 0 && Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= MaxToWin - 1;i++)
                    {
                        if(Board[Yindex - i][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        XwinStartIndex = Xindex;
                        XwinEndIndex = Xindex - (MaxToWin - 1);
                        YwinStartIndex = Yindex;
                        YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
            }
        }
    }

    public static void DrawWinningLine(Graphics m_RealScreenObject)
    {
        if(XwinStartIndex == -1 || YwinStartIndex == -1 || XwinEndIndex == -1 || YwinEndIndex == -1)
            return;

        int Xindex = XwinStartIndex;
        int Yindex = YwinStartIndex;
        int incrementX = 0;
        int incrementY = 0;

        if(XwinStartIndex < XwinEndIndex)
            incrementX = 1;
        else
            incrementX = -1;

        if(YwinStartIndex < YwinEndIndex)
            incrementY = 1;
        else
            incrementY = -1;

        while(true)
        {
            Rectangle box = new Rectangle(Xindex * 64 + 20,Yindex * 64 + 20,64 - 40,64 - 40);
            m_RealScreenObject.setColor(Color.red);
            m_RealScreenObject.fillRect(box.x,box.y,box.width,box.height);

            if(Xindex == XwinEndIndex && Yindex == YwinEndIndex)
            {
                break;
            }

            if(Xindex != XwinEndIndex)
                Xindex += incrementX;
            if(Yindex != YwinEndIndex)
                Yindex += incrementY;
        }
    }

}
