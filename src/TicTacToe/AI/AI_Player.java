package TicTacToe.AI;

import BaseMainGame.GameHandler;
import TicTacToe.basicSystem;

import java.util.concurrent.Future;

public class AI_Player {


    //private static Oturn = true;

    public static void Update()
    {
        /*
        //present
        Oturn  = true;
        //create present NodeState which has X move previously
        presentNodeState;
        MiniMaxTicTacToe(Oturn,presentNodeState);  //start future predict
        */

        System.out.println(" done tick O ");
        basicSystem.Xturn = true;   //done with O --> X turn
    }

    /*
    //future predict
    public static void MiniMaxTicTacToe(next Turn, currentNodeState)  //currentNode is already tick O from
                                                            //creating leaf Node previously
    {
        currentNodeState.nextTurn = nextTurn;
        currentNodeState.isX_Win = false;
        currentNodeState.isO_Win = false;
        currentNodeState.isOutOfSpace = false;

        checkWinning(currentNodeState); //NodeState mostly holds everything

        //base case:
        if(currentNodeState.nextTurn == O turn)  //O turn --> this nodeState has new X move --> check for X
        {
            if(currentNodeState.isX_Win)
            {
                currentNodeState.bestMove = 10;
                backtrack;
            }
        }
        else    //false --> next turn ==  X turn --> this nodeState has new O move --> check for O
        {
            if(currentNodeState.isO_Win)
            {
                currentNodeState.bestMove = -10;
                backtrack;
            }
        }

        if(!currentNodeState.isO_Win && !currentNodeState.isX_Win && currentNodeState.isOutOfSpace)
        {
            currentNodeState.bestMove = 0;
            backtrack;
        }
        //

        //still not outOfSpace
        //assume that...
        //the [currentNode] has new X move + next O turn : bestMove = [all leafNodes] O new moves
        //      |
        //its [leaf node] has O new move + next X turn : bestMove = [all smaller LeafNodes] X new moves
        currentNodeState --> create several LeafNodeStates
        for(all LeafnodeState in NodeStates list)
        {
            MiniMaxTicTacToe(!current Future Turn,LeafNodeState);
        }

        if(current  Turn == future O turn)
            bestMove = Math.Min(all leaf nodes.bestMove);
        else
            bestMove = Math.Max(all leaf nodes.bestMove);
    }

    */
}
