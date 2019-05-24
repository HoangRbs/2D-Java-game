package States;

import BaseMainGame.Game;
import TicTacToe.TicTacToeGameState;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class StatesManager {
    //a list queue here to change State (later)
    //but now for the sake of simplicity --> just a Queue storing a GameState --> play immediately

    private Queue<State> States;
    private State currentState = null;
    private Game m_GameObject = null;

    public StatesManager(Game m_GameObject)       //this always be executed 1st
    {
        this.m_GameObject = m_GameObject;
    }

    public void init()        //used in game.Init()
    {
        States = new LinkedList<State>();

        //States.add(new GameState(m_GameObject));
        //CurrentStateID = STATES_ID._GameState_;

        States.add(new MenuState());
        CurrentStateID = STATES_ID._MenuState_;

        currentState = States.peek();
        currentState.SetGameObject(m_GameObject);    //right now the GameState stores GameObject (not null)
    }

    public void Update()                //used in Game.Update()
    {

        if(StateChange)
        {
            States.remove(currentState);

            if(CurrentStateID == STATES_ID._GameState_)
            {
                States.add(new GameState(m_GameObject));
                currentState = States.peek();
                currentState.SetGameObject(m_GameObject);
            }
            else if(CurrentStateID == STATES_ID._MenuState_)
            {
                States.add(new MenuState());
                currentState = States.peek();
                currentState.SetGameObject(m_GameObject);
            }
            else if(CurrentStateID == STATES_ID._testA_Star_state_)       //FOR TESTING PURPOSE
            {
                States.add(new testA_Star_state());
                currentState = States.peek();
                currentState.SetGameObject(m_GameObject);
            }
            else if(CurrentStateID == STATES_ID._TicTacToeState_)
            {
                States.add(new TicTacToeGameState());
                currentState = States.peek();
                currentState.SetGameObject(m_GameObject);
            }

            StateChange  = false;
        }

        if(currentState != null)
        {
            currentState.Update();
        }
    }

    public void Render(Graphics m_RealScreenObject)           //used in Game.Render()
    {
        if(currentState != null)
        {
            currentState.Render(m_RealScreenObject);
        }
    }

    //to change STATE from outside (GameState/MenuState)
    public enum STATES_ID
    {
        _GameState_,_MenuState_,_testA_Star_state_,_TicTacToeState_
    }

    private static  STATES_ID CurrentStateID;
    public static void set_CurrentStatesID(STATES_ID id) //change current StateID
    {
        CurrentStateID = id;
    }

    public static boolean StateChange = false;
}
