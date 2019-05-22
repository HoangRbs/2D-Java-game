package TicTacToe;

import gfx.Assets;
import gfx.Text;

import java.awt.*;

public class Credit {
    public static void Render(Graphics m_RealScreenObject)
    {
        int x = 650;
        int y = 50;
        Text.DrawText(m_RealScreenObject,"The Unbeatable TicTacToe",x,y,Color.black, Assets.GetInstance().m_StandardFont18);
        Text.DrawText(m_RealScreenObject,"by HoangVy 99",x,y += 40,Color.black, Assets.GetInstance().m_StandardFont18);
        Text.DrawText(m_RealScreenObject,"dob: 17-06-99",x,y += 40,Color.black, Assets.GetInstance().m_StandardFont18);
        Text.DrawText(m_RealScreenObject,"test date:",x,y += 40,Color.black, Assets.GetInstance().m_StandardFont18);
        Text.DrawText(m_RealScreenObject,"22-05-2019",x,y += 40,Color.black, Assets.GetInstance().m_StandardFont18);

        m_RealScreenObject.drawImage(Assets.GetInstance().AuthorImage,x,y+=20,180,230,null);
        if(basicSystem.isX_Win)
        {
            Text.DrawText(m_RealScreenObject,"YOU WIN!!",x,y+=280,Color.blue,Assets.GetInstance().m_StandardFont25);
        }
        else if(basicSystem.isO_Win)
        {
            Text.DrawText(m_RealScreenObject,"NGU VL!!!!",x,y+=280,Color.red,Assets.GetInstance().m_StandardFont25);
        }
        else if(basicSystem.isNoMoreSpace && !basicSystem.isX_Win && !basicSystem.isO_Win)
        {
            Text.DrawText(m_RealScreenObject,"DRAWN!!",x,y+=280,Color.blue,Assets.GetInstance().m_StandardFont25);
        }


    }
}
