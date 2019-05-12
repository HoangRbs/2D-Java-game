package Path_Fiding;

import Camera.Camera_2D;
import Tile.TileInitStuff;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Node {

    public Node(float PosX, float PosY, String Name)
    {
        previousNode = null;
        currentCost = -1;       //define as INFINITY
        FutureCost = -1;        //INFINITY
        is_Visited = false;
        is_Obstacle = false;
        is_BeingInsideNodesToTest = false;

        Neighbors = new ArrayList<Node>();
        this.Name = Name;

        x = PosX;
        y = PosY;

        NodeBoundery = new Rectangle((int)x ,(int)y ,30,30);
    }

    public Node previousNode;  //Previous connected node

    public int currentCost;  //current traveled distance by previous node
    public int FutureCost;   //CurrentCost + heuristic()

    public boolean is_Visited;
    public boolean is_Obstacle;
    public boolean is_BeingInsideNodesToTest;     //to check if the current Node is inside NodeToTest
                                             //will be used

    public String Name;       //just for testing

    public float x,y;     //Node position
    public ArrayList<Node> Neighbors;

    public void Update()
    {
        //....
    }

    public void Render(Graphics m_RealScreenObject)
    {
        m_RealScreenObject.setColor(NodeColor);
        m_RealScreenObject.fillRect(NodeBoundery.x + 16 - (int)Camera_2D.GetInstance().getxOffset(),
                                    NodeBoundery.y + 16 - (int)Camera_2D.GetInstance().getyOffset(),
                                    NodeBoundery.width,
                                    NodeBoundery.height);
    }

    public void Reset()
    {
        previousNode = null;
        currentCost = -1;       //define as INFINITY
        FutureCost = -1;        //INFINITY
        is_Visited = false;
        NodeColor = Color.gray;      //since visited = false;
        is_BeingInsideNodesToTest = false;
    }

    //for debugging purpose
    public Rectangle NodeBoundery;
    public Color NodeColor = Color.gray;

    public float getCenterX()
    {
        return(x + TileInitStuff.TILE_WIDTH/2);
    }

    public float getCenterY()
    {
        return(y + TileInitStuff.TILE_HEIGHT/2);
    }
}
