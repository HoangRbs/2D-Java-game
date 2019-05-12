package States;

import BaseMainGame.GameHandler;
import BaseMainGame.Worlds.World_2D_Tile;
import Camera.Camera_2D;
import Entity.Creature.Player;
import BaseMainGame.Game;
import gfx.Assets;
import Path_Fiding.A_StarAlgorithm;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State{

    private Assets m_GameAssets = Assets.GetInstance();
    //private Player m_Player;
    private World_2D_Tile m_2D_World;
    private Game m_gameObject;
    private boolean isPausing = false;

    public GameState(Game _gameObject)
    {
        this.m_gameObject = _gameObject;

        m_GameAssets.init();

        GameHandler.GetInstance().set_m_gameObject(m_gameObject);

        m_2D_World = new World_2D_Tile("res/Worlds/Worlds.txt",m_gameObject);

        //no need this shit now
        //m_Player = new Player(m_2D_World.InitPlayer_x,m_2D_World.InitPlayer_y);

        GameHandler.GetInstance().set_World2dTile_object(m_2D_World);

        //AI - A STAR ALGORITHM
        A_StarAlgorithm.GenerateNodesTileMap(m_2D_World.getWorldTileWidth(),m_2D_World.getWorldTileHeight());
    }

    @Override
    public void Update()
    {
        if(GameHandler.GetInstance().getKeyManager().isJustPress(KeyEvent.VK_ESCAPE))
        {
            isPausing = !isPausing;
        }

        if(isPausing) {
            //but we can still Update the Inventory
            //for now this is really bad programming practice
            if(GameHandler.GetInstance().get_m_PlayerInventory() != null)
            {
                GameHandler.GetInstance().get_m_PlayerInventory().Update();
            }

            return;
        }

        //since our worlds will use camera() --> camera has to be update first
        Camera_2D.GetInstance().Update();

        m_2D_World.Update();
    }

    public void Render(Graphics m_RealScreenObject)
    {
        m_2D_World.Render(m_RealScreenObject);

        if(isPausing)
        {
            m_RealScreenObject.drawImage(Assets.GetInstance().PauseImage,550,300,200,200,null);
        }
    }

    public void SetGameObject(Game _GameObject)
    {
        m_GameObject = _GameObject;
    }

}
