package TicTacToe.AI;

import BaseMainGame.GameHandler;
import Path_Fiding.Node;
import TicTacToe.Cell;
import TicTacToe.basicSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Future;

public class AI_Player {


    public static boolean Oturn = true;    //AI Player always start with O turn
    public static boolean tickXtwice = false;
    private static int numberOfX = 1;   //when we get here Player has previously tick X

    public static void Update()
    {
        //RANDOM O first
        if(!tickXtwice)
        {
            for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
            {
                for(int Xindex = 0; Xindex < basicSystem.BoardSize;Xindex++)
                {
                    if(basicSystem.Board[Yindex][Xindex].Symbol == 'X')
                    {
                        int randomX = -1000;
                        int randomY = -1000;

                        do
                        {
                            randomX = (int) Math.random() * 2 + (Xindex - 1);
                            randomY = (int) Math.random() * 2 + (Yindex - 1);
                        }
                        while(randomX < 0 || randomX > (basicSystem.BoardSize - 1) ||
                              randomY < 0 || randomY > (basicSystem.BoardSize - 1));

                        basicSystem.Board[randomY][randomX].Symbol = 'O';

                        tickXtwice = true;  //tick X next time
                        basicSystem.Xturn = true;
                        return;
                    }
                }
            }
        }

        //present
        //create present NodeState which has X moved previously
        NodeState presentNodeState = new NodeState();
        MiniMaxTicTacToe(Oturn,presentNodeState,-1000,1000,6,true);  //start future predict


        //now copy the Board data from the LeafNode which has the bestMove in by its constructor

        for(NodeState currentLeafNode : presentNodeState.LeafNodes_State)
        {
            if(currentLeafNode.bestMove == presentNodeState.bestMove)
            {
                //presentNodeState = new NodeState(currentLeafNode.Board);
                //modifying MovedCell also do it with our basicSystem.Board
                currentLeafNode.MovedCell.Symbol = 'O';
                break;
            }
        }

        basicSystem.Xturn = true;   //done with O --> X turn
    }


    //future predict
    public static void MiniMaxTicTacToe(boolean nextTurn,NodeState currentNodeState,int alpha,int beta,int allowDepth,boolean isPresentNodeState)
    {
        currentNodeState.nextTurn = nextTurn;  //X turn,O turn
        currentNodeState.isX_Win = false;
        currentNodeState.isO_Win = false;
        currentNodeState.isOutOfSpace = false;

        currentNodeState.checkWinning();

        //base case:
        if(currentNodeState.nextTurn == Oturn)  //O turn --> this nodeState has new X move --> check for X
        {
            if(currentNodeState.isX_Win)
            {
                currentNodeState.bestMove = 1000;
                if(currentNodeState.bestMove < beta)
                    beta = currentNodeState.bestMove;
                return;
            }
        }
        else    //false --> next turn ==  X turn --> this nodeState has new O move --> check for O
        {
            if(currentNodeState.isO_Win)
            {
                currentNodeState.bestMove = -1000;
                if(currentNodeState.bestMove > alpha)
                    alpha = currentNodeState.bestMove;
                return;
            }
        }

        if(!currentNodeState.isO_Win && !currentNodeState.isX_Win && currentNodeState.isOutOfSpace)
        {
            currentNodeState.bestMove = 0;
            if(currentNodeState.nextTurn == Oturn)
            {
                if(currentNodeState.bestMove < beta)
                    beta = currentNodeState.bestMove;
            }
            else  //X turn
            {
                if(currentNodeState.bestMove > alpha)
                    alpha = currentNodeState.bestMove;
            }
            return;
        }

        if(allowDepth == 0)      //Depth + heuristic
        {
            currentNodeState.bestMove = currentNodeState.calHeuristic();
            return;
        }

        //still not outOfSpace
        currentNodeState.createLeafNodes_State();   //create different child nodes with different moves

        if(nextTurn == Oturn)  //init bestMove for comparing
        {
            currentNodeState.bestMove = 10000;  //as +INFINITY
        }
        else
        {
            currentNodeState.bestMove = -10000;  //as -INFINITY
        }

        Iterator<NodeState> currentLeafNodeState_I = currentNodeState.LeafNodes_State.iterator();
        while(currentLeafNodeState_I.hasNext())
        {
            NodeState currentLeafNodeState = currentLeafNodeState_I.next();
            currentLeafNodeState.Move();   //Move Board before processing it

            MiniMaxTicTacToe(!currentNodeState.nextTurn,currentLeafNodeState,alpha,beta,allowDepth - 1,false);

            if(nextTurn == Oturn)
            {
                currentNodeState.bestMove = Math.min(currentNodeState.bestMove,currentLeafNodeState.bestMove);
                if(currentNodeState.bestMove < beta)
                    beta = currentNodeState.bestMove;
            }
            else  //X turn
            {
                currentNodeState.bestMove = Math.max(currentNodeState.bestMove,currentLeafNodeState.bestMove);
                if(currentNodeState.bestMove > alpha)
                    alpha = currentNodeState.bestMove;
            }

            //UndoMove Board
            currentLeafNodeState.UndoMove();

            //after we get out of the MiniMax above, it means that we get out of the current Leaf Node
            //we no longer need this current Leaf Node --> delete it to avoid out of memory error
            if(!isPresentNodeState)
            {
                currentLeafNodeState_I.remove();
            }

            if(alpha >=  beta)   //alpha, beta pruning
                break;
        }

    }
}
