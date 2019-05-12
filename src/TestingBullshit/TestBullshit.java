package TestingBullshit;

public class TestBullshit {

    static int Count = 0;

    static synchronized void Count_Increment()
    {
        Count++;
    }

    public static void main(String args[])
    {
        //Thread1 m_thread_1 = new Thread1();
        //Thread2 m_thread_2 = new Thread2();

        //m_thread_1.start();             //call start() --> the run() method is called automatically
        //m_thread_2.start();

       Thread new_Thread_1 = new Thread(new Runnable(){
           @Override
           public void run()
           {
               for(int i = 0; i < 10000; i++)
               {
                   Count_Increment();
               }
           }
       });

       Thread new_Thread_2 = new Thread(new Runnable() {
           @Override
           public void run() {

               for(int i = 0; i < 10000; i++)
               {
                   Count_Increment();
               }
           }
       });


       new_Thread_1.start();
       new_Thread_2.start();

       try
       {
           new_Thread_1.join();
           new_Thread_2.join();
       }
       catch(InterruptedException e)
       {
            e.printStackTrace();
       }

        System.out.println(Count);
    }
}
