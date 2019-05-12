package Entity.Creature;

import Entity.Entity;

import java.awt.*;

public abstract class Creature extends Entity {

    public Creature(int x_,int y_)
    {
        super(x_,y_);
        Health = 100;      //set bigger health for Creature entities
    }

    protected float Velocity_x = 0;
    protected float Velocity_y = 0;

    public void Update()
    {
        //avoid using this to other type pf creature
        //this should only be used for PLAYER ONLY

        //CHECK COLLISION WITH OTHER ENTITIES

        //check horizontal first
        float newPos_x = x + Velocity_x;
        float newPos_y = y + 0;
        if(is_CollideWithOtherEntities(newPos_x,newPos_y))
        {
            Velocity_x = 0;
        }

        //after that --> check vertical
        newPos_x = x;
        newPos_y = y + Velocity_y;
        if(is_CollideWithOtherEntities(newPos_x,newPos_y))
        {
            Velocity_y = 0;
        }

        x += Velocity_x;
        y += Velocity_y;
    }
}
