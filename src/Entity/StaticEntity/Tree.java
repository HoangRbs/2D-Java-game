package Entity.StaticEntity;

import BaseMainGame.GameHandler;
import Camera.Camera_2D;
import Item.ItemManager;
import Item.Kinds_Of_Item;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tree extends StaticEntity {

    private BufferedImage TreeTexture;

    public Tree(int _x,int _y)
    {
        super(_x,_y);
        TreeTexture = Assets.GetInstance().Tree_texture;
        EntityWidth = 200;
        EntityHeight = 300;

        //FOR COLLISION DETECTION PURPOSE
        TestingBoundingBox = new Rectangle(0,0,0,0);
        TestingBoundingBox.x = 0 + 60;
        TestingBoundingBox.y = 0 + 265;
        TestingBoundingBox.width = EntityWidth - 120;
        TestingBoundingBox.height = EntityHeight - 275;
    }

    @Override
    public void Update()
    {

    }

    @Override
    public void Render(Graphics m_RealScreenObject) {
        m_RealScreenObject.drawImage(TreeTexture,(int)(x - Camera_2D.GetInstance().getxOffset()),
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
                Kinds_Of_Item.WoodItem.CreateNewCopy((int)x + 80,(int)y + 270));
    }

}
