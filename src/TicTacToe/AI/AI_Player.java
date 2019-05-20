package TicTacToe.AI;

import BaseMainGame.GameHandler;
import Path_Fiding.Node;
import TicTacToe.Cell;
import TicTacToe.basicSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Future;

public class AI_Player {


    public static boolean Oturn = true;    //AI Player always start with O turn

    public static void Update()
    {

        //present
        //create present NodeState which has X moved previously
        NodeState presentNodeState = new NodeState(basicSystem.Board);
        MiniMaxTicTacToe(Oturn,presentNodeState);  //start future predict

        //now copy the Board data from the LeafNode which has the bestMove in by its constructor
        for(NodeState currentLeafNode : presentNodeState.LeafNodes_State)
        {
            if(currentLeafNode.bestMove == presentNodeState.bestMove)
            {
                presentNodeState = new NodeState(currentLeafNode.Board);
                break;
            }
        }

        //copy that data into out main Board in basicSystem
        for(int Yindex = 0; Yindex < basicSystem.BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < basicSystem.BoardSize;Xindex++)
            {
                basicSystem.Board[Yindex][Xindex].Symbol = presentNodeState.Board[Yindex][Xindex].Symbol;
                //only need the symbol
                //the rest of the Cell data remains unchanged
            }
        }

        basicSystem.Xturn = true;   //done with O --> X turn
    }


    //future predict
    public static void MiniMaxTicTacToe(boolean nextTurn,NodeState currentNodeState)  //currentNode is already tick O from
                                                                      //creating leaf Node previously
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
                currentNodeState.bestMove = 10;
                return;
            }
        }
        else    //false --> next turn ==  X turn --> this nodeState has new O move --> check for O
        {
            if(currentNodeState.isO_Win)
            {
                currentNodeState.bestMove = -10;
                return;
            }
        }

        if(!currentNodeState.isO_Win && !currentNodeState.isX_Win && currentNodeState.isOutOfSpace)
        {
            currentNodeState.bestMove = 0;
            return;
        }
        //

        //still not outOfSpace
        //assume that...
        //the [currentNode] has new X move + next O turn : bestMove = [all leafNodes] O new moves
        //      |
        //its [leaf node] has O new move + next X turn : bestMove = [all smaller LeafNodes] X new moves
        currentNodeState.createLeafNodes_State();   //create different child nodes with different moves
        for(NodeState LeafNodeState : currentNodeState.LeafNodes_State)
        {
            MiniMaxTicTacToe(!currentNodeState.nextTurn,LeafNodeState);
        }

        if(nextTurn == Oturn)
            currentNodeState.bestMove = MinOf(currentNodeState.LeafNodes_State);
        else
            currentNodeState.bestMove = MaxOf(currentNodeState.LeafNodes_State);
    }

    private static int MinOf(ArrayList<NodeState> LeafNodes_State)
    {
        int MinValue = LeafNodes_State.get(0).bestMove;
        for(NodeState currentLeafNode : LeafNodes_State)
        {
            if(currentLeafNode.bestMove < MinValue)
                MinValue = currentLeafNode.bestMove;
        }

        return MinValue;
    }

    private static  int MaxOf(ArrayList<NodeState> LeafNodes_State)
    {
        int MaxValue = LeafNodes_State.get(0).bestMove;
        for(NodeState currentLeafNode : LeafNodes_State)
        {
            if(currentLeafNode.bestMove > MaxValue)
                MaxValue = currentLeafNode.bestMove;
        }

        return MaxValue;
    }
}
