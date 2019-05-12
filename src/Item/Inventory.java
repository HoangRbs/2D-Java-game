package Item;

import BaseMainGame.GameHandler;
import gfx.Assets;
import gfx.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Inventory {       //to render and update item inside inventory

    private boolean TurnOn = false;
    private ArrayList<Item> InventoryItems;
    private BufferedImage InventoryTexture;
    private int InventoryX = 100;
    private int InventoryY = 100;
    private int InventoryWidth = 300;
    private int InventoryHeight = 300;
    private int SelectedItem = 0;

    public Inventory()
    {
        InventoryItems = new ArrayList<Item>();
        InventoryTexture = Assets.GetInstance().Inventory;
    }

    public void Update()
    {
        if(GameHandler.GetInstance().getKeyManager().isJustPress(KeyEvent.VK_E))
        {
            TurnOn = !TurnOn;
        }

        if(!TurnOn)
        {
            return;
        }

        /*
        System.out.println("INVENTORY :");
        for(Item i : InventoryItems)
        {
            System.out.println(i.getItemName() + " " + i.getCount());
        }
        */

        if(GameHandler.GetInstance().getKeyManager().isJustPress(KeyEvent.VK_RIGHT))
        {
            SelectedItem++;
        }

        if(GameHandler.GetInstance().getKeyManager().isJustPress(KeyEvent.VK_LEFT))
        {
            SelectedItem--;
        }

        if(SelectedItem < 0)
        {
            SelectedItem = InventoryItems.size() - 1;
        }
        else if(SelectedItem >= InventoryItems.size())
        {
            SelectedItem = 0;
        }
    }

    public void Render(Graphics m_RealScreenObject)
    {
        if(!TurnOn)
            return;

        m_RealScreenObject.drawImage(InventoryTexture,InventoryX,InventoryY,InventoryWidth,InventoryHeight,null);

        Text.DrawText(m_RealScreenObject,"Inventory",InventoryX + 20,InventoryY + 40,Color.red,Assets.GetInstance().m_StandardFont25);

        if(InventoryItems.size() == 0)
            return;

        m_RealScreenObject.drawImage(InventoryItems.get(SelectedItem).ItemTexture,
                                     InventoryX + 50,InventoryY + 100,100,100,null);

        Text.DrawText(m_RealScreenObject,InventoryItems.get(SelectedItem).getItemName(),
                      InventoryX + 75,InventoryY + 280,Color.red,Assets.GetInstance().m_StandardFont25);

        Text.DrawText(m_RealScreenObject,Integer.toString(InventoryItems.get(SelectedItem).getCount()),
                InventoryX + 225,InventoryY + 140,Color.red,Assets.GetInstance().m_StandardFont25);

    }

    public void addItem(Item _item)
    {
        for(Item i : InventoryItems)
        {
            if(i.getItemID() == _item.getItemID())       //same Item Type
            {
                i.setCount(i.getCount() + _item.getCount());
                return;
            }
        }

        InventoryItems.add(_item);
    }

    public boolean Get_isTurnOn()
    {
        return TurnOn;
    }
}
