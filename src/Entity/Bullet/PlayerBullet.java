package Entity.Bullet;

import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Entity.Entity;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBullet extends Entity {

    public PlayerBullet(int _x,int _y,double BulletVelX,double BulletVelY)
    {
        super(_x,_y);
        Health = 1;
        PlayerBulletTexture = Assets.GetInstance().PlayerBullet;

        //for later collision detection inside Enemy class
        EntityWidth = 20;
        EntityHeight = 20;
        TestingBoundingBox = new Rectangle(0,0,EntityWidth,EntityHeight);

        this.BulletVelX = BulletVelX;
        this.BulletVelY = BulletVelY;
    }

    private double BulletVelX;
    private double BulletVelY;
    private BufferedImage PlayerBulletTexture;

    @Override
    public void Update() {
        x += BulletVelX;
        y += BulletVelY;

        float CameraGetX = Camera_2D.GetInstance().getxOffset();
        float CameraGetY = Camera_2D.GetInstance().getyOffset();
        int GameWidth = GameHandler.GetInstance().get_m_gameObject().GetGameWidth();
        int GameHeigh = GameHandler.GetInstance().get_m_gameObject().GetGameHeight();

        if(x  < CameraGetX || x > CameraGetX + GameWidth || y < CameraGetY || y > CameraGetY + GameHeigh)
            isActive = false;
    }

    @Override
    public void Render(Graphics m_RealScreenObject) {
        m_RealScreenObject.drawImage(PlayerBulletTexture,
                                    (int)(x - Camera_2D.GetInstance().getxOffset()),
                                    (int)(y - Camera_2D.GetInstance().getyOffset()),
                                     EntityWidth,EntityHeight,null);
    }

    @Override     //Die() is implement when Entity.getHurt()
    protected void Die() {
        //System.out.println("PlayerBullet Die");
    }
}
