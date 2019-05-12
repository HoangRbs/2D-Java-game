package MultiThreadTestingProject;

//JUST A TESTING PROJECT

public class MultiThreadProject {

    public static void main(String args[])
    {
        Task task1 = new Task();    //creating an object first
        Thread task1_Thread = new Thread(task1);    //creating a Thread for above object
        task1_Thread.start();  //start the thread

        Task task2 = new Task();
        Thread task2_Thread = new Thread(task2);
        task2_Thread.start();

        System.out.println("Main cmnr");
    }
}

class Task implements Runnable
{
    public void run() //run() in runnable Interface auto called when a Thread Start();
    {
        for(int i = 0; i < 50; i++)
        {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
