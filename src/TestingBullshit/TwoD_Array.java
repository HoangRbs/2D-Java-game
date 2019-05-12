package TestingBullshit;

import java.util.Scanner;

public class TwoD_Array {
    public static void main(String args[])
    {
        int [][] testArray = new int[2][3];

        Scanner m_scanner = new Scanner(System.in);

        for(int Height = 0; Height < 2;Height++)
        {
            for(int Width = 0; Width < 3;Width++)
            {
                testArray[Height][Width] = m_scanner.nextInt();
            }
        }

        System.out.println(" Printing out Array");

        for(int Height = 0; Height < 2;Height++)
        {
            for(int Width = 0; Width < 3;Width++)
            {
                System.out.print(testArray[Height][Width] + " ");
            }

            System.out.println();
        }
    }
}
