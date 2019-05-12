package Tile;

public class TileInitStuff {
    //a class contains bunch of static stuff to init tiles
    public static Tile m_Tiles[] = new Tile[100];   //we're gonna access all tiles through this public Array

    private static Tile m_grassTile = new GrassTile(0);
    private static Tile m_dirtTile = new DirtTile(1);
    private static Tile m_BrickTile = new BrickTile(2);

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    //once a Tile is created --> it will automatically add in m_Tiles array[]
    //in its constructor Tile()
}
