package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage LoadBufferedImage(String path)
    {
        try
        {
            return ImageIO.read(new File(path));
        }
        catch(IOException e)    //IO exception not Interrupted exception bitch
        {
            e.printStackTrace();
        }

        return null;
    }
}
