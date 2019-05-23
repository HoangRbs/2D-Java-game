package States;

import BaseMainGame.Game;
import BaseMainGame.GameHandler;
import MouseManager.MouseManager;
import UserInterface.UI_ImageButton;
import UserInterface.UI_Manager;
import UserInterface.UI_clickFunction;
import gfx.Assets;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State {

    private Assets m_GameAssets = Assets.GetInstance();
    private UI_Manager m_UIManager;

    public MenuState()
    {
        m_GameAssets.init();

        m_UIManager = new UI_Manager();
        //now to connect the working MOUSE EVENT(mouse Manager) to focus on this UIManager
        GameHandler.GetInstance().getMouseManager().set_FocusingOnUIManager(m_UIManager);
        //now the MOUSE has full control on this current UIManager

        //creating a StartButton UI on MenuState
        m_UIManager.AddUIObject(new UI_ImageButton(350,260,300,150,
                Assets.GetInstance().UI_StartButtonImages,
                new UI_clickFunction(){

            @Override
            public void on_ClickToDo() {
                //System.out.println(" Start clicked ");

                StatesManager.StateChange = true;

                StatesManager.set_CurrentStatesID(StatesManager.STATES_ID._GameState_);
                //StatesManager.set_CurrentStatesID(StatesManager.STATES_ID._testA_Star_state_);   //for testing purpose

                //StatesManager.set_CurrentStatesID(StatesManager.STATES_ID._TicTacToeState_);

                //after MenuState is removed --> UI Manager is null --> we don't want our
                //MouseManager to focus on a !@#$ UI --> set to null
                GameHandler.GetInstance().getMouseManager().set_FocusingOnUIManager(null);
            }
        }));
    }

    @Override
    public void Update()
    {
        /*
        if(GameHandler.GetInstance().getKeyManager().isPressEnter)
        {
            StatesManager.StateChange = true;
            StatesManager.set_CurrentStatesID(StatesManager.STATES_ID._GameState_);
        }
        */

        //System.out.println(GameHandler.GetInstance().getMouseManager().getMouse_x() + " " +
        //                    GameHandler.GetInstance().getMouseManager().getMouse_y());

        m_UIManager.Update();
    }

    public void Render(Graphics m_RealScreenObject)
    {
        /*
        m_RealScreenObject.drawImage(m_GameAssets.MenuStateBackground_texture,0,0,
                                     m_GameObject.GetGameWidth(),m_GameObject.GetGameHeight(),
                                    null);
                                    */
        //Dark Background
        m_RealScreenObject.drawImage(Assets.GetInstance().BackgroundImage,0,0,2000,2000,null);

        m_RealScreenObject.setColor(Color.red);
        m_RealScreenObject.fillRect(GameHandler.GetInstance().getMouseManager().getMouse_x(),
                                    GameHandler.GetInstance().getMouseManager().getMouse_y(),
                                    10,10);

        m_UIManager.Render(m_RealScreenObject);
    }

    public void SetGameObject(Game _GameObject)
    {
        this.m_GameObject = _GameObject;
    }
}
