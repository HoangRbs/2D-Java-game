package gfx;

import java.awt.image.BufferedImage;

public class Animation {

    private int Index;
    private Long AnimTimeCounter;
    private int AnimSpeed;       //Milisec
    private BufferedImage[] AnimFrames;

    public Animation(int AnimSpeedMilisec, BufferedImage[] AnimFrames)
    {
        this.AnimSpeed = AnimSpeedMilisec;
        this.AnimFrames = AnimFrames;
        Index = 0;
        AnimTimeCounter = (long)0;
    }

    private long LastTime = System.currentTimeMillis();

    public void update()     //update animation
    {
        AnimTimeCounter += System.currentTimeMillis() - LastTime;
        LastTime = System.currentTimeMillis();

        if(AnimTimeCounter >= AnimSpeed)
        {
            if(Index >= AnimFrames.length - 1)
            {
                Index = 0;
            }
            else
            {
                Index++;
            }

            AnimTimeCounter = (long)0;
        }
    }

    public BufferedImage getCurrentAnimFrame()
    {
        if(Index >= AnimFrames.length)
        {
            Index = 0;
        }
        return AnimFrames[Index];
    }
}
