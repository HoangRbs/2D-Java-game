package BaseMainGame.Worlds;

import BaseMainGame.Game;
import BaseMainGame.GameHandler;
import BaseMainGame.Ultilities;
import Camera.Camera_2D;
import Entity.Creature.Enemy;
import Entity.Creature.PathFollowEnemy;
import Entity.Creature.Player;
import Entity.EntityManager;
import Entity.StaticEntity.Rock;
import Entity.StaticEntity.Tree;
import Item.ItemManager;
import Item.Kinds_Of_Item;
import Path_Fiding.A_StarAlgorithm;
import Tile.Tile;
import Tile.*;
import gfx.Assets;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class World_2D_Tile {
    int WorldTileHeight;
    int WorldTileWidth;

    int[][] World_IDs;      // OUR MAP

    private Game m_gameObject;

    public int InitPlayer_x;
    public int InitPlayer_y;

    private EntityManager m_EntitiesManager;
    private ItemManager m_ItemManager;
    public static ArrayList<PathFollowEnemy> RandomGenerateEnemy;   //for random generation when 1 PathFollowEnemy.DIE()

    public World_2D_Tile(String path,Game _gameObject)    //OUR WORLD WILL BE CREATED IN GAMESTATE
    {
        LoadWorld(path);

        m_EntitiesManager = new EntityManager(new Player(InitPlayer_x, InitPlayer_y));

        //static entities
        m_EntitiesManager.addEntity(new Tree(160,1380));
        m_EntitiesManager.addEntity(new Tree(870,650));
        //m_EntitiesManager.addEntity(new Rock(500,400));
        //m_EntitiesManager.addEntity(new Rock(400,700));

        //Enemy entities
        PathFollowEnemy PathFollowEnemy1 = new PathFollowEnemy(192,192,3,3,
                                                    Assets.GetInstance().Enemy1, Kinds_Of_Item.Enemy1DeadItem);

        PathFollowEnemy PathFollowEnemy2 = new PathFollowEnemy(1408,1088,3,4,
                                                     Assets.GetInstance().Enemy2, Kinds_Of_Item.Enemy2DeadItem);

        PathFollowEnemy PathFollowEnemy3 = new PathFollowEnemy(192,1664,4,3,
                                                     Assets.GetInstance().Enemy3, Kinds_Of_Item.Enemy3DeadItem);

        PathFollowEnemy PathFollowEnemy4 = new PathFollowEnemy(192,192,4,4,
                                                     Assets.GetInstance().Enemy4, Kinds_Of_Item.Enemy4DeadItem);

        m_EntitiesManager.addEntity(PathFollowEnemy1.CreateNewCopy());
        m_EntitiesManager.addEntity(PathFollowEnemy2.CreateNewCopy());
        m_EntitiesManager.addEntity(PathFollowEnemy3.CreateNewCopy());
        m_EntitiesManager.addEntity(PathFollowEnemy4.CreateNewCopy());

        RandomGenerateEnemy = new ArrayList<PathFollowEnemy>();
        RandomGenerateEnemy.add(PathFollowEnemy1);
        RandomGenerateEnemy.add(PathFollowEnemy2);
        RandomGenerateEnemy.add(PathFollowEnemy3);
        RandomGenerateEnemy.add(PathFollowEnemy4);

        m_ItemManager = new ItemManager();

        GameHandler.GetInstance().set_m_EntitiesManager(m_EntitiesManager);
        GameHandler.GetInstance().set_m_ItemManager(m_ItemManager);

        this.m_gameObject = _gameObject;
    }

    public void Update()
    {
        /*  this is really not that necessary for Tiles to Update
        for(int y=0; y < WorldHeight;y++)
        {
            for(int x = 0; x < WorldWidth; x++)
            {
                getTileAt(y,x).Update();
            }
        }*/

        m_ItemManager.Update();
        m_EntitiesManager.Update();
    }

    public void Render(Graphics m_RealScreenObject)
    {
        //Dark Background
        m_RealScreenObject.drawImage(Assets.GetInstance().BackgroundImage,0,0,2000,2000,null);

        //World render optimization
        int Xstart = Math.max(0,(int)(Camera_2D.GetInstance().getxOffset()/TileInitStuff.TILE_WIDTH)); //as Xstart index in our Map whose lowest value = 0

        int Xend = Math.min(WorldTileWidth,(int)((Camera_2D.GetInstance().getxOffset() + m_gameObject.GetGameWidth())
                                                 /TileInitStuff.TILE_WIDTH + 1));                    //WorldWidth is the maximum index --> upper that --> array out of bound

        int Ystart = Math.max(0,(int)(Camera_2D.GetInstance().getyOffset()/TileInitStuff.TILE_HEIGHT));

        int Yend = Math.min(WorldTileHeight,(int)((Camera_2D.GetInstance().getyOffset() + m_gameObject.GetGameHeight())
                                                 /TileInitStuff.TILE_HEIGHT + 1));

        for(int y = Ystart; y < Yend;y++)
        {
            for(int x = Xstart; x < Xend; x++)
            {
                getTileAt(y,x).Render(m_RealScreenObject,
                        (int)(x * TileInitStuff.TILE_WIDTH - Camera_2D.GetInstance().getxOffset()),
                        (int)(y * TileInitStuff.TILE_HEIGHT - Camera_2D.GetInstance().getyOffset()));
            }
        }
        m_ItemManager.Render(m_RealScreenObject);
        m_EntitiesManager.Render(m_RealScreenObject);

        //DEMON SPAWNER LOCATION
        m_RealScreenObject.drawImage(Assets.GetInstance().DemonSpawnerLocation,192 - (int)Camera_2D.GetInstance().getxOffset(),192 - (int)Camera_2D.GetInstance().getyOffset(),64,64,null);
        m_RealScreenObject.drawImage(Assets.GetInstance().DemonSpawnerLocation,1408 - (int)Camera_2D.GetInstance().getxOffset(),1088 - (int)Camera_2D.GetInstance().getyOffset(),64,64,null);
        m_RealScreenObject.drawImage(Assets.GetInstance().DemonSpawnerLocation,192 - (int)Camera_2D.GetInstance().getxOffset(),1664 - (int)Camera_2D.GetInstance().getyOffset(),64,64,null);
    }

    private void LoadWorld(String path)
    {
        String Data = Ultilities.LoadDatas_AsString(path);
        String[] Splitted_Datas = Data.split("\\s+");  //Split without SPACE

        this.WorldTileHeight = Ultilities.StringToInt(Splitted_Datas[0]);
        this.WorldTileWidth = Ultilities.StringToInt(Splitted_Datas[1]);

        this.InitPlayer_x = Ultilities.StringToInt(Splitted_Datas[2]);
        this.InitPlayer_y = Ultilities.StringToInt(Splitted_Datas[3]);

        World_IDs = new int[WorldTileHeight][WorldTileWidth];


        for(int y = 0; y < WorldTileHeight; y++)
        {
            for(int x = 0; x < WorldTileWidth; x++)
            {
                World_IDs[y][x] = Ultilities.StringToInt(Splitted_Datas[(WorldTileWidth * y + x) + 4]);
            }
        }

    }

    public Tile getTileAt(int y,int x)
    {
        if(x >= WorldTileWidth || y >= WorldTileHeight || x < 0 || y < 0)
        {
            //WHEN THE PLAYER GO OUTSIDE OF THE MAP
            return TileInitStuff.m_Tiles[0];
        }

        int _ID = World_IDs[y][x];

        Tile tempTile = TileInitStuff.m_Tiles[_ID];
        if(tempTile == null)
        {
            return TileInitStuff.m_Tiles[0];   //which is grassTile --> just assume that out side of the Map is all GRASS
                                               //--> this feature is gonna be used for checkCollision WITH TILE
                                               //in the Entity->Player class
        }

        return tempTile;
    }

    public int getWorldTileWidth()
    {
        return this.WorldTileWidth;
    }

    public int getWorldTileHeight()
    {
        return this.WorldTileHeight;
    }
}
