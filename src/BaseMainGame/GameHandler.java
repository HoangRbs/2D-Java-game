package BaseMainGame;

import BaseMainGame.Worlds.World_2D_Tile;
import Entity.Bullet.PlayerBullet;
import Entity.Creature.Player;
import Entity.EntityManager;
import Item.Inventory;
import Item.ItemManager;
import KeyManager.KeyManager;
import MouseManager.MouseManager;

import java.awt.*;
import java.util.ArrayList;


//THIS IS A CLASS THAT CONTAINS ALL THE SHIT YOU CAN POSSIBLY FIND

public class GameHandler {

    public static void Init()       // in Game.init()
    {
        GameHandler.GetInstance();
    }

    private GameHandler(){
        //singleton
    }

    private static GameHandler m_instance = null;

    public static GameHandler GetInstance()
    {
        if(m_instance == null)
        {
            m_instance = new GameHandler();
        }

        return m_instance;
    }

    //world 2d tile object
    private World_2D_Tile World2dTile_object;
    public void set_World2dTile_object(World_2D_Tile _input)    //used after the world is created in GAMESTATE
    {
        this.World2dTile_object = _input;
    }

    public World_2D_Tile get_World2dTile_object()
    {
        return this.World2dTile_object;
    }

    //game object
    private Game m_gameObject;
    public void set_m_gameObject(Game _gameObject)      //used in game_init()
    {
        this.m_gameObject = _gameObject;
    }

    public Game get_m_gameObject()
    {
        return m_gameObject;
    }

    //m_RealScreenObject --> to render stuff -> without using Render(m_RealScreenObject bullshit)
    private Graphics m_RealScreenObject;

    public void set_RealScreenObject(Graphics m_RealScreenObject)
    {
        this.m_RealScreenObject = m_RealScreenObject;
    }

    public Graphics get_RealScreenObject()
    {
        return this.m_RealScreenObject;
    }
    //but since the m_RealScreenObject is keep init every gameloop (Game.Render())
    //the we'll still have to use traditional method
    //if want to change --> the we'll have to keep set_RealScreenObject every gameLoop.

    //FOR COLLISION DETECTION PURPOSE
    private EntityManager m_EntitiesManager;

    public void set_m_EntitiesManager(EntityManager _entitiesManager)
    {
        this.m_EntitiesManager = _entitiesManager;
    }

    public EntityManager get_m_EntitiesManager()
    {
        return this.m_EntitiesManager;
    }

    //MOUSE MANAGER

    public  MouseManager getMouseManager()
    {
        return m_gameObject.GetMouseManager();
    }

    //KEY MANAGER
    public  KeyManager getKeyManager()
    {
        return m_gameObject.GetKeyManager();
    }

    //MOUSE MANAGER
    private MouseManager m_MouseManager =  null;
    public void set_m_MouseManager(MouseManager m_MouseManager)  //use in  Game class
    {
        this.m_MouseManager = m_MouseManager;
    }

    public MouseManager get_m_MouseManager()      //use in MenuClass
    {
        return this.m_MouseManager;
    }

    //ITEM MANAGER
    private ItemManager m_ItemManager;
    public void set_m_ItemManager(ItemManager m_ItemManager)
    {
        this.m_ItemManager = m_ItemManager;
    }

    public ItemManager get_m_ItemManager()
    {
        return this.m_ItemManager;
    }

    //PLAYER
    private Player m_Player;
    public void set_m_Player(Player m_Player)
    {
        this.m_Player = m_Player;
    }

    public Player get_m_Player()
    {
        return this.m_Player;
    }

    private Inventory m_PlayerInventory;  //saving inventory to watch our archivements when player die
    public void save_m_PlayerInventory(Inventory _playerInventory)
    {
        m_PlayerInventory = _playerInventory;
    }
    public Inventory get_m_PlayerInventory()
    {
        return m_PlayerInventory;
    }

    //PLAYER BULLET
    private ArrayList<PlayerBullet> m_PlayerBullets;

    public void set_m_PlayerBullets(ArrayList<PlayerBullet> _PlayerBullets)
    {
        this.m_PlayerBullets = _PlayerBullets;
    }

    public ArrayList<PlayerBullet> get_m_PlayerBullets()
    {
        return this.m_PlayerBullets;
    }
}
