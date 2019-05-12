package Tile;

import gfx.Assets;

public class GrassTile extends Tile {

    public GrassTile(int _ID)
    {
        super(_ID, Assets.GetInstance().GrassTile);
    }
}
