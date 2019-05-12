package TestingBullshit;

import java.util.LinkedList;
import java.util.Queue;

public class TestBullshit_2 {
    public static void main(String args[])
    {
        Queue<String> m_queue = new LinkedList<String>();

        m_queue.add("hoang");
        m_queue.add("dep");
        m_queue.add("trai");

        String head = m_queue.remove();

        System.out.println(m_queue);
        System.out.println(head);
    }
}
