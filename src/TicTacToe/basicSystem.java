package TicTacToe;

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

    private static int MaxToWin = 3;

    public static void check_for_Winning()
    {
        char currentCheckingSymbol = ' ';
        if(basicSystem.Xturn)
            currentCheckingSymbol = 'X';
        else
            currentCheckingSymbol = 'O';

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
                    for(int i = 1; i < (MaxToWin - 1);i++)
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
                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Xindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i < (MaxToWin - 1);i++)
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
                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i < (MaxToWin - 1);i++)
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
                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i < (MaxToWin - 1);i++)
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
                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }

                //check cross
                if(Xindex + (MaxToWin - 1) <= BoardSize - 1 && Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i < MaxToWin - 1;i++)
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
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex + (MaxToWin - 1) <= BoardSize - 1 && Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i < MaxToWin - 1;i++)
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
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (MaxToWin - 1) >= 0 && Yindex + (MaxToWin - 1) <= BoardSize - 1)
                {
                    for(int i = 1; i < MaxToWin - 1;i++)
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
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (MaxToWin - 1) >= 0 && Yindex - (MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i < MaxToWin - 1;i++)
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
                    }
                    number_of_currentSymbol = 1;
                }
            }
        }
    }

}
