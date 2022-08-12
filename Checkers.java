import java.util.Scanner;

public class Checkers 
{
 
    private char p1, p2,P1,P2;
    boolean eatHint;
    private char[][] arr;
    private static Scanner in;

    public Checkers()
    {
        this.p1 = 'w';
        this.P1 = 'W';
        this.p2 = 'b';
        this.P2 = 'B';
        arr = new char[8][8];
        eatHint = false;
        in = new Scanner(System.in);
        setBoard();
        printBoard();
    }

    private boolean isEven(int num)
    {
        return num % 2 == 0;
    }

    private void printBoard()
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println();
            for (int j = 0; j < arr[0].length; j++){
            System.out.print(' ');
            System.out.print(arr[i][j]);
            }
        }
    }

    private void setBoard()
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if (!isEven(j) && !isEven(i)) 
                {
                    arr[i][j] = '#';
                    continue;
                }

                if (isEven(j) && isEven(i))
                {
                    arr[i][j] = '#';
                    continue;
                }

                if (i < 3) arr[i][j] = p2;
                else if (i > 4) arr[i][j] = p1;
                else arr[i][j] = '.';
            }
        }

    }

    private boolean movePiece(char p)
    {
        System.out.println("Type the piece that you want to move");
        int piece = in.nextInt();
        if (piece == -1) return false;
        
        return false;
    }

}

