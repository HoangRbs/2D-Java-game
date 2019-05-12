package Entity.StaticEntity;

import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Item.Kinds_Of_Item;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rock extends StaticEntity {

    private BufferedImage   RockTexture;

    public Rock(int _x,int _y)
    {
        super(_x,_y);
        RockTexture = Assets.GetInstance().Rock_texture;
        EntityWidth = 100;
        EntityHeight = 100;

        //FOR COLLISION DETECTION PURPOSE
        TestingBoundingBox = new Rectangle(0,0,0,0);
        TestingBoundingBox.x = 0 + 10;
        TestingBoundingBox.y = 0 + 40;
        TestingBoundingBox.width = EntityWidth - 20;
        TestingBoundingBox.height = EntityHeight - 50;
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Render(Graphics m_RealScreenObject) {
        m_RealScreenObject.drawImage(RockTexture,(int)(x - Camera_2D.GetInstance().getxOffset()),
                (int)(y - Camera_2D.GetInstance().getyOffset()),
                EntityWidth,EntityHeight,null);

        //TESTING BOUNDING BOX FOR COLLISION
        //m_RealScreenObject.setColor(Color.red);
        //m_RealScreenObject.fillRect((int)(x + TestingBoundingBox.x - Camera_2D.GetInstance().getxOffset()),
        //                            (int)(y + TestingBoundingBox.y - Camera_2D.GetInstance().getyOffset()),
        //                                  TestingBoundingBox.width,TestingBoundingBox.height);
    }

    public void Die()
    {
        GameHandler.GetInstance().get_m_ItemManager().AddItem(
                Kinds_Of_Item.RockItem.CreateNewCopy((int)x + 10,(int)y + 30));
    }
}
