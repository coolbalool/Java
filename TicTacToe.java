import java.util.Scanner;
import java.util.Random;


public class TicTacToe 
{
 
    private char[][] arr;
    private char p1 = 'X';
    private char p2 = 'O';
    private static Scanner in = new Scanner(System.in);
    private static Random rnd = new Random();

    public TicTacToe(char[][] arr)
    {
        if (arr.length == arr[0].length)
            this.arr = arr;
        else System.err.println("Not a legal board!");
    }

    public TicTacToe(int size)
    {
        arr = new char[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                arr[i][j] = ' ';
            }
        }
    }

    public void setCharacter(char p1, char p2)
    {
        if (p1 == p2) 
        return;
        this.p1 = p1; 
        this.p2 = p2;
        
    }

    public char play()
    {
        while(checkWinner() == ' ')
        {
            if (isFull()) return ' ';
            input(p1);
            if (isFull()) return ' ';
            if (checkWinner() != ' ') break;
            input(p2);
        }
        System.out.println("The Winner Is: " + checkWinner());
        return checkWinner();
    }

    

    private boolean isFull()
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (arr[i][j] == ' ') 
                return false;
            }
        }
        return true;
    }

    private void input(char p)
    {
        printBoard();
        System.out.println(p + ": Type the location that you want to go to");
        int pos = in.nextInt();
        if (pos < 1 || pos > arr.length * arr.length) 
        return;        
        int sum = 1;
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (sum == pos && arr[i][j] == ' ')
                {
                    arr[i][j] = p;
                    return;
                }
                else if (sum == pos) input(p);
                sum++;
            }
        } 
    }

    private void printBoard()
    {
        int count = 1;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length;j++)
        {
            System.out.print(  count + ":" + arr[i][j] + " ");
            count++;
        }
        System.out.println();
        }
    }

    public char checkWinner()
    {
        char tempRow = ' ';
        char tempCol = ' ';
        char tempMid = ' ';
        char tempOpMid = ' ';
        for (int i = 0; i < arr.length; i++)
        {
            
            // Row checker
            for (int j = 0; j < arr.length; j++)
            {
                tempRow = arr[i][0];
                if (tempRow == ' ') break;
                if (tempRow == arr[i][j] && j == arr.length-1) return tempRow;
                if (tempRow != arr[i][j]) break;
            }

            // Col checker
            for (int j = 0; j < arr.length; j++)
            {
                tempCol = arr[0][i];
                if (tempCol == ' ') break;
                if (tempCol == arr[j][i] && j == arr.length-1) return tempCol;
                if (tempCol != arr[j][i]) break;
            }
        }

        // checks the (0,0) cross
        for (int i = 0; i < arr.length; i++)
        {
            tempMid = arr[0][0];
            if (arr[i][i] != tempMid) break;
            if (i == arr.length -1) return tempMid;
        }

        // checks the other cross
        for (int i = 0; i < arr.length; i++)
        {
            tempOpMid = arr[0][arr.length-1];
            if (tempOpMid == ' ') break;
            if (arr[i][arr.length  - i -1] != tempOpMid) break;
            if ( i == arr.length -1) return tempOpMid;
        }

        return ' ';
    }

}
