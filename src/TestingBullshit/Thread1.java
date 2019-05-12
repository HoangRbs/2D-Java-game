package TestingBullshit;

public class Thread1 extends Thread{
    @Override
    public void run()      //run program here
    {
        for(int i = 0; i < 10; i++)
        {
            System.out.println(" A " + i);

            try {
                Thread.sleep(100);
            }
            catch(InterruptedException e)        //To avoid Thread Errors like : not working, infinity thread , ..etc
            {
                e.printStackTrace();
            }

        }
    }
}
