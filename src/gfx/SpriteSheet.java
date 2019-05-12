package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage m_SpriteSheet;

    public SpriteSheet(BufferedImage image_)
    {
        this.m_SpriteSheet = image_;
    }

    public BufferedImage Crop(int x,int y,int width,int height)
    {
        return m_SpriteSheet.getSubimage(x, y, width, height);
    }
}
