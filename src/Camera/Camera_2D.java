package Camera;

import BaseMainGame.Game;
import Entity.Entity;
import States.GameState;

public class Camera_2D {        //SINGLETON

    private float xOffset;
    private float yOffset;
    private float x_amount;
    private float y_amount;

    private Game m_gameObject;

    public static void Camera_2D_Init(Game _gameObject)        //will be init in our GAME.init() before
                                               //somewhere using the camera
    {
        //create a camera object
        Camera_2D camera_2d_object = GetInstance();


        camera_2d_object.m_gameObject = _gameObject;
    }

    private Camera_2D()
    {
        //testing
        xOffset = 0;
        yOffset = 0;
        x_amount = 0;
        y_amount = 0;
    }

    private static Camera_2D m_instance = null;

    public static Camera_2D GetInstance()
    {
        if(m_instance == null)
        {
            m_instance = new Camera_2D();
        }

        return m_instance;
    }

    public void Move(int X_amount, int Y_amount)
    {
        this.x_amount = X_amount;
        this.y_amount = Y_amount;

    }

    public void Update()       //used in GameState.update()
    {
        xOffset += x_amount;
        yOffset += y_amount;
    }

    public float getxOffset()
    {
        return this.xOffset;
    }

    public float getyOffset()
    {
        return this.yOffset;
    }

    public void setxOffset(int input)
    {
        this.xOffset = input;
    }

    public void setyOffset(int input)
    {
        this.yOffset = input;
    }

    public float getX_amount()
    {
        return x_amount;
    }

    public float getY_amount()
    {
        return y_amount;
    }

    public void CenterOnEntity(Entity passed_Entity)        //keep that Entity in the Center of the screen
    {
        xOffset = passed_Entity.getX() - m_gameObject.GetGameWidth()/2;
        yOffset = passed_Entity.getY() - m_gameObject.GetGameHeight()/2;
    }
}
