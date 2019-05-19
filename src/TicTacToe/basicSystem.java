package TicTacToe;

public class basicSystem {
    private basicSystem()
    {

    }

    public static boolean Xturn = true;
    public static int BoardSize = 3;
    public static Cell[][] Board = new Cell[BoardSize][BoardSize];
    private int MaxToWin = 3;

    public static boolean check_X_Win()
    {
        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < BoardSize; Xindex++)
            {

            }
        }

        return false;
    }

    public static boolean check_O_Win()
    {
        for(int Yindex = 0; Yindex < BoardSize;Yindex++)
        {
            for(int Xindex = 0; Xindex < BoardSize; Xindex++)
            {

            }
        }

        return false;
    }
}
