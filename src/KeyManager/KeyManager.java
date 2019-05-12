package KeyManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    //THE KeyManager Object will be created in Game Class
    private boolean[] m_Keys_State;
    private boolean[] justPressed;
    private boolean[] stillPressing;

    public boolean isPressUp;
    public boolean isPressDown;
    public boolean isPressLeft;
    public boolean isPressRight;
    public boolean isPressEnter;

    //Attack for Player
    public boolean isPressA_Up;    //Attack Up
    public boolean isPressA_Down;
    public boolean isPressA_Left;
    public boolean isPressA_Right;

    public KeyManager()
    {
        m_Keys_State = new boolean[256];     //store the state of all keys on keyboard
                                             // the maximum ID is 0-->255
                                             // in java ,a new allocated boolean array will be initialized to FALSE
        justPressed = new boolean[m_Keys_State.length];
        stillPressing = new boolean[m_Keys_State.length];

    }

    public void Update()         //to see if the Up/Down/Left/Right is pressed --> change Player behavior
                                 //used in GAME.UPDATE()
    {

        for(int i = 0 ; i < m_Keys_State.length;i++)   //control just pressed a key
        {
            if(justPressed[i])
            {
                justPressed[i] = false;
                stillPressing[i] = true;
            }

            if(stillPressing[i] && !m_Keys_State[i])
            {
                stillPressing[i] = false;
            }

            if(m_Keys_State[i] && !stillPressing[i])
            {
                justPressed[i] = true;
            }
        }


        isPressUp = m_Keys_State[KeyEvent.VK_W];
        isPressDown = m_Keys_State[KeyEvent.VK_S];
        isPressLeft = m_Keys_State[KeyEvent.VK_A];
        isPressRight = m_Keys_State[KeyEvent.VK_D];
        isPressEnter = m_Keys_State[KeyEvent.VK_ENTER];

        isPressA_Up = m_Keys_State[KeyEvent.VK_UP];
        isPressA_Down = m_Keys_State[KeyEvent.VK_DOWN];
        isPressA_Left = m_Keys_State[KeyEvent.VK_LEFT];
        isPressA_Right = m_Keys_State[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //NOTHING YET
    }

    @Override
    public void keyPressed(KeyEvent e) {          //a built-in function is automatically called
                                                  //whenever a key is pressed
        m_Keys_State[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {       //a built-in function is automatically called
                                                //whenever a key is released
        m_Keys_State[e.getKeyCode()] = false;
    }

    public boolean isJustPress(int KeyCode)
    {
        return justPressed[KeyCode];
    }

}
