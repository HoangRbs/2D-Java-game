package States;

import BaseMainGame.Game;
import Path_Fiding.A_StarAlgorithm;
import gfx.Assets;
import gfx.Text;

import java.awt.*;


//NEVER USE THIS SHIT AGAIN

public class testA_Star_state extends State {

    public testA_Star_state()
    {
        A_StarAlgorithm.GenerateNodesTileMap(10,10);
        //just for testing
    }

    @Override
    public void Update() {
        A_StarAlgorithm.Update();
    }

    @Override
    public void Render(Graphics m_RealScreenObject) {

        A_StarAlgorithm.Render(m_RealScreenObject);

        Text.DrawText(m_RealScreenObject," TESTING A STAR ALGORITHM",800,700,Color.red,
                      Assets.GetInstance().m_StandardFont25);
    }

    @Override
    public void SetGameObject(Game _GameObject) {
        m_GameObject = _GameObject;
    }
}
