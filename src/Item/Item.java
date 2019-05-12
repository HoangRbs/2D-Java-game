package Item;

import BaseMainGame.GameHandler;
import Camera.Camera_2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public final static int ITEMWIDTH = 70, ITEMHEIGHT = 60;

    protected BufferedImage ItemTexture;
    protected String ItemName;
    protected final int ItemID;
    protected int x,y;
    protected Rectangle boundingBox;

    protected boolean isPickedUp = false;

    protected int Count = 1;       //default is 1

    public Item(BufferedImage ItemTexture,String ItemName,int ItemID)
    {
        this.ItemTexture = ItemTexture;
        this.ItemName = ItemName;
        this.ItemID = ItemID;

        Kinds_Of_Item.Kinds_Of_Item.add(this);

        boundingBox = new Rectangle(x,y,ITEMWIDTH,ITEMHEIGHT);
    }

    public void Update()
    {
        if(GameHandler.GetInstance().get_m_Player() == null)
            return;

        if(boundingBox.intersects(GameHandler.GetInstance().get_m_Player().getTestingBoundingBox()))
        {
            isPickedUp = true;
            GameHandler.GetInstance().get_m_Player().get_m_Inventory().addItem(this);
        }
    }

    public void Render(Graphics m_RealScreenObject)          //render in game world
    {
        m_RealScreenObject.drawImage(ItemTexture,(int)(x - Camera_2D.GetInstance().getxOffset()),
                                                 (int)(y - Camera_2D.GetInstance().getyOffset()),
                                                  ITEMWIDTH,ITEMHEIGHT,null);
    }

    public void Render(Graphics m_RealScreenObject, int _x, int _y)      //for render item in inventory screen
    {
        //useless function now
    }

    public Item CreateNewCopy(int x,int y)    //Copy with different position
    {
        Item temp = new Item(ItemTexture,ItemName,ItemID);
        temp.setPos(x,y);
        return temp;
    }

    private void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;

        boundingBox.x = this.x;
        boundingBox.y = this.y;
    }

    public boolean get_isPickedUp()
    {
        return isPickedUp;
    }

    public void set_isPickedUp(boolean input)
    {
        isPickedUp = input;
    }

    public int getItemID()
    {
        return this.ItemID;
    }

    public void setCount(int _num)
    {
        this.Count = _num;
    }

    public int getCount()
    {
        return this.Count;
    }

    public String getItemName()
    {
        return  ItemName;
    }
}
