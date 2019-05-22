package TicTacToe.AI;

import TicTacToe.Cell;
import TicTacToe.basicSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeState {
    //each NodeState hold the "future state" of the game in AI Player

    public NodeState()
    {

    }

    public boolean nextTurn = AI_Player.Oturn;  //init
    public boolean isX_Win = false;
    public boolean isO_Win = false;
    public boolean isOutOfSpace = false;
    private int NoMoreSpaceCounter = 0;
    public int bestMove = 0;
    public ArrayList<NodeState> LeafNodes_State;
    //public Cell[][] Board;          //temperary board for future prediction

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
                if(basicSystem.Board[Yindex][Xindex].Symbol != ' ')
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
                Cell currentCell = basicSystem.Board[Yindex][Xindex];

                if(currentCell.Symbol != currentCheckingSymbol)
                    continue;

                //check horizontal - vertical
                int number_of_currentSymbol = 1;

                if(Xindex + (basicSystem.MaxToWin - 1) <= basicSystem.BoardSize - 1)
                {
                    for(int i = 1; i <= (basicSystem.MaxToWin - 1);i++)
                    {
                        if(basicSystem.Board[Yindex][Xindex + i].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex][Xindex - i].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex + i][Xindex].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex - i][Xindex].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex - i][Xindex + i].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex + i][Xindex + i].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex + i][Xindex - i].Symbol != currentCheckingSymbol)
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
                        if(basicSystem.Board[Yindex - i][Xindex - i].Symbol != currentCheckingSymbol)
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

    public Cell MovedCell = null;
    private char SymbolForMovedCell = ' ';

    public void createLeafNodes_State()
    {
        LeafNodes_State = new ArrayList<NodeState>();

        for(int Yindex = 0;Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0;Xindex < basicSystem.BoardSize;Xindex++)
            {
                if(basicSystem.Board[Yindex][Xindex].Symbol == ' ')  //empty
                {
                    NodeState newLeafNode = new NodeState();
                    newLeafNode.MovedCell = basicSystem.Board[Yindex][Xindex];

                    if(nextTurn == AI_Player.Oturn)
                    {
                        //newLeafNode.Board[newLeafNode.temporaryY][newLeafNode.temporaryX].Symbol = 'O';
                        //newLeafNode.Board[Yindex][Xindex].Symbol = 'O';
                        //newLeafNode.MovedCell.Symbol = 'O';
                        newLeafNode.SymbolForMovedCell = 'O';
                    }
                    else  //nextTurn is X turn
                    {
                        //newLeafNode.Board[newLeafNode.temporaryY][newLeafNode.temporaryX].Symbol = 'X';
                        //newLeafNode.Board[Yindex][Xindex].Symbol = 'X';
                        //newLeafNode.MovedCell.Symbol = 'X';
                        newLeafNode.SymbolForMovedCell = 'X';
                    }

                    LeafNodes_State.add(newLeafNode);
                }
            }
        }
    }

    public void removeLeaf(Iterator<NodeState> currentLeafNodeState_I,NodeState currentLeafNodeState)
    {
        //empty the slot
        //currentLeafNodeState.Board[currentLeafNodeState.temporaryY][currentLeafNodeState.temporaryX].Symbol = ' ';

        //remove Leaf of current NodeState
        currentLeafNodeState_I.remove();
    }

    public void UndoMove()
    {
        MovedCell.Symbol = ' ';
    }

    public void Move()
    {
        MovedCell.Symbol = SymbolForMovedCell;
    }
}
