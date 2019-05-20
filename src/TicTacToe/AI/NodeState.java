package TicTacToe.AI;

import TicTacToe.Cell;
import TicTacToe.basicSystem;

import java.util.ArrayList;

public class NodeState {
    //each NodeState hold the "future state" of the game in AI Player

    public NodeState(Cell[][] passedBoard)
    {
        LeafNodes_State = new ArrayList<NodeState>();
        Board = new Cell[basicSystem.BoardSize][basicSystem.BoardSize];

        //deep copy
        for(int Yindex = 0;Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0;Xindex < basicSystem.BoardSize;Xindex++)
            {
                Board[Yindex][Xindex] = new Cell();
                Board[Yindex][Xindex].Symbol = passedBoard[Yindex][Xindex].Symbol;
                Board[Yindex][Xindex].posX = passedBoard[Yindex][Xindex].posX;
                Board[Yindex][Xindex].posY = passedBoard[Yindex][Xindex].posY;
            }
        }
    }

    public boolean nextTurn = AI_Player.Oturn;  //init
    public boolean isX_Win = false;
    public boolean isO_Win = false;
    public boolean isOutOfSpace = false;
    private int NoMoreSpaceCounter = 0;
    public int bestMove = 0;
    public ArrayList<NodeState> LeafNodes_State;
    public Cell[][] Board;          //temperary board for future prediction

    public void checkWinning()
    {
        char currentCheckingSymbol;
        if(nextTurn != AI_Player.Oturn)
            currentCheckingSymbol = 'O';
        else
            currentCheckingSymbol = 'X';

        //check if there is any space in the board
        boolean DetectStillEmpty = false;
        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            for (int Xindex = 0; Xindex < basicSystem.BoardSize; Xindex++)
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
        if(NoMoreSpaceCounter == basicSystem.BoardSize * basicSystem.BoardSize)
        {
            NoMoreSpaceCounter = 0;
            isOutOfSpace = true;
        }


        //check for winning
        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < basicSystem.BoardSize; Xindex++)
            {
                Cell currentCell = Board[Yindex][Xindex];

                if(currentCell.Symbol != currentCheckingSymbol)
                    continue;

                //check horizontal - vertical
                int number_of_currentSymbol = 1;

                if(Xindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1)
                {
                    for(int i = 1; i <= (basicSystem.MaxToWin - 1);i++)
                    {
                        if(Board[Yindex][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex + (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex;

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Xindex - (basicSystem.MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= (basicSystem.MaxToWin - 1);i++)
                    {
                        if(Board[Yindex][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex - (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex;

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1)
                {
                    for(int i = 1; i <= (basicSystem.MaxToWin - 1);i++)
                    {
                        if(Board[Yindex + i][Xindex].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex;
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }
                if(Yindex - (basicSystem.MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= (basicSystem.MaxToWin - 1);i++)
                    {
                        if(Board[Yindex - i][Xindex].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex;
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;   //reinit for below usage
                }

                //check cross
                if(Xindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1 && Yindex - (basicSystem.MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= basicSystem.MaxToWin - 1;i++)
                    {
                        if(Board[Yindex - i][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex + (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1 && Yindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1)
                {
                    for(int i = 1; i <= basicSystem.MaxToWin - 1;i++)
                    {
                        if(Board[Yindex + i][Xindex + i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex + (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (basicSystem.MaxToWin - 1) >= 0 && Yindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1)
                {
                    for(int i = 1; i <= basicSystem.MaxToWin - 1;i++)
                    {
                        if(Board[Yindex + i][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex - (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex + (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
                if(Xindex - (basicSystem.MaxToWin - 1) >= 0 && Yindex - (basicSystem.MaxToWin - 1) >= 0)
                {
                    for(int i = 1; i <= basicSystem.MaxToWin - 1;i++)
                    {
                        if(Board[Yindex - i][Xindex - i].Symbol != currentCheckingSymbol)
                            break;
                        number_of_currentSymbol++;
                    }
                    if(number_of_currentSymbol == basicSystem.MaxToWin)
                    {
                        if(currentCheckingSymbol == 'X')
                            isX_Win = true;
                        else
                            isO_Win = true;

                        //XwinStartIndex = Xindex;
                        //XwinEndIndex = Xindex - (MaxToWin - 1);
                        //YwinStartIndex = Yindex;
                        //YwinEndIndex = Yindex - (MaxToWin - 1);

                        return;
                    }
                    number_of_currentSymbol = 1;
                }
            }
        }
    }

    public void createLeafNodes_State()
    {
        for(int Yindex = 0;Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0;Xindex < basicSystem.BoardSize;Xindex++)
            {
                if(Board[Yindex][Xindex].Symbol == ' ')  //empty
                {
                    NodeState newLeafNode = new NodeState(Board);
                    if(nextTurn == AI_Player.Oturn)
                    {
                        newLeafNode.Board[Yindex][Xindex].Symbol = 'O';
                    }
                    else  //nextTurn is X turn
                    {
                        newLeafNode.Board[Yindex][Xindex].Symbol = 'X';
                    }

                    LeafNodes_State.add(newLeafNode);
                }
            }
        }
    }
}
