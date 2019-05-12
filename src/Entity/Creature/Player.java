package Entity.Creature;

import BaseMainGame.Game;
import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Entity.Bullet.PlayerBullet;
import Entity.Entity;
import Item.Inventory;
import Tile.TileInitStuff;
import gfx.Animation;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Creature {

    private Game m_GameObject;
    private Inventory m_Inventory;
    private ArrayList<PlayerBullet> m_PlayerBullets;

    public Player(int x_,int y_)
    {
        super(x_,y_);
        EntityWidth = 60;
        EntityHeight = 60;

        //FOR COLLISION DETECTION PURPOSE
        TestingBoundingBox = new Rectangle(0,0,0,0);
        TestingBoundingBox.x = 0 + 10;
        TestingBoundingBox.y = 0 + 10;
        TestingBoundingBox.width = EntityWidth - 20;
        TestingBoundingBox.height = EntityHeight - 20;

        Animations_INIT();

        m_Inventory = new Inventory();
        m_PlayerBullets = new ArrayList<PlayerBullet>();
        GameHandler.GetInstance().set_m_PlayerBullets(m_PlayerBullets);
        GameHandler.GetInstance().save_m_PlayerInventory(m_Inventory);  //when player die but
                                                                //player --> null but we still can save the
                                                                //player inventory to Update and Render
                                                                //-->that's how java works

        Health = 10;
    }

    public void SetGameObject(Game _GameObject)
    {
        m_GameObject = _GameObject;
    }

    @Override
    public void Update()
    {
        GetInput();      //control Velocity and add Bullets

        //this function change VELOCITY --> put above super.update()
        checkTileCollision();

        //if(!m_Inventory.Get_isTurnOn()) {
            super.Update();      //Creature.update() --> update position though VEL_X,VEL_Y

            //any above functions change the velocity --> put this function below
            Animations_Update();     //cuz this function depends on velocity

            checkAttack();
        //}

        Camera_2D.GetInstance().CenterOnEntity(this);         //OUR CAMERA WILL CENTER ON PLAYER

        //Bullets
        Iterator<PlayerBullet> PB_i = m_PlayerBullets.iterator();
        while(PB_i.hasNext())
        {
            PlayerBullet currentPlayerBullet = PB_i.next();
            currentPlayerBullet.Update();
            if(!currentPlayerBullet.is_Active())
                PB_i.remove();
        }

        //Inventory
        m_Inventory.Update();
    }

    @Override
    public void Render(Graphics m_RealScreenObject)
    {
        /*
        m_RealScreenObject.drawImage(Assets.GetInstance().Player_texture,
                                    (int)(x - Camera_2D.GetInstance().getxOffset()),
                                    (int)(y - Camera_2D.GetInstance().getyOffset()),
                                    EntityWidth,EntityHeight,null);
                                   */


        //TESTING BOUNDING BOX FOR COLLISION
        //m_RealScreenObject.setColor(Color.red);
        //m_RealScreenObject.fillRect((int)(x + TestingBoundingBox.x - Camera_2D.GetInstance().getxOffset()),
        //(int)(y + TestingBoundingBox.y - Camera_2D.GetInstance().getyOffset()),
        //                            TestingBoundingBox.width,TestingBoundingBox.height);

        Animations_Render(m_RealScreenObject);

        //Inventory
        //m_Inventory.Render(m_RealScreenObject);

        //Bullets
        for(PlayerBullet currentPlayerBullet : m_PlayerBullets)
        {
            currentPlayerBullet.Render(m_RealScreenObject);
        }
    }

    public void RenderInventoryLast(Graphics m_RealScreenObject)    //used in EntitiesManager
    {
        m_Inventory.Render(m_RealScreenObject);
    }

    private void GetInput()
    {
        Velocity_x = 0;
        Velocity_y = 0;

        if(m_GameObject.GetKeyManager().isPressUp) {
            Velocity_y = -3;
        }
        if(m_GameObject.GetKeyManager().isPressDown) {
            Velocity_y = 3;
        }
        if(m_GameObject.GetKeyManager().isPressLeft) {
            Velocity_x = -3;
        }
        if(m_GameObject.GetKeyManager().isPressRight) {
            Velocity_x = 3;
        }
    }

    private boolean is_CollideWithTile(int x,int y)
    {
        if(GameHandler.GetInstance().get_World2dTile_object().getTileAt(y,x).isCollidable())
            return true;
        return false;
    }

    private void checkTileCollision()
    {
        if(Velocity_x > 0) //moving right
        {
            float newPos_x = x + Velocity_x;
            int X_indexOnTileMap = (int)(newPos_x + TestingBoundingBox.x + TestingBoundingBox.width)/ TileInitStuff.TILE_WIDTH;

            int Y_upperRight_indexOnTileMap = (int)(y + TestingBoundingBox.y)/TileInitStuff.TILE_HEIGHT;
            int Y_bottomRight_indexOnTileMap = (int)(y + TestingBoundingBox.y + TestingBoundingBox.height)/TileInitStuff.TILE_HEIGHT;

            if(is_CollideWithTile(X_indexOnTileMap,Y_upperRight_indexOnTileMap)
                || is_CollideWithTile(X_indexOnTileMap,Y_bottomRight_indexOnTileMap))    //collide with a collidable Tile
            {
                Velocity_x = 0;    //stop moving right
            }
        }
        else if(Velocity_x < 0)             //moving left
        {
            float newPos_x = x + Velocity_x;
            int X_indexOnTileMap = (int)(newPos_x + TestingBoundingBox.x)/ TileInitStuff.TILE_WIDTH;
            int Y_upperRight_indexOnTileMap = (int)(y + TestingBoundingBox.y)/TileInitStuff.TILE_HEIGHT;
            int Y_bottomRight_indexOnTileMap = (int)(y + TestingBoundingBox.y + TestingBoundingBox.height)/TileInitStuff.TILE_HEIGHT;

            if(is_CollideWithTile(X_indexOnTileMap,Y_upperRight_indexOnTileMap)
               || is_CollideWithTile(X_indexOnTileMap,Y_bottomRight_indexOnTileMap))    //collide with a collidable Tile
            {
                Velocity_x = 0;    //stop moving left
            }

        }

        if(Velocity_y > 0)  //moving down
        {
            float newPos_y = y + Velocity_y;
            int Y_indexOnTileMap = (int)(newPos_y + TestingBoundingBox.y + TestingBoundingBox.height)/ TileInitStuff.TILE_HEIGHT;

            int X_Left_indexOnTileMap = (int)(x + TestingBoundingBox.x)/TileInitStuff.TILE_WIDTH;
            int X_Right_indexOnTileMap = (int)(x + TestingBoundingBox.x + TestingBoundingBox.width)/TileInitStuff.TILE_WIDTH;

            if(is_CollideWithTile(X_Left_indexOnTileMap,Y_indexOnTileMap)
                    || is_CollideWithTile(X_Right_indexOnTileMap,Y_indexOnTileMap))    //collide with a collidable Tile
            {
                Velocity_y = 0;    //stop moving down
            }
        }
        else if(Velocity_y < 0) //moving up
        {
            float newPos_y = y + Velocity_y;
            int Y_indexOnTileMap = (int)(newPos_y + TestingBoundingBox.y)/ TileInitStuff.TILE_HEIGHT;

            int X_Left_indexOnTileMap = (int)(x + TestingBoundingBox.x)/TileInitStuff.TILE_WIDTH;
            int X_Right_indexOnTileMap = (int)(x + TestingBoundingBox.x + TestingBoundingBox.width)/TileInitStuff.TILE_WIDTH;

            if(is_CollideWithTile(X_Left_indexOnTileMap,Y_indexOnTileMap)
                    || is_CollideWithTile(X_Right_indexOnTileMap,Y_indexOnTileMap))    //collide with a collidable Tile
            {
                Velocity_y = 0;    //stop moving up
            }
        }
    }

    //ANIMATIONS
    private Animation PlayerStandStillAnim;
    private Animation PlayerDownAnim;
    private Animation PlayerUpAnim;
    private Animation PlayerRightAnim;
    private Animation PlayerLeftAnim;

    private void Animations_INIT()      //used in PlayerConstructor
    {
        PlayerStandStillAnim = new Animation(500,Assets.GetInstance().PlayerStandStillAnim);
        PlayerDownAnim = new Animation(500,Assets.GetInstance().PlayerDownAnim);
        PlayerUpAnim = new Animation(500,Assets.GetInstance().PlayerUpAnim);
        PlayerRightAnim = new Animation(500,Assets.GetInstance().PlayerRightAnim);
        PlayerLeftAnim = new Animation(500,Assets.GetInstance().PlayerLeftAnim);
    }

    private void Animations_Update()
    {
        PlayerStandStillAnim.update();
        PlayerDownAnim.update();
        PlayerUpAnim.update();
        PlayerRightAnim.update();
        PlayerLeftAnim.update();
    }

    private void Animations_Render(Graphics m_RealScreenObject)
    {
        if(Velocity_x > 0)
        {
            m_RealScreenObject.drawImage(PlayerRightAnim.getCurrentAnimFrame(),
                                        (int)(x - Camera_2D.GetInstance().getxOffset()),
                                        (int)(y - Camera_2D.GetInstance().getyOffset()),
                                        EntityWidth,EntityHeight,null);
        }
        else if(Velocity_x < 0)
        {
            m_RealScreenObject.drawImage(PlayerLeftAnim.getCurrentAnimFrame(),
                    (int)(x - Camera_2D.GetInstance().getxOffset()),
                    (int)(y - Camera_2D.GetInstance().getyOffset()),
                    EntityWidth,EntityHeight,null);
        }
        else     // vel_x == 0
        {
             if(Velocity_y > 0)
             {
                 m_RealScreenObject.drawImage(PlayerDownAnim.getCurrentAnimFrame(),
                         (int)(x - Camera_2D.GetInstance().getxOffset()),
                         (int)(y - Camera_2D.GetInstance().getyOffset()),
                         EntityWidth,EntityHeight,null);
             }
             else if(Velocity_y < 0)
             {
                 m_RealScreenObject.drawImage(PlayerUpAnim.getCurrentAnimFrame(),
                         (int)(x - Camera_2D.GetInstance().getxOffset()),
                         (int)(y - Camera_2D.GetInstance().getyOffset()),
                         EntityWidth,EntityHeight,null);
             }
             else   //== 0
             {
                 m_RealScreenObject.drawImage(PlayerStandStillAnim.getCurrentAnimFrame(),
                         (int)(x - Camera_2D.GetInstance().getxOffset()),
                         (int)(y - Camera_2D.GetInstance().getyOffset()),
                         EntityWidth,EntityHeight,null);
             }
        }

    }

    protected void Die()
    {
        System.out.println(" YOU LOSE ");
        GameHandler.GetInstance().set_m_Player(null);
    }

    private long  LastAttackTime, AttackCoolDown = 200, AttackTimer = AttackCoolDown;

    //ATTACK ENTITIES
    private void checkAttack()
    {
        AttackTimer += System.currentTimeMillis() - LastAttackTime;
        LastAttackTime = System.currentTimeMillis();
        if(AttackTimer < AttackCoolDown)
            return;

        Rectangle CollisionBound = getTestingBoundingBox();
        Rectangle AttackRectangle = new Rectangle();

        int AttackSize = 20;

        AttackRectangle.width = AttackSize;
        AttackRectangle.height = AttackSize;

        if(GameHandler.GetInstance().getKeyManager().isPressA_Up)
        {
            AttackRectangle.x = CollisionBound.x + CollisionBound.width/2 - AttackSize/2;
            AttackRectangle.y = CollisionBound.y - AttackSize;
        }
        else if(GameHandler.GetInstance().getKeyManager().isPressA_Down)
        {
            AttackRectangle.x = CollisionBound.x + CollisionBound.width/2 - AttackSize/2;
            AttackRectangle.y = CollisionBound.y + CollisionBound.height;
        }
        else if(GameHandler.GetInstance().getKeyManager().isPressA_Left)
        {
            AttackRectangle.x = CollisionBound.x - AttackSize;
            AttackRectangle.y = CollisionBound.y + CollisionBound.height/2 + AttackSize/2;
        }
        else if(GameHandler.GetInstance().getKeyManager().isPressA_Right)
        {
            AttackRectangle.x = CollisionBound.x + CollisionBound.width;
            AttackRectangle.y = CollisionBound.y + CollisionBound.height/2 + AttackSize/2;
        }
        else if(GameHandler.GetInstance().getMouseManager().get_isLeftMouse_Pressed())   //FIRE BULLETS
        {
            int MousePosx = GameHandler.GetInstance().getMouseManager().getMouse_x();
            int MousePosY = GameHandler.GetInstance().getMouseManager().getMouse_y();

            //since Mouse us position on screen and Player.x is the pos in gameWorld
            //infact Player only at the center of the screen --> no need to get the Player coordinate
            double DeltaX = MousePosx - (m_GameObject.GetGameWidth()/2);
            double DeltaY = MousePosY - (m_GameObject.GetGameHeight()/2);
            double BulletVelX = 0;
            double BulletVelY = 0;
            double Phi = Math.atan2(DeltaY,DeltaX);   //important function
            double BulletVelMax = 8;
            BulletVelX = BulletVelMax * Math.cos(Phi);
            BulletVelY = BulletVelMax * Math.sin(Phi);

            m_PlayerBullets.add(new PlayerBullet((int)x + 20,(int)y + 20,BulletVelX,BulletVelY));
        }
        else  //did not press any attack key
        {
            return;
        }

        AttackTimer = 0;

        //check if the Attack Rectangle intersec with other entities
        for(Entity e : GameHandler.GetInstance().get_m_EntitiesManager().get_m_Entities())
        {
            if(e.equals(this))
            {
                continue;
            }

            if(AttackRectangle.intersects(e.getTestingBoundingBox()))
            {
                e.getHurt(1);
                return;    //only attack one entity at a time
            }
        }
    }

    public Inventory get_m_Inventory()
    {
        return m_Inventory;
    }
}
