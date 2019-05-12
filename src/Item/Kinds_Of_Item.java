package Item;

import gfx.Assets;

import java.util.ArrayList;

public class Kinds_Of_Item {

    public static ArrayList<Item> Kinds_Of_Item = new ArrayList<Item>();

    //auto add its self into KindsOfItem list in constructor
    public static Item WoodItem = new Item(Assets.GetInstance().WoodItem,"WoodItem",0);
    public static Item RockItem = new Item(Assets.GetInstance().RockItem,"RockItem",1);
    public static Item Enemy1DeadItem = new Item(Assets.GetInstance().Enemy1DeadItem,"Enemy1",2);
    public static Item Enemy2DeadItem = new Item(Assets.GetInstance().Enemy2DeadItem,"Enemy2",3);
    public static Item Enemy3DeadItem = new Item(Assets.GetInstance().Enemy3DeadItem,"Enemy3",4);
    public static Item Enemy4DeadItem = new Item(Assets.GetInstance().Enemy4DeadItem,"Enemy4",5);
}
