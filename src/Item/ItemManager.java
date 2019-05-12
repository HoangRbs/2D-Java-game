package Item;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {           //to update and render Item in gameWorld
    ArrayList<Item> items;

    public ItemManager()
    {
        items = new ArrayList<Item>();

    }

    public void Update()
    {
        Item currentItem;
        Iterator<Item> it = items.iterator();
        while(it.hasNext())
        {
            currentItem = it.next();
            currentItem.Update();
            if(currentItem.get_isPickedUp())
            {
                it.remove();
            }
        }
    }

    public void Render(Graphics m_RealScreenObject)
    {
        for(Item e : items)
        {
            e.Render(m_RealScreenObject);
        }
    }

    public void AddItem(Item e)
    {
        items.add(e);
    }
}
