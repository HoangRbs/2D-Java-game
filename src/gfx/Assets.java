package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {          //SINGLETON

    private Assets()
    {
        //not to create a variable outside
    }

    private static Assets m_instance = null;        //SINGLETON

    private SpriteSheet m_sheet;
    private final int CropWidth = 32;
    private final int CropHeight = 32;

    public BufferedImage Player_texture;
    public BufferedImage Grass_texture;
    public BufferedImage Tree_texture;
    public BufferedImage Rock_texture;

    public BufferedImage MenuStateBackground_texture;

    //ITEM
    public BufferedImage WoodItem;
    public BufferedImage RockItem;
    public BufferedImage Enemy1DeadItem;
    public BufferedImage Enemy2DeadItem;
    public BufferedImage Enemy3DeadItem;
    public BufferedImage Enemy4DeadItem;

    //TILES
    private SpriteSheet TerrainSpritesheet;     //SpriteSheet Class has Crop function

    public BufferedImage GrassTile;
    public BufferedImage DirtTile;
    public BufferedImage RockTile;

    //PLAYER ANIMATIONS
    private  SpriteSheet PlayerSheet;

    public BufferedImage[] PlayerDownAnim;
    public BufferedImage[] PlayerUpAnim;
    public BufferedImage[] PlayerStandStillAnim;
    public BufferedImage[] PlayerLeftAnim;
    public BufferedImage[] PlayerRightAnim;

    //PAUSE IMAGE WHEN PLAYER PRESS ESC
    public BufferedImage PauseImage;

    //ENEMY IMAGES
    public BufferedImage Enemy1;
    public BufferedImage Enemy2;
    public BufferedImage Enemy3;
    public BufferedImage Enemy4;

    //DEMON SPAWNER LOCATION IMAGE
    public BufferedImage DemonSpawnerLocation;

    //BULLETS
    public BufferedImage PlayerBullet;

    //USER INTERFACE
    private SpriteSheet UI_StartButton;
    private SpriteSheet UI_TicTacToeCell;

    public BufferedImage[] UI_StartButtonImages;
    public BufferedImage[] UI_TicTacToeCellButtonImages;

    //TicTacToe
    public BufferedImage Xsymbol;
    public BufferedImage Osymbol;

    //DARK BACKGROUND
    public BufferedImage BackgroundImage;

    //AUTHOR
    public BufferedImage AuthorImage;

    //Inventory
    public BufferedImage Inventory;

    public static Assets GetInstance()              //SINGLETON
    {
        if(m_instance == null)
        {
            m_instance = new Assets();
        }

        return m_instance;
    }

    //Font INIT
    public Font m_StandardFont25 = FontLoader.loadFont("res/font/FFFFORWA.TTF",25);
    public Font m_StandardFont18 = FontLoader.loadFont("res/font/FFFFORWA.TTF",18);

    private boolean isAlreadyInit = false;      //cuz this SINGLETON init() is used in many places to ensure that
                                                //everything works correctly
                                                //--> the init() would be called manytimes
                                                //this boolean will take care of that problem

    public void init()      //use in many places to ensure that this is called  --> used in GameInit()
    {
        if(!isAlreadyInit) {
            m_sheet = new SpriteSheet(ImageLoader.LoadBufferedImage("res/testSpriteSheet.png"));

            Player_texture = m_sheet.Crop(0, 0, CropWidth, CropHeight);
            Grass_texture = m_sheet.Crop(0, 32, CropWidth, CropHeight);
            //Tree_texture = m_sheet.Crop(32, 0, CropWidth, CropHeight);  change this a little bit
            Tree_texture = ImageLoader.LoadBufferedImage("res/TreeTexture.png");
            Rock_texture = m_sheet.Crop(32,32,CropWidth,CropHeight);

            MenuStateBackground_texture = ImageLoader.LoadBufferedImage("res/MenuStateBackground.png");

            //TILES
            TerrainSpritesheet = new SpriteSheet(ImageLoader.LoadBufferedImage("res/TerrainSpritesheet.png"));

            GrassTile = TerrainSpritesheet.Crop(0,64,64,64);
            DirtTile =TerrainSpritesheet.Crop(64,0,64,64);
            RockTile = TerrainSpritesheet.Crop(0,0,64,64);

            //PLAYER ANIMATIONS
            PlayerStandStillAnim = new BufferedImage[2];
            PlayerDownAnim = new BufferedImage[2];
            PlayerUpAnim = new BufferedImage[2];
            PlayerLeftAnim = new BufferedImage[2];
            PlayerRightAnim = new BufferedImage[2];

            PlayerSheet = new SpriteSheet(ImageLoader.LoadBufferedImage("res/PlayerSheet.png"));

            PlayerStandStillAnim[0] = PlayerSheet.Crop(0,0,60,60);
            PlayerStandStillAnim[1] = PlayerSheet.Crop(60,0,60,60);
            PlayerDownAnim[0] = PlayerSheet.Crop(0,60,60,60);
            PlayerDownAnim[1] = PlayerSheet.Crop(60,60,60,60);
            PlayerUpAnim[0] = PlayerSheet.Crop(0,120,60,60);
            PlayerUpAnim[1] = PlayerSheet.Crop(60,120,60,60);
            PlayerLeftAnim[0] = PlayerSheet.Crop(0,240,60,60);
            PlayerLeftAnim[1] = PlayerSheet.Crop(60,240,60,60);
            PlayerRightAnim[0] = PlayerSheet.Crop(0,180,60,60);
            PlayerRightAnim[1] =PlayerSheet.Crop(60,180,60,60);

            //PAUSE IMAGE
            PauseImage = ImageLoader.LoadBufferedImage("res/PauseButton.png");

            //ENEMY IMAGES
            Enemy1 = ImageLoader.LoadBufferedImage("res/Enemy1.png");
            Enemy2 = ImageLoader.LoadBufferedImage("res/Enemy2.png");
            Enemy3 = ImageLoader.LoadBufferedImage("res/Enemy3.png");
            Enemy4 = ImageLoader.LoadBufferedImage("res/Enemy4.png");

            //DEMON SPAWNER LOCATION
            DemonSpawnerLocation = ImageLoader.LoadBufferedImage("res/DemonSpawnerLocation.png");

            //PLAYER BULLETS
            PlayerBullet = ImageLoader.LoadBufferedImage("res/PlayerBullet.png");

            //USER INTERFACE
            UI_StartButton = new SpriteSheet(ImageLoader.LoadBufferedImage("res/StartButton.png"));
            UI_StartButtonImages = new BufferedImage[2];
            UI_StartButtonImages[0] = UI_StartButton.Crop(0,0,124,62);
            UI_StartButtonImages[1] = UI_StartButton.Crop(0,62,124,62);

            UI_TicTacToeCell = new SpriteSheet(ImageLoader.LoadBufferedImage("res/TicTacToeCell.png"));
            UI_TicTacToeCellButtonImages = new BufferedImage[2];
            UI_TicTacToeCellButtonImages[0] = UI_TicTacToeCell.Crop(0,0,60,60);
            UI_TicTacToeCellButtonImages[1] = UI_TicTacToeCell.Crop(60,0,60,60);

            //DARK BACKGROUND
            BackgroundImage = ImageLoader.LoadBufferedImage("res/background.png");

            //ITEM
            WoodItem = ImageLoader.LoadBufferedImage("res/WoodItem.png");
            RockItem = ImageLoader.LoadBufferedImage("res/RockItem.png");
            Enemy1DeadItem = ImageLoader.LoadBufferedImage("res/Enemy1DeadItem.png");
            Enemy2DeadItem = ImageLoader.LoadBufferedImage("res/Enemy2DeadItem.png");
            Enemy3DeadItem = ImageLoader.LoadBufferedImage("res/Enemy3DeadItem.png");
            Enemy4DeadItem = ImageLoader.LoadBufferedImage("res/Enemy4DeadItem.png");

            //TicTacToe
            Xsymbol = ImageLoader.LoadBufferedImage("res/Xsymbol.png");
            Osymbol = ImageLoader.LoadBufferedImage("res/Osymbol.png");

            //AUTOR image
            AuthorImage = ImageLoader.LoadBufferedImage("res/HandsomeAuthor.png");

            //INVENTORY
            Inventory = ImageLoader.LoadBufferedImage("res/Inventory.png");

            isAlreadyInit = true;
        }
        else
        {
            System.out.println("Assets are already init");
        }
    }
}
