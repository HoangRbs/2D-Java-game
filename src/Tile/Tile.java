package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    protected BufferedImage Texture;
    protected int ID;

    public Tile(int _ID,BufferedImage _texture)
    {
        this.ID = _ID;
        this.Texture = _texture;

        //adding its self into m_Tiles[];
        TileInitStuff.m_Tiles[this.ID] = this;
    }

    public int GetID(){ return this.ID; }

    public void Update()
    {
        //no need to update right now
    }

    public void Render(Graphics m_RealScreenObject,int _x,int _y)
    {
        m_RealScreenObject.drawImage(Texture,_x,_y,TileInitStuff.TILE_WIDTH,TileInitStuff.TILE_HEIGHT,null);
    }

    public boolean isCollidable()
    {
        return false;
    }
}
