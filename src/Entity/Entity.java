package Entity;

import BaseMainGame.GameHandler;

import java.awt.*;

public abstract class Entity {
    protected float x;        //there will be a VECTOR class later
    protected float y;
    protected int EntityWidth;
    protected int EntityHeight;
    protected  int Health;
    protected boolean isActive = true;

    protected Rectangle TestingBoundingBox;       //for collision detection

    public Entity(int x,int y)
    {
        this.x = x;
        this.y = y;

        Health = 10;       //default health for all entities
    }

    public abstract void Update();
    public abstract void Render(Graphics m_RealScreenObject);

    public float getX()
    {
        return this.x;
    }

    public float getY()
    {
        return this.y;
    }

    public float getCenterX()
    {
        return(x + EntityWidth/2);
    }

    public float getCenterY()
    {
        return (y + EntityHeight/2);
    }

    public int getEntityWidth()
    {
        return EntityWidth;
    }

    public int getEntityHeight()
    {
        return EntityHeight;
    }

    //FOR COLLISION DETECTION
    public Rectangle getTestingBoundingBox()
    {
        //this tempBoundingBox is not the default bounding box
        //it PLUS the current object coordinate to follow the object
        return new Rectangle(TestingBoundingBox.x + (int)x,
                             TestingBoundingBox.y + (int)y,
                                TestingBoundingBox.width,
                                TestingBoundingBox.height);
    }

    //this function should only be used for Player only --> in CREATURE UPDATE()
    public boolean is_CollideWithOtherEntities(float newPos_x,float newPos_y)
    {
        Rectangle new_tempBoundingBox = new Rectangle(TestingBoundingBox.x + (int)newPos_x,
                                                 TestingBoundingBox.y + (int)newPos_y,
                                                 TestingBoundingBox.width,
                                                 TestingBoundingBox.height);

        EntityManager GameEntities = GameHandler.GetInstance().get_m_EntitiesManager();
        for(Entity e : GameEntities.get_m_Entities())
        {
            if(e.equals(this))  //should use equal() instead of using == // maybe it's just deeper
            {
                continue;
            }
            if(new_tempBoundingBox.intersects(e.getTestingBoundingBox()))
                return true;
        }

        return false;
    }

    public void getHurt(int amount)
    {
        Health -= amount;
        if(Health <= 0)
        {
            isActive = false;
            Die();
        }
    }

    protected abstract void Die();  //Each kind of Entity has its own Die style :))

    public boolean is_Active()
    {
        return this.isActive;
    }
}
