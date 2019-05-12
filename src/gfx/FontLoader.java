package gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {
    public static Font loadFont(String path,int Size)
    {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,new File(path)).deriveFont(Font.PLAIN,Size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
