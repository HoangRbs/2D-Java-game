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
                        newLeafNode.SymbolForMovedCell = 'O';
                    }
                    else  //nextTurn is X turn
                    {
                        newLeafNode.SymbolForMovedCell = 'X';
                    }

                    LeafNodes_State.add(newLeafNode);
                }
            }
        }
    }

    public void UndoMove()
    {
        MovedCell.Symbol = ' ';
    }

    public void Move()
    {
        MovedCell.Symbol = SymbolForMovedCell;
    }

    public int calHeuristic()
    {
        int O_canWinLines = 0;
        int X_canWinLines = 0;

        //horizontal check
        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++) {
            //check Line completion
            if(isHorizontalLineCompletionFor('X',Yindex))
            {
                X_canWinLines++;
            }

            if(isHorizontalLineCompletionFor('O',Yindex))
            {
                O_canWinLines++;
            }
        }

        //vertical check
        for(int Xindex = 0;Xindex < basicSystem.BoardSize;Xindex++)
        {
            if(isVerticalLineCompletionFor('X',Xindex))
            {
                X_canWinLines++;
            }

            if(isVerticalLineCompletionFor('O',Xindex))
            {
                O_canWinLines++;
            }
        }

        //cross LINES check
        int limitIndex = basicSystem.BoardSize - basicSystem.MaxToWin;

        //check right cross Lines
        for(int Yindex = 0; Yindex <= limitIndex;Yindex++)
        {
            if(Yindex == 0)
            {
                for (int Xindex = limitIndex; Xindex >= 0; Xindex--)
                {
                    if(isRightCrossLineCompletionFor('X',Yindex,Xindex))
                    {
                        X_canWinLines++;
                    }
                    if(isRightCrossLineCompletionFor('O',Yindex,Xindex))
                    {
                        O_canWinLines++;
                    }
                }
            }
            else
            {
                //only Xindex == 0 here
                if(isRightCrossLineCompletionFor('X',Yindex,0))
                {
                    X_canWinLines++;
                }
                if(isRightCrossLineCompletionFor('O',Yindex,0))
                {
                    O_canWinLines++;
                }
            }
        }

        //check left cross Lines
        for(int Yindex = 0;Yindex <= limitIndex;Yindex++)
        {
            if(Yindex == 0)
            {
                for(int Xindex = (basicSystem.BoardSize - 1) - limitIndex; Xindex <= basicSystem.BoardSize -1;Xindex++)
                {
                    if(isLeftCrossLineCompletionFor('X',Yindex,Xindex))
                    {
                        X_canWinLines++;
                    }
                    if(isLeftCrossLineCompletionFor('O',Yindex,Xindex))
                    {
                        O_canWinLines++;
                    }
                }
            }
            else
            {
                //only Xindex == boardSize - 1 here
                if(isLeftCrossLineCompletionFor('X',Yindex,basicSystem.BoardSize - 1))
                {
                    X_canWinLines++;
                }
                if(isLeftCrossLineCompletionFor('O',Yindex,basicSystem.BoardSize - 1))
                {
                    O_canWinLines++;
                }
            }
        }

        if(nextTurn == AI_Player.Oturn)
        {
            return O_canWinLines - X_canWinLines;
        }
        else
        {
            return X_canWinLines - O_canWinLines;
        }
    }

    private boolean isHorizontalLineCompletionFor(char Symbol,int Yindex)
    {
        char AvoidSymbol = ' ';
        if(Symbol == 'X')
            AvoidSymbol = 'O';
        else
            AvoidSymbol = 'X';

        for(int Xindex = 0; Xindex < basicSystem.BoardSize;Xindex++)
        {
            if(basicSystem.Board[Yindex][Xindex].Symbol != AvoidSymbol)
            {
                int numberOfAvoidSymbol = 0;

                for(int currentXindex = Xindex; currentXindex <= Xindex + (basicSystem.MaxToWin - 1); currentXindex++)
                {
                    if(currentXindex > basicSystem.BoardSize - 1)        //out of bound
                    {
                        return false;
                    }

                    if(basicSystem.Board[Yindex][currentXindex].Symbol == AvoidSymbol)
                    {
                        numberOfAvoidSymbol++;
                        break;
                    }
                }

                if(numberOfAvoidSymbol == 0)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isVerticalLineCompletionFor(char Symbol,int Xindex)
    {
        char AvoidSymbol = ' ';
        if(Symbol == 'X')
            AvoidSymbol = 'O';
        else
            AvoidSymbol = 'X';

        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            if(basicSystem.Board[Yindex][Xindex].Symbol != AvoidSymbol)
            {
                int numberOfAvoidSymbol = 0;

                for(int currentYindex = Yindex; currentYindex <= Yindex + (basicSystem.MaxToWin - 1); currentYindex++)
                {
                    if(currentYindex > basicSystem.BoardSize - 1)        //out of bound
                    {
                        return false;
                    }

                    if(basicSystem.Board[currentYindex][Xindex].Symbol == AvoidSymbol)
                    {
                        numberOfAvoidSymbol++;
                        break;
                    }
                }

                if(numberOfAvoidSymbol == 0)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isRightCrossLineCompletionFor(char Symbol,int Yindex,int Xindex)
    {
        char AvoidSymbol = ' ';
        if(Symbol == 'X')
            AvoidSymbol = 'O';
        else
            AvoidSymbol = 'X';

        while(Yindex <= basicSystem.BoardSize - 1 && Xindex <= basicSystem.BoardSize -1)
        {
            if(basicSystem.Board[Yindex][Xindex].Symbol != AvoidSymbol)
            {
                int currentXindex = Xindex;
                int currentYindex = Yindex;
                int numberOfAvoidSymbol = 0;

                while(currentXindex <= Xindex + (basicSystem.MaxToWin - 1) &&
                        currentYindex <= Yindex + (basicSystem.MaxToWin - 1))
                {
                    if(currentXindex > basicSystem.BoardSize - 1 || currentYindex > basicSystem.BoardSize - 1)  //out of bound
                    {
                        return false;
                    }

                    if(basicSystem.Board[currentYindex][currentXindex].Symbol == AvoidSymbol)
                    {
                        numberOfAvoidSymbol++;
                        break;
                    }

                    currentXindex++;
                    currentYindex++;
                }

                if(numberOfAvoidSymbol == 0)
                    return true;
            }
            Yindex++;
            Xindex++;
        }

        return false;
    }

    private boolean isLeftCrossLineCompletionFor(char Symbol,int Yindex,int Xindex)
    {
        char AvoidSymbol = ' ';
        if(Symbol == 'X')
            AvoidSymbol = 'O';
        else
            AvoidSymbol = 'X';

        while(Yindex <= basicSystem.BoardSize - 1 && Xindex >= 0)
        {
            if(basicSystem.Board[Yindex][Xindex].Symbol != AvoidSymbol)
            {
                int currentXindex = Xindex;
                int currentYindex = Yindex;
                int numberOfAvoidSymbol = 0;

                while(currentXindex >= Xindex - (basicSystem.MaxToWin - 1) &&
                        currentYindex <= Yindex + (basicSystem.MaxToWin - 1))
                {
                    if(currentXindex < 0 || currentYindex > basicSystem.BoardSize - 1)  //out of bound
                    {
                        return false;
                    }

                    if(basicSystem.Board[currentYindex][currentXindex].Symbol == AvoidSymbol)
                    {
                        numberOfAvoidSymbol++;
                        break;
                    }

                    currentXindex--;
                    currentYindex++;
                }

                if(numberOfAvoidSymbol == 0)
                    return true;
            }
            Yindex++;
            Xindex--;
        }

        return false;
    }
}
