package Entity.Creature;

import BaseMainGame.GameHandler;
import BaseMainGame.Worlds.World_2D_Tile;
import Camera.Camera_2D;
import Item.Item;
import Item.Kinds_Of_Item;
import Path_Fiding.A_StarAlgorithm;
import Path_Fiding.Node;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PathFollowEnemy extends Enemy {

    //USING A* TO FOLLOW PLAYER
    private Player m_Player;
    private float DefaultSpeedX;
    private float DefaultSpeedY;

    public PathFollowEnemy(int _x, int _y, float SpeedX, float SpeedY, BufferedImage _EnemyTexture,Item _EnemyDeadItem)
    {
        super(_x,_y);
        m_Player = GameHandler.GetInstance().get_m_Player();
        Health = 2;
        EntityWidth = 60;
        EntityHeight = 60;
        EnemyTexture = _EnemyTexture;
        EnemyDeadItem = _EnemyDeadItem;

        //FOR COLLISION DETECTION PURPOSE
        TestingBoundingBox = new Rectangle();
        TestingBoundingBox.x = 0;
        TestingBoundingBox.y = 0;
        TestingBoundingBox.width = EntityWidth;
        TestingBoundingBox.height = EntityHeight;

        //set up different velocity not to colapse all AI enemies
        //because they all use the same algorithm
        this.DefaultSpeedX = SpeedX;
        this.DefaultSpeedY = SpeedY;
    }

    @Override
    public void Update()
    {
        if(m_Player != null)
        {
            A_StarAlgorithm.setStart_EndNode((int)m_Player.getCenterX(),(int)m_Player.getCenterY(),
                                             (int)getCenterX(),(int)getCenterY());
            A_StarAlgorithm.Update();

            //right now where Enemy is standing is EndNode
            Node previousNode = A_StarAlgorithm.getEndNode().previousNode;
            if(previousNode != null)
            {
                float tempX = previousNode.x - this.x;
                if(tempX > 0)
                {
                    Velocity_x = DefaultSpeedX;
                }
                else if(tempX < 0)
                {
                    Velocity_x = -DefaultSpeedX;
                }
                else
                {
                    Velocity_x = 0;
                }

                float tempY = previousNode.y - this.y;
                if(tempY > 0)
                {
                    Velocity_y = DefaultSpeedY;
                }
                else if(tempY < 0)
                {
                    Velocity_y = -DefaultSpeedY;
                }
                else
                {
                    Velocity_y = 0;
                }
            }
        }

        super.Update();
    }

    public void Render(Graphics m_RealScreenObject)
    {
        //AI - A STAR ALGORITHM
        //A_StarAlgorithm.Render(m_RealScreenObject);  FOR TESTING

        super.Render(m_RealScreenObject);
    }

    public void Die()
    {
        //System.out.println("PathFollowEnemy die");

        //NEXT:
        //Display Item at current position
        GameHandler.GetInstance().get_m_ItemManager().AddItem(
                  EnemyDeadItem.CreateNewCopy((int)x,(int)y));

        //generate random from 0 to 3
        int randomIndex = (int)(Math.random() * 4) + 0;
        GameHandler.GetInstance().get_m_EntitiesManager().addEntity(
                World_2D_Tile.RandomGenerateEnemy.get(randomIndex).CreateNewCopy());
    }

    public PathFollowEnemy CreateNewCopy() //used in World2DTile and function above
    {
        return new PathFollowEnemy((int)this.x,(int)this.y,this.DefaultSpeedX,this.DefaultSpeedY,this.EnemyTexture,this.EnemyDeadItem);
    }
}
