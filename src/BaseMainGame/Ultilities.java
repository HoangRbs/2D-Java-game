package BaseMainGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ultilities {   //some functions to load our world

    public static String LoadDatas_AsString(String path)
    {
        StringBuilder m_stringBuilder = new StringBuilder();       //using stringbuilder is easier to work with ordinary String

        try {
            BufferedReader m_bufferReader = new BufferedReader(new FileReader(path));
            String SingleLine = m_bufferReader.readLine();
            while(SingleLine != null)
            {
                m_stringBuilder.append(SingleLine + "\n");
                SingleLine = m_bufferReader.readLine();
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return m_stringBuilder.toString();
    }

    public static int StringToInt(String input)
    {

        try {
            return Integer.parseInt(input);
        }
        catch(NumberFormatException e)     //just incase the String input is not number type. test later
        {
            e.printStackTrace();     //in this case the program crash
            return 0;
        }
    }
}
