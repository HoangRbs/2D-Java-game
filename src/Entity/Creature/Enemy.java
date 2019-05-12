package Entity.Creature;

import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Entity.Bullet.PlayerBullet;
import Item.Item;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Creature{
    protected BufferedImage EnemyTexture;
    protected Item EnemyDeadItem;

    public Enemy(int _x,int _y)
    {
        super(_x,_y);
    }

    @Override
    public void Update()
    {
        //super.Update(); do not use this..yet
        x += Velocity_x;
        y += Velocity_y;

        checkCollideWithPlayerBullet();
        checkCollideWithPlayer();
    }

    @Override
    public void Render(Graphics m_RealScreenObject) {
        m_RealScreenObject.drawImage(EnemyTexture,(int)(x - Camera_2D.GetInstance().getxOffset()),
                                                  (int)(y - Camera_2D.GetInstance().getyOffset()),
                                                    EntityWidth,EntityHeight,null);
    }

    @Override
    protected void Die() {
        //System.out.println("Enemy die");
    }

    protected void checkCollideWithPlayerBullet()
    {
        for(PlayerBullet currentPlayerBullet : GameHandler.GetInstance().get_m_PlayerBullets())
        {
            if(currentPlayerBullet.getTestingBoundingBox().intersects(getTestingBoundingBox()))
            {
                currentPlayerBullet.getHurt(1);
                this.getHurt(1);
            }
        }
    }

    protected  void checkCollideWithPlayer()   //should not use this--> reduce performance --> use isCollide with
                                                // other entities in Player instead --> fix later
    {
        Player m_Player = GameHandler.GetInstance().get_m_Player();
        if(m_Player == null)
            return;

        if(m_Player.getTestingBoundingBox().intersects(this.getTestingBoundingBox()))
        {
            m_Player.getHurt(30);      //for Player to have instance death
        }
    }
}
