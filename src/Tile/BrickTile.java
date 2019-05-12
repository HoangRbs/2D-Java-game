package Tile;

import gfx.Assets;

public class BrickTile extends Tile{

    public BrickTile(int _ID)
    {
        super(_ID, Assets.GetInstance().RockTile);
    }

    @Override
    public boolean isCollidable()
    {
        return true;
    }
}
