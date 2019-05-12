package States;

import BaseMainGame.Game;

import java.awt.*;

public abstract class State {

    protected Game m_GameObject = null;
    public abstract void Update();
    public abstract void Render(Graphics m_RealScreenObject);
    public abstract void SetGameObject(Game _GameObject);
}
