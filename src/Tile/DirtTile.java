package Tile;

import gfx.Assets;

public class DirtTile extends Tile {

    public DirtTile(int _ID)
    {
        super(_ID, Assets.GetInstance().DirtTile);
    }
}
