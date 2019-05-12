package BaseMainGame;

import Camera.Camera_2D;
import Display.Display;
import KeyManager.KeyManager;
import MouseManager.MouseManager;
import States.StatesManager;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display m_Display;
    private int GameWidth;
    private int GameHeight;
    private String GameTitle;
    private boolean GameIsRunning = false;
    private BufferStrategy m_BufferStrategyObject = null;
    private Graphics m_RealScreenObject = null;
    private StatesManager m_statesManager = new StatesManager(this);
    private KeyManager m_KeyManager = new KeyManager();
    private MouseManager m_MouseManager = new MouseManager();

    private Thread GameThread;     //need to run the game in its own thread instead of Main()
                                   // The main will handle other things
    private boolean GameInit()
    {
        m_Display = new Display(GameTitle,GameWidth,GameHeight);   //this have to be init first

        Camera_2D.Camera_2D_Init(this);                 //Init our camera

        GameHandler.Init();
        GameHandler.GetInstance().set_m_gameObject(this);
        GameHandler.GetInstance().set_m_MouseManager(m_MouseManager);

        Assets.GetInstance().init();

        m_statesManager.init();

        m_Display.GetWindow().addKeyListener(m_KeyManager);   //the KeyManager(KeyListener type)
                                                              //cannot "listen" the inputKey on its own
                                                              // we need an immediate JFRAME object and
                                                              //pass the KeyListener into it
                                                              //that's just how this shitty framework works
        m_Display.GetWindow().addMouseListener(m_MouseManager);
        m_Display.GetWindow().addMouseMotionListener(m_MouseManager);
        m_Display.GetRenderer().addMouseListener(m_MouseManager);
        m_Display.GetRenderer().addMouseMotionListener(m_MouseManager);

        return true;
    }


    private void GameUpdate()
    {
        m_KeyManager.Update();    //to see if the Up/Down/Left/Right is pressed --> change Player behavior
        m_statesManager.Update();
    }

    /*    FIX THIS SHIT LATER
    private void PrepareForRender()     //have to use below
    {
        m_BufferStrategyObject = m_Display.GetRenderer().getBufferStrategy();
        if(m_BufferStrategyObject == null) //buffer strategy is not created
        {
            m_Display.GetRenderer().createBufferStrategy(3);  //create a BufferStrategy for get...() later
            return;
        }
        else {
            m_RealScreenObject = m_BufferStrategyObject.getDrawGraphics();
        }

        m_statesManager.SetRealScreenObject(m_RealScreenObject);   //set the RealScreen for states to draw

    }

    //IMPORTANT NOTE : Why the PrepareForRender() not work

        //because of our Game.Update() can update() multiple times in 1 frame cuz of
        //my shitty FPS algorithm --> at some point it will Update 3 times just to match the FPS
        //algorithm --> then it gets to Render()
        //---> Fix this!!!!
    */

    //private boolean isGameStateSetRealScreen = false;      //use below
    private void GameRender()
    {
        //PrepareForRender();

        //this code has be executed multiple times
        m_BufferStrategyObject = m_Display.GetRenderer().getBufferStrategy();
        if (m_BufferStrategyObject == null) //buffer strategy is not created
        {
            m_Display.GetRenderer().createBufferStrategy(3);  //create a BufferStrategy for get...() later
            return;
        } else {
            m_RealScreenObject = m_BufferStrategyObject.getDrawGraphics();
        }

        //Clear the previous screen for the next below Frame to Draw
        m_RealScreenObject.clearRect(0, 0, GameWidth, GameHeight);



        //Start Draw here
                            //m_RealScreenObject.setColor(Color.red);
                            //m_RealScreenObject.fillRect(100,100,500,500);
                            //m_RealScreenObject.setColor(Color.blue);
                            // m_RealScreenObject.fillRect(800,300,80,80);
                            // m_RealScreenObject.drawImage(testImage,10,10,400,400,null);

                            //m_RealScreenObject.drawImage(m_GameAssets.Player_texture,x,0,100,100,null);
                            //m_RealScreenObject.drawImage(m_GameAssets.Grass_texture,200,50,70,70,null);
                            //m_RealScreenObject.drawImage(m_GameAssets.Tree_texture,90,90,40,40,null);

        m_statesManager.Render(m_RealScreenObject);                 //this has to be set multiple times
                                                                    //since this framework is shit
                                                                    //how this shitty framework works :
                                                                    //the BUFFERSTRATEGY keep changing
                                                                    //---> then its REALSCREEN keep changing
                                                                    //--> we have to set(REALSCREEN) many times

        //End Drawing  --> show() ---> Dispose() everything
        m_BufferStrategyObject.show();
        m_RealScreenObject.dispose();


    }

    private long LastTime;
    private long CurrentTime;
    private long DeltaTime = 0;

    private int FPS = 70;
    private double TimePerFrame = 1000000000.0/(double)FPS ;

    private long TimeElapsed = 0;
    private int FramesCounter = 0;

    private void GameRun()
    {
        LastTime = System.nanoTime();

        while(GameIsRunning)
        {
            CurrentTime = System.nanoTime();
            DeltaTime += (CurrentTime - LastTime);

            TimeElapsed += (CurrentTime - LastTime); //check for how many Frames per second

            LastTime = CurrentTime;      //ready for the NEXT LAST TIME

            while(DeltaTime >= TimePerFrame)
            {
                GameUpdate();
                DeltaTime -= TimePerFrame;
            }

            FramesCounter++;

            if(TimeElapsed >= 1000000000)   //display FPS every second
            {
                m_Display.GetWindow().setTitle("JavaBullshit Project by TRIPLE_H FPS: "+FramesCounter);
                FramesCounter = 0;
                TimeElapsed = 0;
            }

            GameRender();
        }

        GameStop();
    }

    private void GameStop()
    {
        if(GameIsRunning) //Game running ---> stop --> not run + join Thread
        {

            try
            {
                GameThread.join();       //join with the Main function
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            GameIsRunning = false;
        }
    }


    public int GetGameWidth()
    {
        return GameWidth;
    }
    public int GetGameHeight()
    {
        return GameHeight;
    }
    public KeyManager GetKeyManager(){ return m_KeyManager; }
    public MouseManager GetMouseManager(){
        return m_MouseManager;
    }



    //EXECUTE 1ST,2ND,3RD     (for future debugging)

    public Game(String GameTitle,int GameWidth,int GameHeight)         //EXECUTE 1ST
    {
        this.GameTitle = GameTitle;
        this.GameHeight = GameHeight;
        this.GameWidth = GameWidth;
    }


    public void GameStart()          //EXECUTE 2ND
    {

        if(!GameIsRunning)     //not to create multiple Threads while GameIsRunning --> or mess Threads up!!!
        {
            GameIsRunning = true;
            GameThread = new Thread(this);   //this current Game object will be in a new Thread
            GameThread.start();    //auto executed the run() method
        }
        else
        {
            System.out.println("Game init failed !!!");
        }
    }

    @Override
    public void run()           //EXECUTE 3RD
    {
        if(GameInit()) {
            GameRun();          //FINALLY EVERYTHING IS RUNNING IS THIS FUNCTION
        }
        else
        {
            System.out.println("Cannot init the game");
        }
    }

}
