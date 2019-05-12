package Display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame m_Window;
    private Canvas m_Renderer;
    private String WindowTitle;
    private int WindowWidth , WindowHeight;

    public Display(String WindowTitle, int WindowWidth, int WindowHeight)
    {
        this.WindowTitle = WindowTitle;
        this.WindowWidth = WindowWidth;
        this.WindowHeight = WindowHeight;

        StartCreateDisplay();
    }

    private void StartCreateDisplay()       //Init my frame
    {
        m_Window = new JFrame(WindowTitle);
        m_Window.setSize(WindowWidth,WindowHeight);
        m_Window.setResizable(false);
        m_Window.setLocationRelativeTo(null);   //Center my frame
        m_Window.setVisible(true);
        m_Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //close the Display --> auto close the game
                                                                    //close my game properly
                                                                    //avoid just closing the window while the game
                                                                    //is still running in the background
        m_Renderer = new Canvas();
        m_Renderer.setPreferredSize(new Dimension(WindowWidth,WindowHeight));
        m_Renderer.setMaximumSize(new Dimension(WindowWidth,WindowHeight));
        m_Renderer.setMinimumSize(new Dimension(WindowWidth,WindowHeight));
        m_Renderer.setFocusable(false);  //Will FOCUS more on m_Window...
                                         //...the above JAVA COMPONENT that KEY LISTENER registered to

        m_Window.add(m_Renderer);
        m_Window.pack();    //just resize the window to fit the canvas to see that shit fully
    }

    public Canvas GetRenderer()
    {
        return this.m_Renderer;
    }
    public JFrame GetWindow(){ return this.m_Window;};
}
